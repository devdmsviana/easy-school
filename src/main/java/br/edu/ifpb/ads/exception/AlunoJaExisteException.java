package br.edu.ifpb.ads.exception;

public class AlunoJaExisteException extends  RuntimeException {

    public AlunoJaExisteException(String mensagem) {
        super(mensagem);
    }

}
