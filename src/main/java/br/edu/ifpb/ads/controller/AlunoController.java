package br.edu.ifpb.ads.controller;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifpb.ads.dao.impl.AlunoDaoImpl;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;
import br.edu.ifpb.ads.model.Aluno;

public class AlunoController {


    private final AlunoDaoImpl alunoDao;

    public AlunoController(){
        alunoDao = AlunoDaoImpl.getInstancia();
    }


    public List<Aluno> listarAlunos(){
        return alunoDao.listarAlunos();
    }

    public Aluno buscarAluno(String matricula) throws AlunoNaoEncontradoException {
        return alunoDao.buscarAluno(matricula);
    }

    public void salvarAluno(Aluno aluno) throws AlunoJaExisteException{
        alunoDao.salvarAluno(aluno);
    }

    public void atualizarAluno(Aluno aluno) throws AlunoNaoEncontradoException {
        alunoDao.atualizarAluno(aluno);
    }

    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        alunoDao.removerAluno(matricula);
    }


    public List<Aluno> buscarAlunosInativos() {
        return alunoDao.buscarAlunosInativos();
    }

    public List<Aluno> buscarAlunosPorMensalidadeAtrasada() {
        return alunoDao.buscarAlunosPorMensalidadeAtrasada();
    }

    public List<Aluno> buscarAlunosAtivos(){
        return alunoDao.buscarAlunosAtivos();
    }

    public List<Aluno> buscarAlunosPosData(LocalDate data){
        return alunoDao.buscarAlunosPosData(data);
    }   



    
}
