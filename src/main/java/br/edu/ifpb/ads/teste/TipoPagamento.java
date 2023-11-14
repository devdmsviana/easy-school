package br.edu.ifpb.ads.payments;

public enum TipoPagamento {

    PIX("Pix") {

        @Override
        public FormaPagamentoStrategy obterFormaPagamento() {
           return new PagamentoPix();
        }
    },
    DINHEIRO("Dinheiro") {

        @Override
        public FormaPagamentoStrategy obterFormaPagamento() {
            return new PagamentoDinheiro();
        }
    },
    CARTAO("Cartão") {
        
        @Override
        public FormaPagamentoStrategy obterFormaPagamento() {
          return new PagamentoCartao();
        }
    };

    private String descricao;

    TipoPagamento(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public abstract FormaPagamentoStrategy obterFormaPagamento();

}