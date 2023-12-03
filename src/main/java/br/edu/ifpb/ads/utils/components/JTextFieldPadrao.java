package br.edu.ifpb.ads.utils.components;


import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

public class JTextFieldPadrao extends JTextField {

    public JTextFieldPadrao(String placeHolder) {
        setHorizontalAlignment(JTextField.CENTER);
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeHolder);
    }

    public JTextFieldPadrao(String placeHolder, int posX, int posY, int largura, int altura) {
        setBounds(posX, posY, largura, altura);
        setHorizontalAlignment(JTextField.CENTER);
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeHolder);

    }

}