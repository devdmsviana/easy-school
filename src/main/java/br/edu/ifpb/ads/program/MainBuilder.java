package br.edu.ifpb.ads.program;

import br.edu.ifpb.ads.builder.Diretor;
import br.edu.ifpb.ads.builder.RelatorioFinanceiro;
import br.edu.ifpb.ads.controller.AlunoController;
import com.itextpdf.layout.Document;

import javax.swing.*;
import java.io.FileNotFoundException;

public class MainBuilder {

    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();
        Object[] options = {"Relatório Financeiro", "Criar Aluno", "Cancelar"};

        int selectedOption = JOptionPane.showOptionDialog(
                null,
                "Escolha uma opção:",
                "Lista de Relatorios",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (selectedOption == 0) {
            try {
                Diretor diretor = new Diretor();
                RelatorioFinanceiro relatorioFinanceiro = new RelatorioFinanceiro(alunoController);
                Document document = diretor.construct(relatorioFinanceiro);
                document.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (selectedOption == 1) {
            String nome = JOptionPane.showInputDialog("Nome: ");

            JOptionPane.showMessageDialog(null, nome);
        }
    }
}
