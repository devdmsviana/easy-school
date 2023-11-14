package br.edu.ifpb.ads.payments;

import java.math.BigDecimal;

public interface FormaPagamentoStrategy {
    

    BigDecimal calcularValorPagamento(BigDecimal valorMensalidade);

}
