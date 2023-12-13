package br.edu.ifpb.ads.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends Pessoa {

    private String matricula;
    private BigDecimal salario;
    private String nivel;
    private boolean ativo;


}
