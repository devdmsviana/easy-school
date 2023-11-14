package br.edu.ifpb.ads.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private Endereco endereco;


    public Aluno(){
        this.dataMatricula = LocalDate.now();
        this.mensalidades = gerarMensalidades();
        this.ativo = true;
    }


    private ArrayList<Mensalidade> gerarMensalidades(){
        ArrayList<Mensalidade>  mensalidades = new ArrayList<Mensalidade>();
        for(int i = 1; i <= 5; i++){
            LocalDate dataVencimento = dataMatricula.plusMonths(i);
            mensalidades.add(new Mensalidade(BigDecimal.valueOf(125), dataVencimento));
        }

        return mensalidades;
    }




    
}
