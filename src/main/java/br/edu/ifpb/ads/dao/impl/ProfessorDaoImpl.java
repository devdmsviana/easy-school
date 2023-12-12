package br.edu.ifpb.ads.dao.impl;

import br.edu.ifpb.ads.dao.ProfessorDAO;
import br.edu.ifpb.ads.dto.ProfessorDTO;
import br.edu.ifpb.ads.exception.ProfessorJaExistenteException;
import br.edu.ifpb.ads.exception.ProfessorNaoEncontradoException;
import br.edu.ifpb.ads.model.Professor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;
import org.modelmapper.ModelMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfessorDaoImpl implements ProfessorDAO {

    private XStream xstream;

    private static final String ARQUIVO_XML = "professores.xml";
    private HashMap<Long, Professor> professores;
    private static ProfessorDaoImpl instancia;
    private ModelMapper modelMapper = new ModelMapper();

    private ProfessorDaoImpl() {
        xstream = new XStream(new DomDriver("UTF-8"));
        xstream.alias("professor", ProfessorDTO.class);

        TypePermission allowAll = new AnyTypePermission();
        TypePermission allowProfessor = new WildcardTypePermission(new String[]{"model.Professor"});
        xstream.addPermission(allowAll);
        xstream.addPermission(allowProfessor);

        professores = carregarDados();
    }

    public static ProfessorDaoImpl getInstancia() {
        if (instancia == null) {
            instancia = new ProfessorDaoImpl();
        }
        return instancia;
    }

    @SuppressWarnings("unchecked")
    public HashMap<Long, Professor> carregarDados() {
        try {
            FileInputStream fileInputStream = new FileInputStream(ARQUIVO_XML);
            return (HashMap<Long, Professor>) xstream.fromXML(fileInputStream);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    @Override
    public Professor buscarProfessor(long id) {
        Professor professor = professores.get(id);

        return professor;
    }

    @Override
    public List<Professor> listarProfessor() {
        HashMap professorModel = carregarDados();
        return new ArrayList<>(professorModel.values());
    }

    @Override
    public Professor buscarProfessorPorMatricula(String matricula) throws ProfessorNaoEncontradoException {
        List<Professor> professorList = listarProfessor();
        for (Professor professor : professorList)
            if (professor.equals(matricula)) {
                return professor;
            }
        return null;
    }

    @Override
    public Professor salvarProfessor(Professor professor) throws ProfessorJaExistenteException {
        Professor professorExistente = buscarProfessor(professor.getId());
        if (professorExistente != null) {
            throw new ProfessorJaExistenteException("Professor já existe!");
        }

        professores.put(professor.getId(),professor);
        salvarDados();
        return professor;
    }

    @Override
    public void atualizarProfessor(Professor professor) throws ProfessorNaoEncontradoException {
        Professor professorEncontrado = buscarProfessor(professor.getId());

        if (professorEncontrado == null) {
            throw new ProfessorNaoEncontradoException("Porfessor não encontrado!");
        }
        professores.put(professor.getId(), professor);
        salvarDados();
    }

    @Override
    public void removerProfessor(long id) throws ProfessorNaoEncontradoException {
        Professor professor = buscarProfessor(id);
        if (professor == null) {
            throw new ProfessorNaoEncontradoException("Professor não encontrado.");
        }

        professores.remove(professor.getId());
        salvarDados();
    }

    private void salvarDados() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ARQUIVO_XML);
            xstream.toXML(professores, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
