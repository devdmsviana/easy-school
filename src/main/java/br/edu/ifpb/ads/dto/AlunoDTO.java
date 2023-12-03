package br.edu.ifpb.ads.dto;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifpb.ads.model.Endereco;
import br.edu.ifpb.ads.model.Mensalidade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDTO {

    private String nome;

    private String email;

    private LocalDate dataNascimento;

    private String telefone;

    private String matricula;

    private String nomeResponsavel;

    private String telefoneResponsavel;

    private String turno;

    private String nivel;

    private LocalDate dataMatricula;
    private List<Mensalidade> mensalidades;
    private boolean ativo;
    private boolean inadimplente;
    private Endereco endereco;

}
