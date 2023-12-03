package br.edu.ifpb.ads.views.aluno;

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

import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;
import br.edu.ifpb.ads.utils.Imagens;
import br.edu.ifpb.ads.utils.components.JButtonPadrao;
import br.edu.ifpb.ads.utils.components.JButtonVoltar;
import br.edu.ifpb.ads.utils.components.JLabelTitulo;
import br.edu.ifpb.ads.utils.components.JTextFieldPadrao;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import br.edu.ifpb.ads.views.inicio.InicioGUI;

public class AlunoGUI extends JanelaPadrao {

    private JCheckBox filtroAlunosAtivos;
    private JTable tabelaAlunos;
    private JScrollPane painelTabela;
    private DefaultTableModel modeloTabela;
    private AlunoController alunoController;
    private JPanel conteudoPainel;

    public AlunoGUI() {
        super("Easy School - Alunos");
        alunoController = new AlunoController();
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
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Matrícula");
        modeloTabela.addColumn("E-mail");
        modeloTabela.addColumn("Data Nascimento");
        modeloTabela.addColumn("Telefone");
        modeloTabela.addColumn("Matrícula");
        modeloTabela.addColumn("Nível");
        modeloTabela.addColumn("Turno");
        return modeloTabela;

    }

    private void adicionarAlunoNaTabela(AlunoDTO aluno) {
        Object[] linha = new Object[8];
        linha[0] = aluno.getNome();
        linha[1] = aluno.getMatricula();
        linha[2] = aluno.getEmail();
        linha[3] = aluno.getDataNascimento();
        linha[4] = aluno.getTelefone();
        linha[5] = aluno.getMatricula();
        linha[6] = aluno.getNivel();
        linha[7] = aluno.getTurno();
        modeloTabela.addRow(linha);
    }

    private void adicionarTabela() {
        modeloTabela = getModeloTabela();
        List<AlunoDTO> listaDeAlunos = alunoController.listarAlunos();
        for (AlunoDTO aluno : listaDeAlunos) {
            adicionarAlunoNaTabela(aluno);
        }
        tabelaAlunos = new JTable(modeloTabela);
        tabelaAlunos.setAutoscrolls(true);
        painelTabela = new JScrollPane(tabelaAlunos);
        conteudoPainel.add(painelTabela, BorderLayout.CENTER);
    }

    private void adicionarImagens() {
        JLabel lblAlunoFlat = new JLabel(Imagens.ALUNO_FLAT);
        conteudoPainel.add(lblAlunoFlat, BorderLayout.WEST);
    }

    private void adicionarLabels() {

        JLabel lblTitulo = new JLabelTitulo("Alunos");
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
        JButton btnCadastrarAluno = new JButtonPadrao("Adicionar Aluno");
        btnCadastrarAluno.setIcon(Imagens.ADICIONAR);
        btnCadastrarAluno.addActionListener(new OuvinteBotaoCadastrarAluno(this));

        JButton btnAtualizarAluno = new JButtonPadrao("Editar Aluno");
        btnAtualizarAluno.setIcon(Imagens.EDITAR);
        btnAtualizarAluno.addActionListener(new OuvinteBotaoEditarAluno(this));

        JButton btnRemoverAluno = new JButtonPadrao("Remover Aluno");
        btnRemoverAluno.addActionListener(new OuvinteBotaoRemoverAluno());
        btnRemoverAluno.setIcon(Imagens.DELETAR);

        JTextFieldPadrao txtBuscar = new JTextFieldPadrao("Buscar Aluno");
        JLabel lblPesquisar = new JLabel(Imagens.PESQUISAR);
        filtroAlunosAtivos = new JCheckBox("Mostrar apenas alunos ativos");
        filtroAlunosAtivos.addActionListener(new OuvinteFiltroCheckBox());

        painelBotoes.add(filtroAlunosAtivos);
        painelBotoes.add(lblPesquisar);
        painelBotoes.add(txtBuscar);
        painelBotoes.add(btnCadastrarAluno);
        painelBotoes.add(btnAtualizarAluno);
        painelBotoes.add(btnRemoverAluno);
        conteudoPainel.add(painelBotoes, BorderLayout.SOUTH);

    }

    private class OuvinteBotaoCadastrarAluno implements ActionListener {

        private JFrame janela;

        public OuvinteBotaoCadastrarAluno(JFrame janela) {
            this.janela = janela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            janela.dispose();
            new AlunoFormGUI().setVisible(true);

        }

    }

    private class OuvinteBotaoEditarAluno implements ActionListener {

        private JFrame janela;

        public OuvinteBotaoEditarAluno(JFrame janela) {
            this.janela = janela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tabelaAlunos.getSelectedRow() != -1) {
                int linhaSelecionada = tabelaAlunos.getSelectedRow();
                String matricula = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
                AlunoDTO alunoSelecionado = alunoController.buscarAlunoPorMatricula(matricula);
                new DetalhesAlunoGUI(alunoSelecionado, "Easy School - Detalhes Aluno").setVisible(true);
            } else {
                JOptionPane.showMessageDialog(janela, "Selecione um aluno para editar", "Aviso!",
                        JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    private class OuvinteFiltroCheckBox implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            modeloTabela.setRowCount(0);
            List<AlunoDTO> listaDeAlunos = alunoController.listarAlunos();

            for (AlunoDTO aluno : listaDeAlunos) {
                if (!filtroAlunosAtivos.isSelected() || aluno.isAtivo()) {
                    adicionarAlunoNaTabela(aluno);
                }
            }

            tabelaAlunos.repaint();
        }
    }

    private class OuvinteBotaoRemoverAluno implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tabelaAlunos.getSelectedRow() != -1) {
                int linhaSelecionada = tabelaAlunos.getSelectedRow();
                String matricula = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
                AlunoDTO alunoSelecionado = alunoController.buscarAlunoPorMatricula(matricula);
                try {
                    alunoController.removerAluno(alunoSelecionado.getMatricula());
                    JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");

                    modeloTabela.removeRow(linhaSelecionada);

                    modeloTabela.fireTableDataChanged();
                } catch (AlunoNaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
                }
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um aluno para remover", "Aviso!",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
