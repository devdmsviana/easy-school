package br.edu.ifpb.ads.views.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OuvinteBotaoVoltar implements ActionListener {


    private JFrame janela;

    public OuvinteBotaoVoltar(JFrame janela) {
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        janela.dispose();
        // TODO implementar lógica de voltar pra tela anterio
        JOptionPane.showMessageDialog(null, "Em breve irá voltar pra tela anterior", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
