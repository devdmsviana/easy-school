package br.edu.ifpb.ads.views.aluno;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.modelmapper.ModelMapper;

import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.email.EmailServico;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;
import br.edu.ifpb.ads.model.Aluno;
import br.edu.ifpb.ads.utils.components.JButtonPadrao;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import br.edu.ifpb.ads.views.mensalidade.MensalidadeGUI;
import net.miginfocom.swing.MigLayout;

public class DetalhesAlunoGUI extends JanelaPadrao {

    private JPanel painelInfoAluno;
    private JLabel lblTituloInfo;
    private JLabel lblNome;
    private JLabel lblEmail;
    private JLabel lblNomeRecuperado;
    private JLabel lblEmailRecuperado;
    private JLabel lblTelefone;
    private JLabel lblMatricula;
    private JLabel lblTelefoneRecuperado;
    private JLabel lblMatriculaRecuperada;
    private JLabel lblAtivo;
    private JLabel lblInadimplente;
    private JLabel lblAtivoRecuperado;
    private JLabel lblInadimplenteRecuperado;
    private JPanel painelPagamentos;
    private JLabel lblTituloPagamentos;
    private JButton btnPagarMensalidade;
    private JButton btnCancelarMatricula;
    private JButton btnTornarInadimplente;
    private AlunoDTO aluno;
    private AlunoController alunoController;
    private ModelMapper modelMapper;

    public DetalhesAlunoGUI(AlunoDTO aluno, String titulo) {
        super(titulo);
        this.aluno = aluno;
        this.alunoController = new AlunoController();
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        Container painelConteudo = getContentPane();
        painelConteudo.setLayout(new BorderLayout());

        painelInfoAluno = criarPainelInfo();
        painelConteudo.add(painelInfoAluno, BorderLayout.WEST);

        painelPagamentos = criarPainelPagamento();
        painelConteudo.add(painelPagamentos, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(getOwner());
    }

    private JPanel criarPainelInfo() {
        JPanel painelInfo = new JPanel(new MigLayout("hidemode 3", "[fill][fill][fill][fill][fill][fill]",
                "[][][][][][][][][]"));

        lblTituloInfo = new JLabel("INFORMAÇÕES DO ALUNO");
        lblTituloInfo.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblTituloInfo, "cell 3 0");

        lblNome = new JLabel("Nome:");
        painelInfo.add(lblNome, "cell 0 2");

        lblEmail = new JLabel("Email:");
        painelInfo.add(lblEmail, "cell 4 2");

        lblNomeRecuperado = new JLabel(aluno.getNome());
        lblNomeRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblNomeRecuperado, "cell 0 3 3 1");

        lblEmailRecuperado = new JLabel(aluno.getEmail());
        lblEmailRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblEmailRecuperado, "cell 4 3");

        lblTelefone = new JLabel("Telefone:");
        painelInfo.add(lblTelefone, "cell 0 4");

        lblMatricula = new JLabel("Matrícula:");
        painelInfo.add(lblMatricula, "cell 4 4");

        lblTelefoneRecuperado = new JLabel(aluno.getTelefone());
        lblTelefoneRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblTelefoneRecuperado, "cell 0 5 3 1");

        lblMatriculaRecuperada = new JLabel(aluno.getMatricula());
        lblMatriculaRecuperada.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblMatriculaRecuperada, "cell 4 5");

        lblAtivo = new JLabel("Ativo:");
        painelInfo.add(lblAtivo, "cell 0 6");

        lblInadimplente = new JLabel("Inadimplente:");
        painelInfo.add(lblInadimplente, "cell 4 6");

        lblAtivoRecuperado = new JLabel(aluno.isAtivo() ? "Sim" : "Não");
        lblAtivoRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblAtivoRecuperado, "cell 0 7");

        lblInadimplenteRecuperado = new JLabel(aluno.isInadimplente() ? "Sim" : "Não");
        lblInadimplenteRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblInadimplenteRecuperado, "cell 4 7");

        return painelInfo;
    }

    private JPanel criarPainelPagamento() {
        JPanel painelPagamento = new JPanel(new MigLayout("hidemode 3", "[fill][fill][fill][fill][fill]",
                "[][][][][][][]"));

        lblTituloPagamentos = new JLabel("GERENCIAR PAGAMENTOS");
        lblTituloPagamentos.setFont(new Font("Roboto", Font.BOLD, 14));
        painelPagamento.add(lblTituloPagamentos, "cell 2 0");

        btnPagarMensalidade = new JButtonPadrao("Pagar mensalidade");
        btnPagarMensalidade.addActionListener(new OuvinteBotaoPagarMensalidade());
        painelPagamento.add(btnPagarMensalidade, "cell 2 1");

        btnCancelarMatricula = new JButtonPadrao("Cancelar matrícula");
        btnCancelarMatricula.addActionListener(new OuvinteBotaoCancelarMatricula());
        painelPagamento.add(btnCancelarMatricula, "cell 2 2");

        btnTornarInadimplente = new JButtonPadrao("Tornar inadimplente");
        btnTornarInadimplente.addActionListener(new OuvinteBotaoTornarInadimplente());
        painelPagamento.add(btnTornarInadimplente, "cell 2 3");

        return painelPagamento;
    }

    private class OuvinteBotaoTornarInadimplente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                aluno.setInadimplente(true);
                alunoController.atualizarAluno(aluno.getMatricula(), aluno);
                JOptionPane.showMessageDialog(null, "Aluno notificado via e-mail!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar status do aluno: " + ex.getMessage());
            } finally {
                dispose();
            }
        }
    }

    private class OuvinteBotaoPagarMensalidade implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MensalidadeGUI janela = new MensalidadeGUI(alunoController, aluno, "Easy School - Pagar Mensalidade");
            janela.setVisible(true);

        }

    }

    private class OuvinteBotaoCancelarMatricula implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            aluno.setAtivo(false);
            try {
                alunoController.atualizarAluno(aluno.getMatricula(), aluno);
                JOptionPane.showMessageDialog(null, "Matrícula cancelada com sucesso!");
            } catch (AlunoNaoEncontradoException ex) {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
            }
            dispose();
        }

    }
}
