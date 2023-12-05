package br.edu.ifpb.ads.dao;

import br.edu.ifpb.ads.exception.ProfessorJaExistenteException;
import br.edu.ifpb.ads.exception.ProfessorNaoEncontradoException;
import br.edu.ifpb.ads.model.Professor;

import java.util.List;

public interface ProfessorDAO {
    Professor buscarProfessor(long id);
    List<Professor> listarProfessor();
    Professor buscarProfessorPorMatricula(String matricula) throws ProfessorNaoEncontradoException;
    Professor salvarProfessor(Professor professor) throws ProfessorJaExistenteException;
    void atualizarProfessor(Professor professor) throws ProfessorNaoEncontradoException;
    void removerProfessor(long id) throws ProfessorNaoEncontradoException;
}
