package br.edu.ifpb.ads.views.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OuvinteBotoesTelaInicial implements ActionListener {
    
    private JFrame janela;
    private JFrame janelaAnterior;


    public OuvinteBotoesTelaInicial(JFrame janela, JFrame janelaAnterior){
        this.janela = janela;
        this.janelaAnterior = janelaAnterior;
    }


        @Override
        public void actionPerformed(ActionEvent e) {

            janelaAnterior.dispose();
            janela.setVisible(true);
        }
    
}