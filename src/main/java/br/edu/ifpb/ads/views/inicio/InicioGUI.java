package br.edu.ifpb.ads.views.inicio;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import br.edu.ifpb.ads.views.professor.ProfessorGUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.edu.ifpb.ads.utils.Imagens;
import br.edu.ifpb.ads.utils.components.JButtonPadrao;
import br.edu.ifpb.ads.utils.components.JLabelTitulo;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import br.edu.ifpb.ads.views.aluno.AlunoGUI;
import br.edu.ifpb.ads.views.ouvintes.OuvinteBotoesTelaInicial;
import br.edu.ifpb.ads.views.relatorio.RelatorioGUI;

public class InicioGUI extends JanelaPadrao {

    public InicioGUI() {
        super("Easy School - Início");
        adicionarLabels();
        adicionarImagens();
        adicionarButtons();
        adicionarSeparador();
        adicionarMenuBar();
    }


    private void adicionarLabels() {
        //TODO adicionar nome do usuário logado -> buscar na base de dados
        JLabel lblTitulo = new JLabelTitulo("Seja bem-vindo", 510, 5, 350, 90);
        add(lblTitulo);

    }

    private void adicionarImagens() {
        JLabel lblImagemInicio = new JLabel(Imagens.INICIO_FLAT);
        lblImagemInicio.setBounds(5, 35, 368, 368);
        add(lblImagemInicio);
    }

    private void adicionarButtons(){
        JButton btnAlunos = new JButtonPadrao("Alunos", 510, 95, 300, 50);
        btnAlunos.addActionListener(new OuvinteBotoesTelaInicial(new AlunoGUI(), this));
        add(btnAlunos);

        JButton btnProfessores = new JButtonPadrao("Professores", 510, 155, 300, 50);
        btnProfessores.addActionListener(new OuvinteBotoesTelaInicial(new ProfessorGUI(), this));
        add(btnProfessores);

        JButton btnRelatorios = new JButtonPadrao("Relatórios", 510, 215, 300, 50);
        btnRelatorios.addActionListener(new OuvinteBotoesTelaInicial(new RelatorioGUI(), this));
        add(btnRelatorios);
    }

    private void adicionarSeparador(){
        JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
        separador.setBounds(370, 0, 5, 500);
        separador.setForeground(Color.GRAY);
        add(separador);
    }

    private void adicionarMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Opções");
        menuBar.add(menu);

        JMenuItem itemSobre = new JMenuItem("Sobre");
        //TODO adicionar action listener (ação) -> exibir janela com informações sobre o sistema
        menu.add(itemSobre);

        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                dispose();
                //new LoginGUI().setVisible(true);
            }
        });
        menu.add(itemSair);
    }
}
