package br.edu.ifpb.ads.dao;

import java.util.List;

import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;

public interface AlunoDAO {
    List<AlunoDTO> listarAlunos();
    AlunoDTO buscarAlunoPorMatricula(String matricula) throws AlunoNaoEncontradoException;
    void salvarAluno(AlunoDTO aluno) throws AlunoJaExisteException;
    void atualizarAluno(String matricula, AlunoDTO aluno) throws AlunoNaoEncontradoException;
    void removerAluno(String matricula) throws AlunoNaoEncontradoException;
}
