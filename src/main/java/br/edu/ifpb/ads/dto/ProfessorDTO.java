package br.edu.ifpb.ads.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.edu.ifpb.ads.model.Endereco;
import br.edu.ifpb.ads.model.Mensalidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDTO {

    public long id;
    public String nome;
    public String email;
    public LocalDate dataNascimento;
    public String telefone;
    public String matricula;
    public BigDecimal salario;
    public String turno;
    public LocalDate dataMatricula;
    public boolean ativo;
    public Endereco endereco;

    public ProfessorDTO(String nome, String email, LocalDate dataNascimento, String telefone, String matricula,
                        BigDecimal salario, String turno, String nivel, LocalDate dataMatricula, boolean ativo,
                        Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.matricula = matricula;
        this.salario = salario;
        this.turno = turno;
        this.dataMatricula = dataMatricula;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public ProfessorDTO() {
    }
}
