package br.edu.ifpb.ads.model.enums;

public enum StatusPagamento {
    

    PAGA("Paga"),
    PENDENTE("Pendente"),
    ATRASADA("Atrasada");

    private String status;

    StatusPagamento(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }


}
