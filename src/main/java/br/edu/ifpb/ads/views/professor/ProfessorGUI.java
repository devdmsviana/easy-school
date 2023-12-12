package br.edu.ifpb.ads.views.professor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.ifpb.ads.controller.ProfessorController;
import br.edu.ifpb.ads.dto.ProfessorDTO;
import br.edu.ifpb.ads.exception.ProfessorNaoEncontradoException;
import br.edu.ifpb.ads.utils.Imagens;
import br.edu.ifpb.ads.utils.components.JButtonPadrao;
import br.edu.ifpb.ads.utils.components.JButtonVoltar;
import br.edu.ifpb.ads.utils.components.JLabelTitulo;
import br.edu.ifpb.ads.utils.components.JTextFieldPadrao;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import br.edu.ifpb.ads.views.inicio.InicioGUI;

public class ProfessorGUI extends JanelaPadrao {

    private JCheckBox filtroProfessorsAtivos;
    private JTable tabelaProfessors;
    private JScrollPane painelTabela;
    private DefaultTableModel modeloTabela;
    private JPanel conteudoPainel;

    private ProfessorController ProfessorController;

    public ProfessorGUI() {
        super("Easy School - Professores");
        this.ProfessorController = new ProfessorController();
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        conteudoPainel = new JPanel();
        setContentPane(conteudoPainel);
        conteudoPainel.setLayout(new BorderLayout());
        adicionarImagens();
        adicionarButtons();
        adicionarTabela();
        adicionarLabels();
    }

    private DefaultTableModel getModeloTabela() {
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("id");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Matrícula");
        modeloTabela.addColumn("E-mail");
        modeloTabela.addColumn("Data Nascimento");
        modeloTabela.addColumn("Telefone");
        modeloTabela.addColumn("Turno");
        return modeloTabela;

    }

    private void adicionarProfessorNaTabela(ProfessorDTO Professor) {
        Object[] linha = new Object[7];
        linha[0] = Professor.id;
        linha[1] = Professor.nome;
        linha[2] = Professor.getMatricula();
        linha[3] = Professor.email;
        linha[4] = Professor.dataNascimento;
        linha[5] = Professor.telefone;
        linha[6] = Professor.turno;
        modeloTabela.addRow(linha);
    }

    private void adicionarTabela() {
        modeloTabela = getModeloTabela();
        List<ProfessorDTO> listaDeProfessors = ProfessorController.listarProfessores();
        for (ProfessorDTO Professor : listaDeProfessors) {
            adicionarProfessorNaTabela(Professor);
        }
        tabelaProfessors = new JTable(modeloTabela);
        tabelaProfessors.setAutoscrolls(true);
        painelTabela = new JScrollPane(tabelaProfessors);
        conteudoPainel.add(painelTabela, BorderLayout.CENTER);
    }

    private void adicionarImagens() {
        JLabel lblProfessorFlat = new JLabel(Imagens.ALUNO_FLAT);
        conteudoPainel.add(lblProfessorFlat, BorderLayout.WEST);
    }

    private void adicionarLabels() {

        JLabel lblTitulo = new JLabelTitulo("Professores");
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        conteudoPainel.add(lblTitulo, BorderLayout.NORTH);
    }

    private void adicionarButtons() {
        JButtonVoltar btnVoltar = new JButtonVoltar();
        btnVoltar.setBounds(10, 10, 50, 50);
        btnVoltar.addActionListener(e -> {
            dispose();
            new InicioGUI().setVisible(true);
        });
        add(btnVoltar);

        JPanel painelBotoes = new JPanel();
        JButton btnCadastrarProfessor = new JButtonPadrao("Adicionar Professor");
        btnCadastrarProfessor.setIcon(Imagens.ADICIONAR);
        btnCadastrarProfessor.addActionListener(new OuvinteBotaoCadastrarProfessor(this));

        JButton btnAtualizarProfessor = new JButtonPadrao("Editar Professor");
        btnAtualizarProfessor.setIcon(Imagens.EDITAR);
        btnAtualizarProfessor.addActionListener(new OuvinteBotaoEditarProfessor(this));

        JButton btnRemoverProfessor = new JButtonPadrao("Remover Professor");
        btnRemoverProfessor.addActionListener(new OuvinteBotaoRemoverProfessor());
        btnRemoverProfessor.setIcon(Imagens.DELETAR);

        JTextFieldPadrao txtBuscar = new JTextFieldPadrao("Buscar Professor");
        JLabel lblPesquisar = new JLabel(Imagens.PESQUISAR);
        filtroProfessorsAtivos = new JCheckBox("Mostrar apenas Professores ativos");
        filtroProfessorsAtivos.addActionListener(new OuvinteFiltroCheckBox());

        painelBotoes.add(filtroProfessorsAtivos);
        painelBotoes.add(lblPesquisar);
        painelBotoes.add(txtBuscar);
        painelBotoes.add(btnCadastrarProfessor);
        painelBotoes.add(btnAtualizarProfessor);
        painelBotoes.add(btnRemoverProfessor);
        conteudoPainel.add(painelBotoes, BorderLayout.SOUTH);
    }

    private class OuvinteBotaoCadastrarProfessor implements ActionListener {

        private JFrame janela;

        public OuvinteBotaoCadastrarProfessor(JFrame janela) {
            this.janela = janela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            janela.dispose();
            new ProfessorFormGUI().setVisible(true);

        }

    }

    private class OuvinteBotaoEditarProfessor implements ActionListener {

        private JFrame janela;

        public OuvinteBotaoEditarProfessor(JFrame janela) {
            this.janela = janela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tabelaProfessors.getSelectedRow() != -1) {
                int linhaSelecionada = tabelaProfessors.getSelectedRow();
                long matricula = (long) modeloTabela.getValueAt(linhaSelecionada, 0);
                ProfessorDTO ProfessorSelecionado = ProfessorController.buscarProfessor(matricula);
                new DetalhesProfessorGUI(ProfessorSelecionado, "Easy School - Detalhes Professor").setVisible(true);
            } else {
                JOptionPane.showMessageDialog(janela, "Selecione um Professor para editar", "Aviso!",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private class OuvinteFiltroCheckBox implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            modeloTabela.setRowCount(0);
            List<ProfessorDTO> listaDeProfessors = ProfessorController.listarProfessores();

            for (ProfessorDTO Professor : listaDeProfessors) {
                if (!filtroProfessorsAtivos.isSelected() || Professor.ativo) {
                    adicionarProfessorNaTabela(Professor);
                }
            }

            tabelaProfessors.repaint();
        }
    }

    private class OuvinteBotaoRemoverProfessor implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tabelaProfessors.getSelectedRow() != -1) {
                int linhaSelecionada = tabelaProfessors.getSelectedRow();
                long id = (long) modeloTabela.getValueAt(linhaSelecionada, 0);
                ProfessorDTO ProfessorSelecionado = ProfessorController.buscarProfessor(id);
                try {
                    ProfessorController.removerProfessor(ProfessorSelecionado.id);
                    JOptionPane.showMessageDialog(null, "Professor removido com sucesso!");

                    modeloTabela.removeRow(linhaSelecionada);

                    modeloTabela.fireTableDataChanged();
                } catch (ProfessorNaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, "Professor não encontrado!");
                }
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Professor para remover", "Aviso!",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
