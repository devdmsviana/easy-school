package br.edu.ifpb.ads.payments;

import java.math.BigDecimal;

public class PagamentoCartao implements FormaPagamentoStrategy{

    @Override
    public BigDecimal calcularValorPagamento(BigDecimal valorMensalidade) {
        //acréscimo de 3%
        BigDecimal acrescimo = new BigDecimal("1.03");
        return valorMensalidade.multiply(acrescimo);
    }
    
}
