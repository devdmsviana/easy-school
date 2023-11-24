package br.edu.ifpb.ads.dao.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;

import br.edu.ifpb.ads.dao.AlunoDAO;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;
import br.edu.ifpb.ads.model.Aluno;
import br.edu.ifpb.ads.model.Mensalidade;
import br.edu.ifpb.ads.model.enums.StatusPagamento;

public class AlunoDaoImpl implements AlunoDAO {

    private static final String ARQUIVO_XML = "alunos.xml";
    private XStream xstream;

    private static AlunoDaoImpl instancia;

    private AlunoDaoImpl() {
        xstream = new XStream(new DomDriver("UTF-8"));
        xstream.alias("aluno", Aluno.class);

        TypePermission allowAll = new AnyTypePermission();
        TypePermission allowAluno = new WildcardTypePermission(new String[]{"model.Aluno"});
        xstream.addPermission(allowAll);
        xstream.addPermission(allowAluno);
    }

    public static AlunoDaoImpl getInstancia(){
        if (instancia == null){
            instancia = new AlunoDaoImpl();
        }
        return instancia;
    }
    
    @SuppressWarnings("unchecked")
    public List<Aluno> listarAlunos(){
        try {
            FileInputStream fileInputStream = new FileInputStream(ARQUIVO_XML);
            return (ArrayList<Aluno>) xstream.fromXML(fileInputStream);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Aluno buscarAluno(String matricula) {
        List<Aluno> alunos = listarAlunos();
        for (Aluno aluno : alunos){
            if(aluno.getMatricula().equalsIgnoreCase(matricula)){
                return aluno;
            }
        }
        return null;
    }


    @Override
    public void salvarAluno(Aluno novoAluno) throws AlunoJaExisteException {
        if (buscarAluno(novoAluno.getMatricula()) != null){
            throw new AlunoJaExisteException("Aluno com a matrícula " + novoAluno.getMatricula() + " já existe!");
        }
        
        List<Aluno> alunos = listarAlunos();
        alunos.add(novoAluno);
        salvarDados(alunos);
    }


    @Override
    public void atualizarAluno(Aluno alunoAtualizado) throws AlunoNaoEncontradoException {
        Aluno alunoExiste = buscarAluno(alunoAtualizado.getMatricula());
        if (alunoExiste == null) {
            throw new AlunoNaoEncontradoException("Aluno com a matrícula " + alunoAtualizado.getMatricula() + " não encontrado!");
        }
    
        List<Aluno> alunos = listarAlunos();
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getMatricula().equalsIgnoreCase(alunoAtualizado.getMatricula())) {
                alunos.set(i, alunoAtualizado);
                salvarDados(alunos);
                return;
            }
        }
    }



    @Override
    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        Aluno aluno = buscarAluno(matricula);
        if (aluno == null) {
            throw new AlunoNaoEncontradoException("Aluno com matrícula " + matricula + " não encontrado.");
        }

        List<Aluno> alunos = listarAlunos();
        alunos.remove(aluno);
        salvarDados(alunos);
    }

    public List<Aluno> buscarAlunosAtivos(){
        List<Aluno> alunos = listarAlunos();
        Iterator<Aluno> iterator = alunos.iterator();
        while (iterator.hasNext()){
            Aluno aluno = iterator.next();
            if (!aluno.isAtivo()){
                iterator.remove();
            }
        }
        return alunos;
    }

    public List<Aluno> buscarAlunosInativos(){
        List<Aluno> alunos = listarAlunos();
        Iterator<Aluno> iterator = alunos.iterator();
        while (iterator.hasNext()){
            Aluno aluno = iterator.next();
            if (aluno.isAtivo()){
                iterator.remove();
            }
        }
        return alunos;
    }


    public List<Aluno> buscarAlunosPorMensalidadeAtrasada() {
        List<Aluno> alunos = listarAlunos();
        List<Aluno> alunosComMensalidadeAtrasada = new ArrayList<>();

        for (Aluno aluno : alunos) {
            for (Mensalidade mensalidade : aluno.getMensalidades()) {
                if (mensalidade.getStatusPagamento() == StatusPagamento.ATRASADO) {
                    alunosComMensalidadeAtrasada.add(aluno);
                    break;
                }
            }
        }

        return alunosComMensalidadeAtrasada;
    }

    public List<Aluno> buscarAlunosPosData(LocalDate data){
        List<Aluno> alunos = listarAlunos();
        List<Aluno> alunosFiltroData = new ArrayList<>();
        for (Aluno aluno : alunos){
            if (aluno.getDataMatricula().isAfter(data)){
                alunosFiltroData.add(aluno);
            }
        }

        return alunosFiltroData;
    }


   


    private void salvarDados(List<Aluno> alunos){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ARQUIVO_XML);
            xstream.toXML(alunos, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}