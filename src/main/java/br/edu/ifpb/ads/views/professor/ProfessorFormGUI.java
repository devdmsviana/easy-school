package br.edu.ifpb.ads.views.professor;

import br.edu.ifpb.ads.controller.ProfessorController;
import br.edu.ifpb.ads.dto.ProfessorDTO;
import br.edu.ifpb.ads.exception.AlunoJaExisteException;
import br.edu.ifpb.ads.model.Endereco;
import br.edu.ifpb.ads.views.ouvintes.OuvinteBotaoVoltar;
import br.edu.ifpb.ads.views.template.TemplateMethod;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProfessorFormGUI extends TemplateMethod {

    private ProfessorController professorController;
    private JFrame jFrame;
    public ProfessorFormGUI() {
        this.professorController = new ProfessorController();
        jFrame = this;
    }

    @Override
    public void adicionarBotaoVoltar() {
        // --- btnVoltar ---
        btnVoltar.addActionListener(new OuvinteBotaoVoltar(this, 3));
        painelComponentes.add(btnVoltar, "cell -10 -10");
    }

    @Override
    public void adicionarBotaoSalvar() {
        // ---- btnSalvar ----
        btnSalvar.setText("Cadastrar");
        painelComponentes.add(btnSalvar, "cell 0 30 7 1,alignx center,growx 0");
        btnSalvar.addActionListener(new ProfessorFormGUI.OuvinteBotaoCadastrar());
    }

    @Override
    public void adicionarTitulo() {
        lblTituloAluno.setText("Informações do Professor");
        lblTituloAluno.setFont(new Font("Poppins", Font.BOLD, 14));
    }

    private class OuvinteBotaoCadastrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String nome = txtNome.getText();
            String matricula = txtMatricula.getText();
            String telefone = txtTelefoneAluno.getText();
            String email = txtEmail.getText();
            LocalDate dataNascimento = LocalDate.parse(txtDataNascimento.getText());
            LocalDate dataMatricula = LocalDate.now(); // Ajustar para capturar a data do componente
            String rua = txtRua.getText();
            String cidade = txtCidade.getText();
            String bairro = txtBairro.getText();
            String estado = txtEstado.getText();
            String cep = txtCep.getText();
            String numero = txtNumero.getText();

            ProfessorDTO professorDTO = new ProfessorDTO(
                    nome,
                    email,
                    dataNascimento,
                    telefone,
                    matricula,
                    new BigDecimal(3000),
                    null,
                    null,
                    dataMatricula,
                    true,
                    null);

            Endereco endereco = new Endereco();
            endereco.setRua(rua);
            endereco.setCidade(cidade);
            endereco.setBairro(bairro);
            endereco.setEstado(estado);
            endereco.setCep(cep);
            endereco.setNumero(numero);

            professorDTO.endereco = endereco;

            try {
                professorController.salvarProfessor(professorDTO);
                JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                new ProfessorGUI().setVisible(true);
                jFrame.dispose();

            } catch (AlunoJaExisteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
