package br.edu.ifpb.ads.payments;

import java.math.BigDecimal;

public class PagamentoPix implements FormaPagamentoStrategy {

    @Override
    public BigDecimal calcularValorPagamento(BigDecimal valorMensalidade) {
        return valorMensalidade;
    }
    
}
