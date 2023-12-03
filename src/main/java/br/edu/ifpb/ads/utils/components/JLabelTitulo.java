package br.edu.ifpb.ads.utils.components;

import java.awt.Font;

import javax.swing.JLabel;

public class JLabelTitulo extends JLabel {

    public JLabelTitulo(String texto) {
        super(texto);
        setFont(new Font("Roboto", Font.BOLD, 24));
    }

    public JLabelTitulo(String texto, int posX, int posY, int largura, int altura) {
        super(texto);
        setBounds(posX, posY, largura, altura);
        setFont(new Font("Roboto", Font.BOLD, 24));
        setHorizontalAlignment(JLabel.CENTER);
    }

}