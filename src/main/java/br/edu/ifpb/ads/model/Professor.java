package br.edu.ifpb.ads.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends Pessoa {

    private BigDecimal salario;
    private String nivel;
    private boolean ativo;
    
    
}
