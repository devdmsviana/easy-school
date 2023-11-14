package br.edu.ifpb.ads.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public abstract class Pessoa {


    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;


    public Pessoa(){
        this.id = System.currentTimeMillis();
    }

    
}
