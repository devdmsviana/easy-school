package br.edu.ifpb.ads.dao;

import java.util.List;

import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;
import br.edu.ifpb.ads.model.Aluno;

public interface AlunoDAO {
    List<Aluno> listarAlunos();
    Aluno buscarAluno(String matricula) throws AlunoNaoEncontradoException;
    void salvarAluno(Aluno aluno) throws AlunoJaExisteException;
    void atualizarAluno(Aluno aluno) throws AlunoNaoEncontradoException;
    void removerAluno(String matricula) throws AlunoNaoEncontradoException;
}
