package br.edu.ifpb.ads.observer;

import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.model.Aluno;

public interface Observador {
    void notificar(AlunoDTO aluno);
}
