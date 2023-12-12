package br.edu.ifpb.ads.views.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.ifpb.ads.views.aluno.AlunoGUI;
import br.edu.ifpb.ads.views.inicio.InicioGUI;
import br.edu.ifpb.ads.views.professor.ProfessorFormGUI;
import br.edu.ifpb.ads.views.professor.ProfessorGUI;

public class OuvinteBotaoVoltar implements ActionListener {

    private JFrame janela;
    private int nextFrame;

    public OuvinteBotaoVoltar(JFrame janela, int nextFrame) {
        this.janela = janela;
        this.nextFrame = nextFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        janela.dispose();
        if(nextFrame == 0){
            new AlunoGUI().setVisible(true);
        } else if(nextFrame == 1){
            new ProfessorFormGUI().setVisible(true);
        } else if (nextFrame == 2) {
            new InicioGUI().setVisible(true);
        }else if (nextFrame == 3){
            new ProfessorGUI().setVisible(true);
        }
    }
    
}
