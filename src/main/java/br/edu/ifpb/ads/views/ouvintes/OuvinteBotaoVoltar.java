package br.edu.ifpb.ads.views.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.ifpb.ads.views.aluno.AlunoGUI;

public class OuvinteBotaoVoltar implements ActionListener {


    private JFrame janela;

    public OuvinteBotaoVoltar(JFrame janela) {
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        janela.dispose();
        new AlunoGUI().setVisible(true);
    }
    
}
