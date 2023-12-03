package br.edu.ifpb.ads.utils.components;

import java.awt.Insets;

import javax.swing.JButton;

import br.edu.ifpb.ads.utils.Imagens;

public class JButtonVoltar extends JButton {

    public JButtonVoltar(){
        super(Imagens.VOLTAR);
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(null);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setToolTipText("Voltar");
    }


    
}