package br.edu.ifpb.ads.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.model.Aluno;
import br.edu.ifpb.ads.model.Endereco;
import net.miginfocom.swing.MigLayout;

public class AlunoFormGUI extends JFrame {

    private AlunoController alunoController;
    private JPanel panel2;
    private JLabel label13;
    private JPanel panel1;
    private JLabel lblTituloAluno, lblMatricula, lblNome, lblNomeResponsavel, lblTurno, lblNivel, lblDataMatricula;
    private JLabel lblTituloEndereco, lblRua, lblCidade, lblBairro, lblEstado, lblCep, lblNumero;
    private JTextField txtMatricula, txtNome, txtNomeResponsavel, txtRua, txtCidade, txtBairro, txtEstado, txtCep, txtNumero;
    private JComboBox<String> cbTurno, cbNivel;
    private JFormattedTextField txtDataMatricula;
    private JButton btnSalvar;

    public AlunoFormGUI() {
        alunoController = new AlunoController();
        initComponents();
    }

    private void initComponents() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        panel2 = new JPanel(new BorderLayout());
		label13 = new JLabel("Teste");
        panel2.add(label13, BorderLayout.CENTER);

        panel1 = new JPanel();
        panel1.setBorder(null);
        panel1.setLayout(new MigLayout(
                "fill,hidemode 3,align center center",
                "[fill][fill][fill][fill][fill][fill][fill]",
                "[] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] []"));

        lblTituloAluno = new JLabel("Informações do Aluno");
        lblTituloAluno.setFont(new Font("Poppins", Font.BOLD, 14));
        panel1.add(lblTituloAluno, "cell 0 0");
        panel1.add(new JSeparator(), "cell 0 1 7 2, dock center");

        lblMatricula = new JLabel("Matrícula:");
        txtMatricula = new JTextField();
        panel1.add(lblMatricula, "cell 0 3");
        panel1.add(txtMatricula, "cell 0 4 4 1");

        lblNome = new JLabel("Nome Completo:");
        txtNome = new JTextField();
        panel1.add(lblNome, "cell 0 5");
        panel1.add(txtNome, "cell 0 6 4 1");

        lblNomeResponsavel = new JLabel("Nome Responsável:");
        txtNomeResponsavel = new JTextField();
        panel1.add(lblNomeResponsavel, "cell 0 7");
        panel1.add(txtNomeResponsavel, "cell 0 8 4 1");

        lblTurno = new JLabel("Turno:");
        cbTurno = new JComboBox<>(new String[]{"Manhã", "Tarde", "Noite"});
        panel1.add(lblTurno, "cell 0 9");
        panel1.add(cbTurno, "cell 0 9");

        lblNivel = new JLabel("Nível:");
        cbNivel = new JComboBox<>(new String[]{"Básico", "Intermediário", "Avançado"});
        panel1.add(lblNivel, "cell 5 9");
        panel1.add(cbNivel, "cell 5 9");

        lblDataMatricula = new JLabel("Data Matrícula:");
        txtDataMatricula = new JFormattedTextField();
        panel1.add(lblDataMatricula, "cell 0 10");
        panel1.add(txtDataMatricula, "cell 0 11 4 1");

        lblTituloEndereco = new JLabel("Informações do Endereço");
        lblTituloEndereco.setFont(new Font("Poppins", Font.BOLD, 14));
        panel1.add(lblTituloEndereco, "cell 0 13");
        panel1.add(new JSeparator(), "cell 0 14 7 2");

        lblRua = new JLabel("Rua:");
        txtRua = new JTextField();
        lblCidade = new JLabel("Cidade:");
        txtCidade = new JTextField();
        panel1.add(lblRua, "cell 0 16");
        panel1.add(lblCidade, "cell 5 16");
        panel1.add(txtRua, "cell 0 17");
        panel1.add(txtCidade, "cell 5 17 2 1");

        lblBairro = new JLabel("Bairro:");
        txtBairro = new JTextField();
        lblEstado = new JLabel("Estado:");
        txtEstado = new JTextField();
        panel1.add(lblBairro, "cell 0 18");
        panel1.add(lblEstado, "cell 5 18");
        panel1.add(txtBairro, "cell 0 19");
        panel1.add(txtEstado, "cell 5 19");

        lblCep = new JLabel("CEP:");
        txtCep = new JTextField();
        lblNumero = new JLabel("Número:");
        txtNumero = new JTextField();
        panel1.add(lblCep, "cell 0 20");
        panel1.add(lblNumero, "cell 5 20");
        panel1.add(txtCep, "cell 0 21");
        panel1.add(txtNumero, "cell 5 21");

        btnSalvar = new JButton("Cadastrar");
        panel1.add(btnSalvar, "cell 0 25 7 1, alignx center, growx 0");

        contentPane.add(panel2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        contentPane.add(panel1, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 5), 0, 0));

        pack();
        setLocationRelativeTo(getOwner());

        btnSalvar.addActionListener(new OuvinteBotaoCadastrar());
    }

    private class OuvinteBotaoCadastrar implements ActionListener {



        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nome = txtNome.getText();
                String matricula = txtMatricula.getText();
                String nomeResponsavel = txtNomeResponsavel.getText();
                String turno = cbTurno.getSelectedItem().toString();
                String nivel = cbNivel.getSelectedItem().toString();
                LocalDate dataMatricula = LocalDate.now();  // Ajustar para capturar a data do componente
                String rua = txtRua.getText();
                String cidade = txtCidade.getText();
                String bairro = txtBairro.getText();
                String estado = txtEstado.getText();
                String cep = txtCep.getText();
                String numero = txtNumero.getText();

                Aluno aluno = new Aluno();
                aluno.setNome(nome);
                aluno.setMatricula(matricula);
                aluno.setNomeResponsavel(nomeResponsavel);
                aluno.setTurno(turno);
                aluno.setNivel(nivel);
                aluno.setDataMatricula(dataMatricula);

                Endereco endereco = new Endereco();
                endereco.setRua(rua);
                endereco.setCidade(cidade);
                endereco.setBairro(bairro);
                endereco.setEstado(estado);
                endereco.setCep(cep);
                endereco.setNumero(numero);

                aluno.setEndereco(endereco);

                alunoController.salvarAluno(aluno);
                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (AlunoJaExisteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);;
            }
        }
    }
}