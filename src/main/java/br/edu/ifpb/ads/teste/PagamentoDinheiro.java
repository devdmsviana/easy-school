package br.edu.ifpb.ads.payments;

import java.math.BigDecimal;

public class PagamentoDinheiro implements FormaPagamentoStrategy{

    @Override
    public BigDecimal calcularValorPagamento(BigDecimal valorMensalidade) {
       //desconto de 5%
       BigDecimal desconto = new BigDecimal("0.05");
       return valorMensalidade.multiply(BigDecimal.ONE.subtract(desconto));
    }
    
}
