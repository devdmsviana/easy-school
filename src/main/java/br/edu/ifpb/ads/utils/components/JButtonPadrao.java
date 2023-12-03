package br.edu.ifpb.ads.utils.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class JButtonPadrao extends JButton implements MouseListener{


    public JButtonPadrao(String texto){
        setText(texto);
    }

    public JButtonPadrao(String texto, int posX, int posY, int largura, int altura){
        setText(texto);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(posX, posY, largura, altura);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        putClientProperty("hover", true);
        setBackground(new Color(0, 119, 212));
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        putClientProperty("hover", false);
        setBackground(new Color(86,86,86));
        repaint();
    }
    
}