package br.edu.ifpb.ads.views.relatorio;

import br.edu.ifpb.ads.builder.Diretor;
import br.edu.ifpb.ads.builder.RelatorioFinanceiro;
import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.utils.Imagens;
import br.edu.ifpb.ads.utils.components.JButtonPadrao;
import br.edu.ifpb.ads.utils.components.JButtonVoltar;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import br.edu.ifpb.ads.views.aluno.AlunoGUI;
import br.edu.ifpb.ads.views.inicio.InicioGUI;
import br.edu.ifpb.ads.views.ouvintes.OuvinteBotoesTelaInicial;
import br.edu.ifpb.ads.views.professor.ProfessorGUI;
import com.itextpdf.layout.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class RelatorioGUI extends JanelaPadrao {

    public RelatorioGUI() {
        super("Easy School - Relatórios");
        adicionarImagens();
        adicionarButtons();
    }

    private void adicionarImagens() {
        JLabel lblImagemInicio = new JLabel(Imagens.INICIO_FLAT);
        lblImagemInicio.setBounds(5, 35, 368, 368);
        add(lblImagemInicio);
    }

    private void adicionarButtons() {
        JButtonVoltar btnVoltar = new JButtonVoltar();
        btnVoltar.setBounds(10, 10, 50, 50);
        btnVoltar.addActionListener(e -> {
            dispose();
            new InicioGUI().setVisible(true);
        });
        add(btnVoltar);

        JButton btnAlunos = new JButtonPadrao("Relatório Financeiro", 510, 95, 300, 50);
        btnAlunos.addActionListener(new OuvinteGerarRelatorio(TipoRelatorio.FINANCEIRO));
        add(btnAlunos);
    }

    public class OuvinteGerarRelatorio implements ActionListener {

        private TipoRelatorio tipoRelatorio;
        private AlunoController alunoController;
        public OuvinteGerarRelatorio(TipoRelatorio tipoRelatorio) {
            this.tipoRelatorio = tipoRelatorio;
            alunoController = new AlunoController();
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                Diretor diretor = new Diretor();
                switch (tipoRelatorio){
                    case FINANCEIRO:
                        RelatorioFinanceiro relatorioFinanceiro = new RelatorioFinanceiro(alunoController);
                        Document document = diretor.construct(relatorioFinanceiro);
                        document.close();
                        break;
                }

            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(null, fileNotFoundException.getMessage());
            }


        }
    }

    public enum TipoRelatorio {
        FINANCEIRO
    }
}

