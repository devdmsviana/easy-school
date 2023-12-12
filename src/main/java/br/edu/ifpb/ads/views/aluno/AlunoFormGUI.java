package br.edu.ifpb.ads.views.aluno;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.model.Endereco;
import br.edu.ifpb.ads.utils.components.JButtonVoltar;
import br.edu.ifpb.ads.views.ouvintes.OuvinteBotaoVoltar;
import net.miginfocom.swing.MigLayout;

public class AlunoFormGUI extends JFrame {

	private AlunoController alunoController;

	public AlunoFormGUI() {
		initComponents();
		setSize(new Dimension(1200, 710));
		setLocationRelativeTo(null);
		alunoController = new AlunoController();
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

			// --- btnVoltar ---
			btnVoltar.addActionListener(new OuvinteBotaoVoltar(this, 0));
			painelComponentes.add(btnVoltar, "cell -10 -10");

			// ---- lblTituloAluno ----
			lblTituloAluno.setText("Informações do Aluno");
			lblTituloAluno.setFont(new Font("Poppins", Font.BOLD, 14));
			painelComponentes.add(lblTituloAluno, "cell 0 0");
			painelComponentes.add(separadorAluno, "cell 0 1 7 2,dock center");

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

			// ---- lblNomeResponsavel ----
			lblNomeResponsavel.setText("Nome Responsável:");
			painelComponentes.add(lblNomeResponsavel, "cell 0 12");
			painelComponentes.add(txtNomeResponsavel, "cell 0 13 4 1");

			// ---- chMaiorIdade ----
			chMaiorIdade.setText("Maior de idade?");
			chMaiorIdade.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					boolean isMaior = chMaiorIdade.isSelected();
					txtNomeResponsavel.setEnabled(!isMaior);
					txtTelefoneResponsavel.setEnabled(!isMaior);

				}

			});
			painelComponentes.add(chMaiorIdade, "cell 5 13");

			// ---- lblTelefoneResponsavel ----
			lblTelefoneResponsavel.setText("Telefone Responsável:");
			painelComponentes.add(lblTelefoneResponsavel, "cell 0 14");

			try {
				txtTelefoneResponsavel = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}

			painelComponentes.add(txtTelefoneResponsavel, "cell 0 15");

			// ---- lblTurno ----
			lblTurno.setText("Turno:");
			painelComponentes.add(lblTurno, "cell 0 16");

			// ---- lblNivel ----
			lblNivel.setText("Nível:");
			painelComponentes.add(lblNivel, "cell 5 16");
			cbTurno = new JComboBox<>(new String[] { "Manhã", "Tarde", "Noite" });
			cbNivel = new JComboBox<>(new String[] { "Básico", "Intermediário", "Avançado" });
			painelComponentes.add(cbTurno, "cell 0 17");
			painelComponentes.add(cbNivel, "cell 5 17");

			// ---- lblDataNascimento ----
			lblDataNascimento.setText("Data Nascimento:");
			painelComponentes.add(lblDataNascimento, "cell 0 18");

			try {
				txtDataNascimento = new JFormattedTextField(new MaskFormatter("####-##-##"));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			painelComponentes.add(txtDataNascimento, "cell 0 19 4 1");

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

			// ---- btnSalvar ----
			btnSalvar.setText("Cadastrar");
			painelComponentes.add(btnSalvar, "cell 0 30 7 1,alignx center,growx 0");
			btnSalvar.addActionListener(new OuvinteBotaoCadastrar());

			
		}
		contentPane.add(painelComponentes, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 5), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Diogo Marcelo
	private JPanel painelConteudo;
	private JPanel painelComponentes;
	private JLabel lblTituloAluno;
	private JSeparator separadorAluno;
	private JLabel lblMatricula;
	private JTextField txtMatricula;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblTelefoneAluno;
	private JFormattedTextField txtTelefoneAluno;
	private JLabel lblNomeResponsavel;
	private JTextField txtNomeResponsavel;
	private JCheckBox chMaiorIdade;
	private JLabel lblTelefoneResponsavel;
	private JFormattedTextField txtTelefoneResponsavel;
	private JLabel lblTurno;
	private JLabel lblNivel;
	private JComboBox<String> cbTurno;
	private JComboBox<String> cbNivel;
	private JLabel lblDataNascimento;
	private JFormattedTextField txtDataNascimento;
	private JLabel lblTituloEndereco;
	private JSeparator separadorEndereco;
	private JLabel lblRua;
	private JLabel lblCidade;
	private JTextField txtRua;
	private JTextField txtCidade;
	private JLabel lblBairro;
	private JLabel lblEstado;
	private JTextField txtBairro;
	private JTextField txtEstado;
	private JLabel lblCep;
	private JLabel lblNumero;
	private JTextField txtCep;
	private JTextField txtNumero;
	private JButton btnSalvar;
	private JButtonVoltar btnVoltar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

	private class OuvinteBotaoCadastrar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String nome = txtNome.getText();
			String matricula = txtMatricula.getText();
			String nomeResponsavel = txtNomeResponsavel.getText();
			String telefoneResponsavel = txtTelefoneResponsavel.getText();
			String telefoneAluno = txtTelefoneAluno.getText();
			String email = txtEmail.getText();
			LocalDate dataNascimento = LocalDate.parse(txtDataNascimento.getText());
			String turno = cbTurno.getSelectedItem().toString();
			String nivel = cbNivel.getSelectedItem().toString();
			LocalDate dataMatricula = LocalDate.now(); // Ajustar para capturar a data do componente
			String rua = txtRua.getText();
			String cidade = txtCidade.getText();
			String bairro = txtBairro.getText();
			String estado = txtEstado.getText();
			String cep = txtCep.getText();
			String numero = txtNumero.getText();

			AlunoDTO alunoDTO = new AlunoDTO();
			alunoDTO.setNome(nome);
			alunoDTO.setMatricula(matricula);
			alunoDTO.setDataNascimento(dataNascimento);
			alunoDTO.setTelefone(telefoneAluno);
			if (!chMaiorIdade.isSelected()) {
				alunoDTO.setNomeResponsavel(nomeResponsavel);
				alunoDTO.setTelefoneResponsavel(telefoneResponsavel);
			}
			alunoDTO.setTurno(turno);
			alunoDTO.setNivel(nivel);
			alunoDTO.setAtivo(true);
			alunoDTO.setInadimplente(false);
			alunoDTO.setDataMatricula(dataMatricula);
			alunoDTO.setEmail(email);

			Endereco endereco = new Endereco();
			endereco.setRua(rua);
			endereco.setCidade(cidade);
			endereco.setBairro(bairro);
			endereco.setEstado(estado);
			endereco.setCep(cep);
			endereco.setNumero(numero);

			alunoDTO.setEndereco(endereco);

			try {
				alunoController.salvarAluno(alunoDTO);
				JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (AlunoJaExisteException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
