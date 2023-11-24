package br.edu.ifpb.ads;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import br.edu.ifpb.ads.builder.RelatorioFinanceiroBuilder;
import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.model.Aluno;

public class MainRelatorio {

    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();
       
         
        Aluno aluno = new Aluno();
        aluno.setNome("Diogo");
        aluno.setMatricula("123456");
        aluno.setEmail("marcello.razer@gmail.com");
        aluno.setTelefone("83996586204");

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Gilberto");
        aluno2.setMatricula("123456789");
        aluno2.setEmail("gil@gmail.com");
        aluno2.setTelefone("83996586205");
        aluno2.setAtivo(false);

        alunoController.salvarAluno(aluno);
        alunoController.salvarAluno(aluno2); 
        
        try {
            RelatorioFinanceiroBuilder relatorioBuilder = new RelatorioFinanceiroBuilder(alunoController);
            relatorioBuilder.addCabecalho()
                            .addAluno()
                            .addFinanceiro()
                            .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
