package br.edu.ifpb.ads.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.email.EmailServico;
import br.edu.ifpb.ads.model.enums.StatusPagamento;
import br.edu.ifpb.ads.observer.Observador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Aluno extends Pessoa {

    private String matricula;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private String turno;
    private String nivel;
    private LocalDate dataMatricula;
    private List<Mensalidade> mensalidades;
    private boolean ativo;
    private boolean inadimplente;


    public Aluno(){
        this.dataMatricula = LocalDate.now();
        this.mensalidades = gerarMensalidades(this);
        this.ativo = true;
    }

    public ArrayList<Mensalidade> gerarMensalidades(Aluno aluno){
        ArrayList<Mensalidade> mensalidades = new ArrayList<Mensalidade>();
        for(int i = 1; i <= 5; i++){
            LocalDate dataVencimento = dataMatricula.plusMonths(i);
            Mensalidade mensalidade = new Mensalidade(BigDecimal.valueOf(125), dataVencimento);
            mensalidades.add(mensalidade);
        }
        return mensalidades;
    }


    private List<Observador> observadores = new ArrayList<>();

    public void adicionarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores() {
        if (this.isInadimplente()) {
            ModelMapper modelMapper = new ModelMapper();
            AlunoDTO alunoDTO = modelMapper.map(this, AlunoDTO.class);
            for (Observador observador : observadores) {
                observador.notificar(alunoDTO);
            }
        }
    }

    public void setInadimplente(boolean inadimplente) {
        this.inadimplente = inadimplente;
        if (inadimplente) {
            notificarObservadores();
        }
    }
}

