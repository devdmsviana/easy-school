package br.edu.ifpb.ads.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<Mensalidade> mensalidades;
    private boolean ativo;
    private boolean inadimplente;


    public Aluno(){
        this.dataMatricula = LocalDate.now();
        this.mensalidades = gerarMensalidades(this);
        this.ativo = true;
    }

    private ArrayList<Mensalidade> gerarMensalidades(Aluno aluno){
        ArrayList<Mensalidade> mensalidades = new ArrayList<Mensalidade>();
        for(int i = 1; i <= 5; i++){
            LocalDate dataVencimento = dataMatricula.plusMonths(i);
            Mensalidade mensalidade = new Mensalidade(BigDecimal.valueOf(125), dataVencimento);
            mensalidades.add(mensalidade);
        }
        return mensalidades;
    }


    // Lista de observadores
    private List<Observador> observadores = new ArrayList<>();

    // Método para adicionar um observador
    public void adicionarObservador(Observador observador) {
        observadores.add(observador);
    }

    // Método para remover um observador
    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    // Método para notificar todos os observadores
    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.notificar(this);
        }
    }

    // Método para atualizar o status de uma mensalidade e notificar observadores
    public void atualizarStatusMensalidade(Mensalidade mensalidade, StatusPagamento status) {
        mensalidade.setStatusPagamento(status);
        if (status == StatusPagamento.ATRASADO) {
            notificarObservadores();
        }
    }




    
}
