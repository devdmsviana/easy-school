package br.edu.ifpb.ads.controller;
import br.edu.ifpb.ads.dao.impl.ProfessorDaoImpl;
import br.edu.ifpb.ads.dto.ProfessorDTO;
import br.edu.ifpb.ads.exception.ProfessorJaExistenteException;
import br.edu.ifpb.ads.exception.ProfessorNaoEncontradoException;
import br.edu.ifpb.ads.model.Professor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProfessorController {
    private final ProfessorDaoImpl professorDao;
    private ModelMapper modelMapper = new ModelMapper();

    public ProfessorController() {
        professorDao = ProfessorDaoImpl.getInstancia();
    }

    public void salvarProfessor(ProfessorDTO professorDTO) throws ProfessorJaExistenteException {
        Professor professor = modelMapper.map(professorDTO, Professor.class);
        professorDao.salvarProfessor(professor);
    }

    public void atualizarProfessor(ProfessorDTO professorDTO) throws ProfessorNaoEncontradoException {
        Professor professor = modelMapper.map(professorDTO, Professor.class);
        professorDao.atualizarProfessor(professor);
    }

    public void removerProfessor(long id) throws ProfessorNaoEncontradoException {
        professorDao.removerProfessor(id);
    }

    public List<ProfessorDTO> listarProfessores() {
        List<ProfessorDTO> professores = new ArrayList<>();
        for (Professor p : professorDao.listarProfessor()) {
            professores.add(modelMapper.map(p, ProfessorDTO.class));
        }
        return professores;
    }

    public ProfessorDTO buscarProfessor(long id) {
        Professor professor = professorDao.buscarProfessor(id);
        ProfessorDTO professorDTO = modelMapper.map(professor, ProfessorDTO.class);
        return professorDTO;
    }
}