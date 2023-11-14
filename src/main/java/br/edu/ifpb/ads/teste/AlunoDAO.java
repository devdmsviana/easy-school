package br.edu.ifpb.ads.dao;

import java.util.List;

import br.edu.ifpb.ads.model.Aluno;

public interface AlunoDAO {
    List<Aluno> listarAlunos();
    Aluno buscarAluno(String matricula);
    void adicionarAluno(Aluno aluno);
    void atualizarAluno(Aluno aluno);
    void removerAluno(String matricula);
}
