package br.edu.ifpb.ads.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import br.edu.ifpb.ads.controller.AdminController;
import br.edu.ifpb.ads.dto.AdministradorDTO;
import br.edu.ifpb.ads.model.Administrador;
import br.edu.ifpb.ads.utils.Imagens;
import br.edu.ifpb.ads.utils.components.JButtonPadrao;
import br.edu.ifpb.ads.utils.components.JLabelTitulo;
import br.edu.ifpb.ads.utils.components.JTextFieldPadrao;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import br.edu.ifpb.ads.views.inicio.InicioGUI;

public class LoginGUI extends JanelaPadrao {

    private JTextFieldPadrao txtEmail;
    private JPasswordField txtSenha;
    private JCheckBox chLembrarSenha;
    private AdminController adminController;

    public LoginGUI() {
        super("Easy School - Login");
        adminController = AdminController.getInstance();
        adicionarImagens();
        adicionarLabels();
        adicionarTextFields();
        adicionarButtons();
        adicionarCheckBox();
        carregarCredenciaisSalvas();

    }

    private void adicionarImagens() {
        JLabel lblLogo = new JLabel(Imagens.LOGIN_FLAT);
        lblLogo.setBounds(5, 35, 368, 368);
        add(lblLogo);

    }

    private void adicionarLabels() {

        JLabelTitulo lblTitulo = new JLabelTitulo("Bem-vindo ao nosso sistema", 510, 95, 300, 50);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Faça login para continuar");
        lblSubtitulo.setBounds(585, 120, 300, 50);
        add(lblSubtitulo);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(500, 170, 100, 30);
        add(lblEmail);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(500, 220, 100, 30);
        add(lblSenha);

    }

    private void adicionarTextFields() {
        txtEmail = new JTextFieldPadrao("Digite seu email", 550, 170, 250, 30);
        add(txtEmail);

        txtSenha = new JPasswordField();
        txtSenha.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite sua senha");
        txtSenha.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");
        txtSenha.setHorizontalAlignment(JTextField.CENTER);
        txtSenha.setBounds(550, 220, 250, 30);
        add(txtSenha);

    }

    private void adicionarButtons() {
        JButtonPadrao btnLogin = new JButtonPadrao("Entrar", 550, 300, 250, 40);
        btnLogin.setBackground(new Color(0, 119, 212));
        btnLogin.addActionListener(new OuvinteBotaoLogin());
        add(btnLogin);
    }

    private void adicionarCheckBox() {
        chLembrarSenha = new JCheckBox("Lembrar senha");
        chLembrarSenha.setBounds(550, 255, 200, 30);
        add(chLembrarSenha);
    }

    private class OuvinteBotaoLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            AdministradorDTO administradorDTO = new AdministradorDTO(email, senha);

            Administrador admin = adminController.autenticarAdmin(administradorDTO);

            if (admin != null) {
                dispose();
                new InicioGUI().setVisible(true);

                if(chLembrarSenha.isSelected()){
                    salvarCredenciais(email, senha);
                }
            } else {
                JOptionPane.showMessageDialog(null, "E-mail ou senha incorretos!", "Credenciais inválidas",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void salvarCredenciais(String email, String senha) {
        Properties properties = new Properties();
        try (FileOutputStream fileOut = new FileOutputStream("credenciais.properties")) {
            properties.setProperty("email", email);
            properties.setProperty("senha", senha);
            properties.store(fileOut, "Credenciais de login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void carregarCredenciaisSalvas() {
    Properties properties = new Properties();

    try (FileInputStream fileIn = new FileInputStream("credenciais.properties")) {
        properties.load(fileIn);
        String email = properties.getProperty("email");
        String senha = properties.getProperty("senha");

        if (email != null && senha != null) {
            txtEmail.setText(email);
            txtSenha.setText(senha);
            chLembrarSenha.setSelected(true);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public JTextFieldPadrao getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextFieldPadrao txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }

    public void setTxtSenha(JPasswordField txtSenha) {
        this.txtSenha = txtSenha;
    }

}