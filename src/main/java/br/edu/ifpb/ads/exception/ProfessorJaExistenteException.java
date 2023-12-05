package br.edu.ifpb.ads.exception;

public class ProfessorJaExistenteException extends RuntimeException {
    public ProfessorJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
