package br.edu.ifpb.ads;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.email.EmailServico;
import br.edu.ifpb.ads.model.Aluno;
import br.edu.ifpb.ads.model.Mensalidade;
import br.edu.ifpb.ads.model.enums.StatusPagamento;

public class MainObserver {

    public static void main(String[] args) throws Exception {
        AlunoController alunoController = new AlunoController();
        List<AlunoDTO> alunos = alunoController.listarAlunos();

        EmailServico emailServico = new EmailServico();

        for (int i = 0; i < alunos.size(); i++) {
            AlunoDTO aluno = alunos.get(i);
            List<Mensalidade> mensalidades = aluno.getMensalidades();
            for (int j = 0; j < mensalidades.size(); j++) {
                Mensalidade mensalidade = mensalidades.get(j);
                if (mensalidade.isMensalidadeAtrasada()) {
                    aluno.setInadimplente(true);
                    mensalidade.setStatusPagamento(StatusPagamento.ATRASADA);
                }
            } 
            if(aluno.isInadimplente()){
                emailServico.enviarCobrancaMensalidade(aluno);
            }
        }
    }
}
