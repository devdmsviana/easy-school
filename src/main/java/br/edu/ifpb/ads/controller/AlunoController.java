package br.edu.ifpb.ads.controller;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifpb.ads.dao.impl.AlunoDaoImpl;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;

public class AlunoController {

    private final AlunoDaoImpl alunoDao;

    public AlunoController() {
        alunoDao = AlunoDaoImpl.getInstancia();
    }

    public List<AlunoDTO> listarAlunos() {
        return alunoDao.listarAlunos();
    }

    public AlunoDTO buscarAlunoPorMatricula(String matricula) throws AlunoNaoEncontradoException {
        return alunoDao.buscarAlunoPorMatricula(matricula);
    }

    public void salvarAluno(AlunoDTO alunoDTO) throws AlunoJaExisteException {
        alunoDao.salvarAluno(alunoDTO);
    }

    public void atualizarAluno(String matricula, AlunoDTO alunoDTO) throws AlunoNaoEncontradoException {
        alunoDao.atualizarAluno(matricula, alunoDTO);
    }

    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        alunoDao.removerAluno(matricula);
    }




     public List<AlunoDTO> buscarAlunosInativos() {
        return alunoDao.buscarAlunosInativos();
    }

    public List<AlunoDTO> buscarAlunosPorMensalidadeAtrasada() {
        return alunoDao.buscarAlunosPorMensalidadeAtrasada();
    }

    public List<AlunoDTO> buscarAlunosAtivos(){
        return alunoDao.buscarAlunosAtivos();
    }

    public List<AlunoDTO> buscarAlunosPosData(LocalDate data){
        return alunoDao.buscarAlunosPosData(data);
        
    }   

}

    

