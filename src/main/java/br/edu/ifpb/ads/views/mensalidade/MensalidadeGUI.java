package br.edu.ifpb.ads.views.mensalidade;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.model.Mensalidade;
import br.edu.ifpb.ads.payments.FormaPagamentoStrategy;
import br.edu.ifpb.ads.payments.TipoPagamento;
import br.edu.ifpb.ads.utils.components.JButtonPadrao;
import br.edu.ifpb.ads.utils.components.JButtonVoltar;
import br.edu.ifpb.ads.utils.components.JLabelTitulo;
import br.edu.ifpb.ads.utils.components.JanelaPadrao;
import br.edu.ifpb.ads.views.ouvintes.OuvinteBotaoVoltar;

public class MensalidadeGUI extends JanelaPadrao {

    private ButtonGroup btnGrupo = new ButtonGroup();
    private JRadioButton rbDinheiro, rbCartao, rbPix;
    private JComboBox<Mensalidade> cbMensalidades;
    private JButton btnPagar, btnVoltar;
    private JLabel lblFormaPagamento, lblMensalidadesDisponiveis, lblTitulo;
    private AlunoDTO aluno;
    private AlunoController alunoController;

    public MensalidadeGUI(AlunoController alunoController, AlunoDTO aluno, String titulo) {
        super(titulo);
        this.aluno = aluno;
        this.alunoController = alunoController;
        setLocationRelativeTo(null);
        iniciarComponentes();
        setPreferredSize(new Dimension(500, 300));
        setSize(new Dimension(500, 285));
        preencherComboBox();

        rbDinheiro.setActionCommand(TipoPagamento.DINHEIRO.name());
        rbCartao.setActionCommand(TipoPagamento.CARTAO.name());
        rbPix.setActionCommand(TipoPagamento.PIX.name());
        btnGrupo.add(rbDinheiro);
        btnGrupo.add(rbPix);
        btnGrupo.add(rbCartao);

    }

    private void pagarMensalidade() {
        Mensalidade mensalidadeSelecionada = (Mensalidade) cbMensalidades.getSelectedItem();
        FormaPagamentoStrategy formaPagamento = null;

        ButtonModel radioSelecionado = btnGrupo.getSelection();
        if (radioSelecionado != null) {
            String tipoSelecionado = radioSelecionado.getActionCommand();
            formaPagamento = TipoPagamento.valueOf(tipoSelecionado).obterFormaPagamento();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma forma de pagamento.");
            return;
        }

        try {
            mensalidadeSelecionada.setFormaPagamentoStrategy(formaPagamento);
            mensalidadeSelecionada.calcularPagamento();
            alunoController.atualizarAluno(aluno.getMatricula(), aluno);
            JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao efetuar pagamento!", "Atenção!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void preencherComboBox() {
        DefaultComboBoxModel<Mensalidade> model = new DefaultComboBoxModel<>();
        for (Mensalidade mensalidade : Mensalidade.buscarMensalidadesNaoPagas(aluno.getMensalidades())) {
            model.addElement(mensalidade);
        }
        cbMensalidades.setModel(model);
    }

    private void iniciarComponentes() {
        lblTitulo = new JLabelTitulo("MENSALIDADES");
        lblMensalidadesDisponiveis = new JLabel("Mensalidades disponíveis para pagamentos:");
        cbMensalidades = new JComboBox<>(new Mensalidade[] {});
        lblFormaPagamento = new JLabel("Selecionar forma de pagamento:");
        rbDinheiro = new JRadioButton("Dinheiro");
        rbCartao = new JRadioButton("Cartão");
        rbPix = new JRadioButton("Pix");
        btnPagar = new JButtonPadrao("Pagar");
        btnVoltar = new JButtonVoltar();

        btnVoltar.addActionListener(new OuvinteBotaoVoltar(this, 99999));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null); // Mantendo o layout null

        lblTitulo.setBounds(180, 10, 180, 30);
        lblMensalidadesDisponiveis.setBounds(20, 85, 265, 20);
        cbMensalidades.setBounds(20, 110, 210, 20);
        lblFormaPagamento.setBounds(20, 140, 210, 30);
        rbDinheiro.setBounds(20, 170, 80, 35);
        rbPix.setBounds(100, 170, 80, 35);
        rbCartao.setBounds(160, 170, 90, 35);
        btnPagar.setBounds(200, 220, 60, 25);

        btnPagar.addActionListener(new OuvinteBotaoPagar());

        btnVoltar.setBounds(0, 10, 70, 30);

        getContentPane().add(lblTitulo);
        getContentPane().add(lblMensalidadesDisponiveis);
        getContentPane().add(cbMensalidades);
        getContentPane().add(lblFormaPagamento);
        getContentPane().add(rbDinheiro);
        getContentPane().add(rbCartao);
        getContentPane().add(rbPix);
        getContentPane().add(btnPagar);
        getContentPane().add(btnVoltar);

        pack();
    }

    private class OuvinteBotaoPagar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pagarMensalidade();
            dispose();
        }

    }

}
