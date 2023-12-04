package br.edu.ifpb.ads.dao.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;

import br.edu.ifpb.ads.dao.AlunoDAO;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;
import br.edu.ifpb.ads.model.Aluno;
import br.edu.ifpb.ads.model.Mensalidade;
import br.edu.ifpb.ads.model.enums.StatusPagamento;

public class AlunoDaoImpl implements AlunoDAO {

    private static final String ARQUIVO_XML = "alunos.xml";
    private XStream xstream;
    private List<AlunoDTO> alunos;
    private static AlunoDaoImpl instancia;
    private ModelMapper modelMapper = new ModelMapper();

    private AlunoDaoImpl() {
        xstream = new XStream(new DomDriver("UTF-8"));
        xstream.alias("aluno", AlunoDTO.class);

        TypePermission allowAll = new AnyTypePermission();
        TypePermission allowAluno = new WildcardTypePermission(new String[] { "model.Aluno" });
        xstream.addPermission(allowAll);
        xstream.addPermission(allowAluno);

        alunos = carregarDados();
    }

    public static AlunoDaoImpl getInstancia() {
        if (instancia == null) {
            instancia = new AlunoDaoImpl();
        }
        return instancia;
    }

    @SuppressWarnings("unchecked")
    public List<AlunoDTO> carregarDados() {
        try {
            FileInputStream fileInputStream = new FileInputStream(ARQUIVO_XML);
            return (ArrayList<AlunoDTO>) xstream.fromXML(fileInputStream);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<AlunoDTO> listarAlunos() {
        List<AlunoDTO> alunosModel = carregarDados();
        List<AlunoDTO> alunosDTO = new ArrayList<>();

        for (AlunoDTO aluno : alunosModel) {
            AlunoDTO dto = modelMapper.map(aluno, AlunoDTO.class);
            alunosDTO.add(dto);
        }

        return alunosDTO;
    }

    @Override
    public AlunoDTO buscarAlunoPorMatricula(String matricula) {
        for (AlunoDTO aluno : alunos) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula)) {
                return modelMapper.map(aluno, AlunoDTO.class);
            }
        }
        return null;
    }

    @Override
    public void salvarAluno(AlunoDTO novoAlunoDTO) throws AlunoJaExisteException {
        if (buscarAlunoPorMatricula(novoAlunoDTO.getMatricula()) != null) {
            throw new AlunoJaExisteException("Aluno com a matrícula " + novoAlunoDTO.getMatricula() + " já existe!");
        }

        
        Aluno aluno = modelMapper.map(novoAlunoDTO, Aluno.class);
        if (aluno.getMensalidades() == null || aluno.getMensalidades().isEmpty()) {
            List<Mensalidade> mensalidades = aluno.gerarMensalidades(aluno);
            aluno.setMensalidades(mensalidades);
        }
        AlunoDTO alunoComMensalidadesDTO = modelMapper.map(aluno, AlunoDTO.class);

        alunos.add(alunoComMensalidadesDTO);
        salvarDados();
    }

    @Override
    public void atualizarAluno(String matricula, AlunoDTO alunoAtualizadoDTO) throws AlunoNaoEncontradoException {
        AlunoDTO alunoEncontradoDTO = buscarAlunoPorMatricula(matricula);
        if (alunoEncontradoDTO == null) {
            throw new AlunoNaoEncontradoException("Aluno com a matrícula " + matricula + " não encontrado!");
        }

        AlunoDTO alunoEncontrado = modelMapper.map(alunoEncontradoDTO, AlunoDTO.class);
        AlunoDTO alunoAtualizado = modelMapper.map(alunoAtualizadoDTO, AlunoDTO.class);
        alunos.set(alunos.indexOf(alunoEncontrado), alunoAtualizado);
        salvarDados();
    }

    @Override
    public void removerAluno(String matricula) throws AlunoNaoEncontradoException {
        AlunoDTO alunoDTO = buscarAlunoPorMatricula(matricula);
        if (alunoDTO == null) {
            throw new AlunoNaoEncontradoException("Aluno com matrícula " + matricula + " não encontrado.");
        }

        AlunoDTO aluno = modelMapper.map(alunoDTO, AlunoDTO.class);
        alunos.remove(aluno);
        salvarDados();
    }

    private void salvarDados() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ARQUIVO_XML);
            xstream.toXML(alunos, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<AlunoDTO> buscarAlunosAtivos() {
        List<AlunoDTO> alunos = listarAlunos();
        Iterator<AlunoDTO> iterator = alunos.iterator();
        while (iterator.hasNext()) {
            AlunoDTO aluno = iterator.next();
            if (!aluno.isAtivo()) {
                iterator.remove();
            }
        }
        return alunos;
    }

    public List<AlunoDTO> buscarAlunosInativos() {
        List<AlunoDTO> alunos = listarAlunos();
        Iterator<AlunoDTO> iterator = alunos.iterator();
        while (iterator.hasNext()) {
            AlunoDTO aluno = iterator.next();
            if (aluno.isAtivo()) {
                iterator.remove();
            }
        }
        return alunos;
    }

    public List<AlunoDTO> buscarAlunosPorMensalidadeAtrasada() {
        List<AlunoDTO> alunos = listarAlunos();
        List<AlunoDTO> alunosComMensalidadeAtrasada = new ArrayList<>();

        for (AlunoDTO aluno : alunos) {
            for (Mensalidade mensalidade : aluno.getMensalidades()) {
                if (mensalidade.getStatusPagamento() == StatusPagamento.ATRASADA) {
                    alunosComMensalidadeAtrasada.add(aluno);
                    break;
                }
            }
        }

        return alunosComMensalidadeAtrasada;
    }

    public List<AlunoDTO> buscarAlunosPosData(LocalDate data) {
        List<AlunoDTO> alunos = listarAlunos();
        List<AlunoDTO> alunosFiltroData = new ArrayList<>();
        for (AlunoDTO aluno : alunos) {
            if (aluno.getDataMatricula().isAfter(data)) {
                alunosFiltroData.add(aluno);
            }
        }

        return alunosFiltroData;
    }

}
