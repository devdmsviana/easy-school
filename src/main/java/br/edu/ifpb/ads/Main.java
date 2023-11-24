package br.edu.ifpb.ads;

import java.util.List;
import java.util.Scanner;

import br.edu.ifpb.ads.controller.AlunoController;
import br.edu.ifpb.ads.dao.AlunoDAO;
import br.edu.ifpb.ads.dao.impl.AlunoDaoImpl;
import br.edu.ifpb.ads.model.Aluno;
import br.edu.ifpb.ads.model.Mensalidade;
import br.edu.ifpb.ads.model.enums.StatusPagamento;
import br.edu.ifpb.ads.payments.FormaPagamentoStrategy;
import br.edu.ifpb.ads.payments.TipoPagamento;

public class Main {

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      AlunoController alunoController = new AlunoController();

      Aluno aluno = alunoController.buscarAluno("20231121");

      System.out.println("Deseja pagar alguma mensalidade? (s/n)");
      String resposta = scanner.nextLine();

      while (resposta.equalsIgnoreCase("s")) {
         List<Mensalidade> mensalidadesDisponiveis = Mensalidade.buscarMensalidadesNaoPagas(aluno.getMensalidades());
         if (mensalidadesDisponiveis.isEmpty()) {
            System.out.println("Não há mensalidades disponíveis para pagamento.");
         } else {
            System.out.println("Mensalidades disponíveis para pagamento:");
            for (int i = 0; i < mensalidadesDisponiveis.size(); i++) {
               Mensalidade mensalidade = mensalidadesDisponiveis.get(i);
               System.out.println("Opção " + (i + 1) + ": Valor " + mensalidade.getValor() + " - Data de Vencimento "
                     + mensalidade.getDataVencimento());
            }
         }
         System.out.println("Qual mensalidade deseja pagar? (1-" + mensalidadesDisponiveis.size() + ")");

         String mensalidadeIdStr = scanner.nextLine();
         if (mensalidadeIdStr.isEmpty()) {
            System.out.println("A entrada não pode estar vazia. Por favor, digite um número entre 1 e " + mensalidadesDisponiveis.size() + ".");
            continue;
         }
         int mensalidadeId = Integer.parseInt(mensalidadeIdStr) - 1;

         if (mensalidadeId >= 0 && mensalidadeId < mensalidadesDisponiveis.size()) {
            Mensalidade mensalidade = mensalidadesDisponiveis.get(mensalidadeId);

            System.out.println("Selecione a forma de pagamento da mensalidade (Pix, Dinheiro ou Cartão): ");
            String formaPagamento = scanner.nextLine().toUpperCase();

            TipoPagamento tipoPagamento = TipoPagamento.valueOf(formaPagamento);
            FormaPagamentoStrategy formaPagamentoStrategy = tipoPagamento.obterFormaPagamento();

            mensalidade.setFormaPagamentoStrategy(formaPagamentoStrategy);

            System.out.println("Calculando pagamento da mensalidade...");

            if (mensalidade.getStatusPagamento() == StatusPagamento.PENDENTE || mensalidade.getStatusPagamento() == StatusPagamento.ATRASADO) {
               mensalidade.calcularPagamento();
               System.out.println("Mensalidade paga com sucesso!");
            } else {
               System.out.println("Mensalidade não encontrada ou já paga!");
            }
         } else {
            System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
         }

         System.out.println("Deseja pagar outra mensalidade? (s/n)");
         resposta = scanner.next();
      }

      // Verificar se todas as mensalidades estão pagas
      boolean todasPagas = true;
      for (Mensalidade m : aluno.getMensalidades()) {
         if (m.getStatusPagamento() != StatusPagamento.PAGO) {
            todasPagas = false;
            break;
         }
      }

      // Atualizar o status do aluno em caso de cancelamento ou término da matrícula
      if (todasPagas) {
         aluno.setAtivo(false);
         System.out.println("Todas as mensalidades foram pagas. Matrícula encerrada.");
      }

      alunoController.atualizarAluno(aluno);
   }
}
