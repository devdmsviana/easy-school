package br.edu.ifpb.ads.views.template;

import br.edu.ifpb.ads.controller.ProfessorController;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.model.Endereco;
import br.edu.ifpb.ads.utils.components.JButtonVoltar;
import br.edu.ifpb.ads.views.ouvintes.OuvinteBotaoVoltar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

public class TemplateMethod extends JFrame {

    private ProfessorController professorController;

    public TemplateMethod() {
        initComponents();
        setSize(new Dimension(1200, 710));
        setLocationRelativeTo(null);
        professorController = new ProfessorController();
    }

    private void initComponents() {
        painelConteudo = new JPanel();
        painelComponentes = new JPanel();
        lblTituloAluno = new JLabel();
        separadorAluno = new JSeparator();
        lblMatricula = new JLabel();
        txtMatricula = new JTextField();
        lblNome = new JLabel();
        txtNome = new JTextField();
        lblEmail = new JLabel();
        txtEmail = new JTextField();
        lblTelefoneAluno = new JLabel();
        txtTelefoneAluno = new JFormattedTextField();
        lblNomeResponsavel = new JLabel();
        txtNomeResponsavel = new JTextField();
        chMaiorIdade = new JCheckBox();
        lblTelefoneResponsavel = new JLabel();
        txtTelefoneResponsavel = new JFormattedTextField();
        lblTurno = new JLabel();
        lblNivel = new JLabel();
        cbTurno = new JComboBox<String>();
        cbNivel = new JComboBox<String>();
        lblDataNascimento = new JLabel();
        txtDataNascimento = new JFormattedTextField();
        lblTituloEndereco = new JLabel();
        separadorEndereco = new JSeparator();
        lblRua = new JLabel();
        lblCidade = new JLabel();
        txtRua = new JTextField();
        txtCidade = new JTextField();
        lblBairro = new JLabel();
        lblEstado = new JLabel();
        txtBairro = new JTextField();
        txtEstado = new JTextField();
        lblCep = new JLabel();
        lblNumero = new JLabel();
        txtCep = new JTextField();
        txtNumero = new JTextField();
        btnSalvar = new JButton();
        btnVoltar = new JButtonVoltar();

        // ======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 164 };

        // ======== painelConteudo ========
        {
            painelConteudo.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM,
                            new java.awt.Font("Dialo\u0067", java.awt.Font.BOLD, 12), java.awt.Color.red),
                    painelConteudo.getBorder()));
            painelConteudo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("borde\u0072".equals(e.getPropertyName()))
                        throw new RuntimeException();
                }
            });
            painelConteudo.setLayout(new BorderLayout());
        }
        contentPane.add(painelConteudo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

        // ======== painelComponentes ========
        {
            painelComponentes.setBorder(null);
            painelComponentes.setLayout(new MigLayout(
                    "fill,hidemode 3,align center center",
                    // columns
                    "[fill]" +
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
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]" +
                            "[]"));

            adicionarBotaoVoltar();


            // ---- lblTituloAluno ----
            lblTituloAluno.setText("Informações do Aluno");
            lblTituloAluno.setFont(new Font("Poppins", Font.BOLD, 14));
            painelComponentes.add(lblTituloAluno, "cell 0 0");
            painelComponentes.add(separadorAluno, "cell 0 1 7 2,dock center");

            adicionarCamposBasicos();
            adicionarCamposExtras();

            // ---- lblDataNascimento ----
            lblDataNascimento.setText("Data Nascimento:");
            painelComponentes.add(lblDataNascimento, "cell 0 18");

            try {
                txtDataNascimento = new JFormattedTextField(new MaskFormatter("####-##-##"));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            painelComponentes.add(txtDataNascimento, "cell 0 19 4 1");

            adicionarCamposEndereco();
            adicionarBotaoSalvar();
        }
        contentPane.add(painelComponentes, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void adicionarCamposBasicos() {
        // ---- lblMatricula ----
        lblMatricula.setText("Matrícula:");
        painelComponentes.add(lblMatricula, "cell 0 3");
        painelComponentes.add(txtMatricula, "cell 0 4 4 1");

        // ---- lblNome ----
        lblNome.setText("Nome Completo:");
        painelComponentes.add(lblNome, "cell 0 5");
        painelComponentes.add(txtNome, "cell 0 6 4 1");

        // ---- lblEmail ----
        lblEmail.setText("Email:");
        painelComponentes.add(lblEmail, "cell 0 7");
        painelComponentes.add(txtEmail, "cell 0 8");

        // ---- lblTelefoneAluno ----
        lblTelefoneAluno.setText("Telefone:");
        painelComponentes.add(lblTelefoneAluno, "cell 0 9");

        try {
            txtTelefoneAluno = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        painelComponentes.add(txtTelefoneAluno, "cell 0 10");
    }

    public void adicionarCamposEndereco(){
        // ---- lblTituloEndereco ----
        lblTituloEndereco.setText("Informações Endereço");
        lblTituloEndereco.setFont(new Font("Poppins", Font.BOLD, 14));
        painelComponentes.add(lblTituloEndereco, "cell 0 21");
        painelComponentes.add(separadorEndereco, "cell 0 22 7 2");

        // ---- lblRua ----
        lblRua.setText("Rua:");
        painelComponentes.add(lblRua, "cell 0 24");

        // ---- lblCidade ----
        lblCidade.setText("Cidade:");
        painelComponentes.add(lblCidade, "cell 5 24");
        painelComponentes.add(txtRua, "cell 0 25");
        painelComponentes.add(txtCidade, "cell 5 25 2 1");

        // ---- lblBairro ----
        lblBairro.setText("Bairro:");
        painelComponentes.add(lblBairro, "cell 0 26");

        // ---- lblEstado ----
        lblEstado.setText("Estado:");
        painelComponentes.add(lblEstado, "cell 5 26");
        painelComponentes.add(txtBairro, "cell 0 27");
        painelComponentes.add(txtEstado, "cell 5 27");

        // ---- lblCep ----
        lblCep.setText("CEP:");
        painelComponentes.add(lblCep, "cell 0 28");

        // ---- lblNumero ----
        lblNumero.setText("Número:");
        painelComponentes.add(lblNumero, "cell 5 28");
        painelComponentes.add(txtCep, "cell 0 29");
        painelComponentes.add(txtNumero, "cell 5 29");
    }

    public void adicionarCamposExtras() {

    }

    public void adicionarBotaoVoltar() {

    }

    public void adicionarBotaoSalvar() {

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Diogo Marcelo
    public JPanel painelConteudo;
    public JPanel painelComponentes;
    public JLabel lblTituloAluno;
    public JSeparator separadorAluno;
    public JLabel lblMatricula;
    public JTextField txtMatricula;
    public JLabel lblNome;
    public JTextField txtNome;
    public JLabel lblEmail;
    public JTextField txtEmail;
    public JLabel lblTelefoneAluno;
    public JFormattedTextField txtTelefoneAluno;
    public JLabel lblNomeResponsavel;
    public JTextField txtNomeResponsavel;
    public JCheckBox chMaiorIdade;
    public JLabel lblTelefoneResponsavel;
    public JFormattedTextField txtTelefoneResponsavel;
    public JLabel lblTurno;
    public JLabel lblNivel;
    public JComboBox<String> cbTurno;
    public JComboBox<String> cbNivel;
    public JLabel lblDataNascimento;
    public JFormattedTextField txtDataNascimento;
    public JLabel lblTituloEndereco;
    public JSeparator separadorEndereco;
    public JLabel lblRua;
    public JLabel lblCidade;
    public JTextField txtRua;
    public JTextField txtCidade;
    public JLabel lblBairro;
    public JLabel lblEstado;
    public JTextField txtBairro;
    public JTextField txtEstado;
    public JLabel lblCep;
    public JLabel lblNumero;
    public JTextField txtCep;
    public JTextField txtNumero;
    public JButton btnSalvar;
    public JButtonVoltar btnVoltar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
