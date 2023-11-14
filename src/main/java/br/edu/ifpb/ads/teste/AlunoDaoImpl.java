package br.edu.ifpb.ads.dao.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;

import br.edu.ifpb.ads.dao.AlunoDAO;
import br.edu.ifpb.ads.model.Aluno;
import java.util.Iterator;

public class AlunoDaoImpl implements AlunoDAO {

    private static final String ARQUIVO_XML = "alunos.xml";
    private XStream xstream;

    public AlunoDaoImpl() {
        xstream = new XStream(new DomDriver());
        xstream.alias("aluno", Aluno.class);

         TypePermission allowAll = new AnyTypePermission();
        TypePermission allowAluno = new WildcardTypePermission(new String[]{"model.Aluno"});
        xstream.addPermission(allowAll);
        xstream.addPermission(allowAluno);
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
    public void adicionarAluno(Aluno aluno) {
        List<Aluno> alunos = listarAlunos();
        alunos.add(aluno);
        salvarAlunos(alunos);
    }


    @Override
    public void atualizarAluno(Aluno aluno) {
        List<Aluno> alunos = listarAlunos();
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getMatricula().equalsIgnoreCase(aluno.getMatricula())) {
                alunos.set(i, aluno);
                salvarAlunos(alunos);
                return; 
            }
        }
        
    }



    @Override
    public void removerAluno(String matricula) {
        List<Aluno> alunos = listarAlunos();
        for (Iterator<Aluno> iterator = alunos.iterator(); iterator.hasNext();) {
            Aluno aluno = iterator.next();
            if (aluno.getMatricula().equalsIgnoreCase(matricula)) {
                iterator.remove();
            }
        }
        salvarAlunos(alunos);
    }


    private void salvarAlunos(List<Aluno> alunos){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ARQUIVO_XML);
            xstream.toXML(alunos, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}