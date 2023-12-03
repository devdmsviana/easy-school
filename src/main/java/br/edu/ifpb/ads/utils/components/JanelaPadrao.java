package br.edu.ifpb.ads.utils.components;

import javax.swing.JFrame;

public abstract class JanelaPadrao extends JFrame {
    

    public JanelaPadrao(String titulo){
        setSize(1000, 500);
        setTitle(titulo);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        //setIconImage(Imagens.SISTEMA.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}