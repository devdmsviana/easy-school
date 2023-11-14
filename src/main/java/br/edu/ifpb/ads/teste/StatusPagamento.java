package br.edu.ifpb.ads.model.enums;

public enum StatusPagamento {
    

    PAGO("Pago"),
    PENDENTE("Pendente"),
    ATRASADO("Atrasado");

    private String status;

    StatusPagamento(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }


}
