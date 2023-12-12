package br.edu.ifpb.ads.views.professor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import br.edu.ifpb.ads.dto.ProfessorDTO;
import org.modelmapper.ModelMapper;

import br.edu.ifpb.ads.controller.ProfessorController;
import br.edu.ifpb.ads.dto.ProfessorDTO;
import br.edu.ifpb.ads.email.EmailServico;
import br.edu.ifpb.ads.exception.ProfessorNaoEncontradoException;
import br.edu.ifpb.ads.model.Professor;
import br.edu.ifpb.ads.utils.components.JButtonPadrao;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import br.edu.ifpb.ads.views.mensalidade.MensalidadeGUI;
import net.miginfocom.swing.MigLayout;

public class DetalhesProfessorGUI extends JanelaPadrao {

    private JPanel painelInfoProfessor;
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
    private ProfessorDTO Professor;
    private ProfessorController ProfessorController;
    private ModelMapper modelMapper;

    public DetalhesProfessorGUI(ProfessorDTO Professor, String titulo) {
        super(titulo);
        this.Professor = Professor;
        this.ProfessorController = new ProfessorController();
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        Container painelConteudo = getContentPane();
        painelConteudo.setLayout(new BorderLayout());

        painelInfoProfessor = criarPainelInfo();
        painelConteudo.add(painelInfoProfessor, BorderLayout.WEST);

        pack();
        setLocationRelativeTo(getOwner());
    }

    private JPanel criarPainelInfo() {
        JPanel painelInfo = new JPanel(new MigLayout("hidemode 3", "[fill][fill][fill][fill][fill][fill]",
                "[][][][][][][][][]"));

        lblTituloInfo = new JLabel("INFORMAÇÕES DO Professor");
        lblTituloInfo.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblTituloInfo, "cell 3 0");

        lblNome = new JLabel("Nome:");
        painelInfo.add(lblNome, "cell 0 2");

        lblEmail = new JLabel("Email:");
        painelInfo.add(lblEmail, "cell 4 2");

        lblNomeRecuperado = new JLabel(Professor.nome);
        lblNomeRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblNomeRecuperado, "cell 0 3 3 1");

        lblEmailRecuperado = new JLabel(Professor.email);
        lblEmailRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblEmailRecuperado, "cell 4 3");

        lblTelefone = new JLabel("Telefone:");
        painelInfo.add(lblTelefone, "cell 0 4");

        lblMatricula = new JLabel("Matrícula:");
        painelInfo.add(lblMatricula, "cell 4 4");

        lblTelefoneRecuperado = new JLabel(Professor.telefone);
        lblTelefoneRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblTelefoneRecuperado, "cell 0 5 3 1");

        lblMatriculaRecuperada = new JLabel(Professor.getMatricula());
        lblMatriculaRecuperada.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblMatriculaRecuperada, "cell 4 5");

        lblAtivo = new JLabel("Ativo:");
        painelInfo.add(lblAtivo, "cell 0 6");

        lblAtivoRecuperado = new JLabel(Professor.ativo ? "Sim" : "Não");
        lblAtivoRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
        painelInfo.add(lblAtivoRecuperado, "cell 0 7");

        return painelInfo;
    }

    private class OuvinteBotaoCancelarMatricula implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Professor.ativo = false;
            try {
                ProfessorController.atualizarProfessor(Professor);
                JOptionPane.showMessageDialog(null, "Matrícula cancelada com sucesso!");
            } catch (ProfessorNaoEncontradoException ex) {
                JOptionPane.showMessageDialog(null, "Professor não encontrado!");
            }
            dispose();
        }

    }
}
