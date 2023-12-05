package br.edu.ifpb.ads.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.ads.model.enums.StatusPagamento;
import br.edu.ifpb.ads.payments.FormaPagamentoStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensalidade {

    private BigDecimal valor;
    private LocalDate dataVencimento;
    private StatusPagamento statusPagamento;
    private FormaPagamentoStrategy formaPagamentoStrategy;

    public Mensalidade(BigDecimal valor, LocalDate dataVencimento) {
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    public Mensalidade(BigDecimal valor) {
        this.valor = valor;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento){
        if (LocalDate.now().isAfter(dataVencimento)){
            this.statusPagamento = StatusPagamento.ATRASADA;
        } else {
            this.statusPagamento = statusPagamento;
        }
    }

    public boolean isMensalidadeAtrasada() {
        if (dataVencimento == null) {
            return false; // ou lançar uma exceção
        }
        LocalDate dataAtual = LocalDate.now();
        return dataAtual.isAfter(dataVencimento) && statusPagamento == StatusPagamento.ATRASADA;
    }

    public int calcularDiasAtraso() {
        LocalDate dataAtual = LocalDate.now();
        return (int) dataVencimento.until(dataAtual).getDays();
    }

    public void calcularPagamento() {
        if (statusPagamento == StatusPagamento.PENDENTE) {
            BigDecimal valorFinal = formaPagamentoStrategy.calcularValorPagamento(valor);

            if (isMensalidadeAtrasada()) {
                int diasAtraso = calcularDiasAtraso();
                valorFinal = valorFinal.add(valor.multiply(BigDecimal.valueOf(0.01 * diasAtraso)));
            }

            efetuarPagamento(valorFinal);
        }
    }

    public void efetuarPagamento(BigDecimal valorFinal) {
        this.statusPagamento = StatusPagamento.PAGA;
        this.valor = valorFinal;
    }


    public static List<Mensalidade> buscarMensalidadesNaoPagas(List<Mensalidade> mensalidades) {
        List<Mensalidade> mensalidadesNaoPagas = new ArrayList<>();
        for (Mensalidade mensalidade : mensalidades) {
            if (mensalidade.getStatusPagamento() == StatusPagamento.PENDENTE) {
                mensalidadesNaoPagas.add(mensalidade);
            }
        }
        return mensalidadesNaoPagas;
    }

    public String toString(){
        return "Valor: " + this.valor + ", Vencimento: " + this.dataVencimento + ", Status: " + this.statusPagamento;
    }

}
