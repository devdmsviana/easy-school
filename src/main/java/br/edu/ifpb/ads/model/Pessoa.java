package br.edu.ifpb.ads.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    private Endereco endereco;

    public Pessoa() {
        this.id = System.currentTimeMillis();
    }

}
