package br.edu.ifpb.ads.views.aluno;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.exception.AlunoNaoEncontradoException;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import net.miginfocom.swing.MigLayout;

/**
 * @author dmviana
 */
public class DetalhesAlunoGUI extends JanelaPadrao {

    public DetalhesAlunoGUI(AlunoDTO aluno, String titulo) {
        super(titulo);
        this.aluno = aluno;
        alunoController = new AlunoController();
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        painelInfoAluno = new JPanel();
        lblTituloInfo = new JLabel();
        lblNome = new JLabel();
        lblEmail = new JLabel();
        lblNomeRecuperado = new JLabel();
        lblEmailRecuperado = new JLabel();
        lblTelefone = new JLabel();
        lblMatricula = new JLabel();
        lblTelefoneRecuperado = new JLabel();
        lblMatriculaRecuperada = new JLabel();
        lblAtivo = new JLabel();
        lblInadimplente = new JLabel();
        lblAtivoRecuperado = new JLabel();
        lblInadimplenteRecuperado = new JLabel();
        painelPagamentos = new JPanel();
        lblTituloPagamentos = new JLabel();
        btnPagarMensalidade = new JButton();
        btnCancelarMatricula = new JButton();

        // ======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        // ======== panel1 ========
        {
            painelInfoAluno.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM,
                            new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt.Color.red),
                    painelInfoAluno.getBorder()));
            painelInfoAluno.addPropertyChangeListener(
                    new java.beans.PropertyChangeListener() {
                        @Override
                        public void propertyChange(java.beans.PropertyChangeEvent e) {
                            if ("bord\u0065r"
                                    .equals(e.getPropertyName()))
                                throw new RuntimeException();
                        }
                    });
            painelInfoAluno.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]",
                    // rows
                    "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]"));

            // ---- lblTituloInfo ----
            lblTituloInfo.setText("INFORMAÇÕES DO ALUNO");
            lblTituloInfo.setFont(new Font("Roboto", Font.BOLD, 14));
            painelInfoAluno.add(lblTituloInfo, "cell 3 0");

            // ---- lblNome ----
            lblNome.setText("Nome : ");
            painelInfoAluno.add(lblNome, "cell 0 2");

            // ---- lblEmail ----
            lblEmail.setText("Email:");
            painelInfoAluno.add(lblEmail, "cell 4 2");

            // ---- lblNomeRecuperado ----
            lblNomeRecuperado.setText(aluno.getNome());
            lblNomeRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
            painelInfoAluno.add(lblNomeRecuperado, "cell 0 3 3 1");

            // ---- lblEmailRecuperado ----
            lblEmailRecuperado.setText(aluno.getEmail());
            lblEmailRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
            painelInfoAluno.add(lblEmailRecuperado, "cell 4 3");

            // ---- lblTelefone ----
            lblTelefone.setText("Telefone:");
            painelInfoAluno.add(lblTelefone, "cell 0 4");

            // ---- lblMatricula ----
            lblMatricula.setText("Matrícula:");
            painelInfoAluno.add(lblMatricula, "cell 4 4");

            // ---- lblTelefoneRecuperado ----
            lblTelefoneRecuperado.setText(aluno.getTelefone());
            lblTelefoneRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
            painelInfoAluno.add(lblTelefoneRecuperado, "cell 0 5 3 1");

            // ---- lblMatriculaRecuperada ----
            lblMatriculaRecuperada.setText(aluno.getMatricula());
            lblMatriculaRecuperada.setFont(new Font("Roboto", Font.BOLD, 14));
            painelInfoAluno.add(lblMatriculaRecuperada, "cell 4 5");

            // ---- lblAtivo ----
            lblAtivo.setText("Ativo:");
            painelInfoAluno.add(lblAtivo, "cell 0 6");

            // ---- lblInadimplente ----
            lblInadimplente.setText("Inadimplente:");
            painelInfoAluno.add(lblInadimplente, "cell 4 6");

            // ---- lblAtivoRecuperado ----
            lblAtivoRecuperado.setText(aluno.isAtivo() ? "Sim" : "Não");
            lblAtivoRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
            painelInfoAluno.add(lblAtivoRecuperado, "cell 0 7");

            // ---- lblInadimplenteRecuperado ----
            lblInadimplenteRecuperado.setText(aluno.isInadimplente() ? "Sim" : "Não");
            lblInadimplenteRecuperado.setFont(new Font("Roboto", Font.BOLD, 14));
            painelInfoAluno.add(lblInadimplenteRecuperado, "cell 4 7");
        }
        contentPane.add(painelInfoAluno);
        painelInfoAluno.setBounds(new Rectangle(new Point(0, 0), painelInfoAluno.getPreferredSize()));

        // ======== painelPagamentos ========
        {
            painelPagamentos.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]",
                    // rows
                    "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]"));

            // ---- lblTituloPagamentos ----
            lblTituloPagamentos.setText("GERENCIAR PAGAMENTOS");
            lblTituloPagamentos.setFont(new Font("Roboto", Font.BOLD, 14));
            painelPagamentos.add(lblTituloPagamentos, "cell 2 0");

            // ---- btnPagarMensalidade ----
            btnPagarMensalidade.setText("Pagar mensalidade");
            painelPagamentos.add(btnPagarMensalidade, "cell 2 1");

            // ---- btnCancelarMatricula ----
            btnCancelarMatricula.setText("Cancelar matrícula");
            btnCancelarMatricula.addActionListener(new OuvinteBotaoCancelarMatricula());
            painelPagamentos.add(btnCancelarMatricula, "cell 2 2");
        }
        contentPane.add(painelPagamentos);
        painelPagamentos.setBounds(new Rectangle(new Point(450, 5), painelPagamentos.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
    }

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
    private AlunoDTO aluno;
    private AlunoController alunoController;

    private class OuvinteBotaoCancelarMatricula implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            aluno.setAtivo(false);
            try {
                alunoController.atualizarAluno(aluno.getMatricula(), aluno);
                JOptionPane.showMessageDialog(null, "Matricula cancelada com sucesso!");
            } catch (AlunoNaoEncontradoException ex){
                JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
            }
            dispose();
        }

    }

}
