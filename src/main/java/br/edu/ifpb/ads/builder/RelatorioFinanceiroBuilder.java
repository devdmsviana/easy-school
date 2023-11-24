
package br.edu.ifpb.ads.builder;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import br.edu.ifpb.ads.controller.AlunoController;

public class RelatorioFinanceiroBuilder {

    private Document document;
    private AlunoController alunoController;

    public RelatorioFinanceiroBuilder(AlunoController alunoController) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter("relatorio-financeiro.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        document = new Document(pdf);
        this.alunoController = alunoController;
    }

    public RelatorioFinanceiroBuilder addCabecalho() {
        Paragraph cabecalho = new Paragraph("Relatório Financeiro")
                .setBold()
                .setFontSize(20)
                .setMarginLeft(20)
                .setMarginRight(20)
                .setMarginBottom(10)
                .setMarginTop(10);
        document.add(cabecalho);
        return this;
    }

    public RelatorioFinanceiroBuilder addAluno() {
        int alunosAtivos = alunoController.buscarAlunosAtivos().size();
        int alunosNovos = alunoController.buscarAlunosPosData(LocalDate.now()).size();
        int alunosInativos = alunoController.buscarAlunosInativos().size();

        Paragraph sumarioAluno = new Paragraph("Sumário Aluno")
                .setMarginLeft(20)
                .setMarginRight(20)
                .setMarginBottom(10)
                .setMarginTop(10)
                .setBold();
        Table table = new Table(new float[] { 1, 1, 1 });
        table.addCell("Alunos Ativos");
        table.addCell("Novos Alunos");
        table.addCell("Alunos Inativos");
        table.addCell(String.valueOf(alunosAtivos));
        table.addCell(String.valueOf(alunosNovos));
        table.addCell(String.valueOf(alunosInativos));

        document.add(sumarioAluno);
        document.add(table);
        return this;
    }


    public RelatorioFinanceiroBuilder addFinanceiro(){
        int alunosComMensalidadeAtrasada = alunoController.buscarAlunosPorMensalidadeAtrasada().size();
        int alunosSemMensalidadeAtrasada = alunoController.buscarAlunosAtivos().size() - alunosComMensalidadeAtrasada;

        Paragraph sumarioFinanceiro = new Paragraph("Sumário Financeiro")
                .setMarginLeft(20)
                .setMarginRight(20)
                .setMarginBottom(10)
                .setMarginTop(10)
                .setBold();
        Table table = new Table(new float[] { 1, 1 });
        table.addCell("Alunos com mensalidade atrasada");
        table.addCell("Alunos sem mensalidade atrasada");
        table.addCell(String.valueOf(alunosComMensalidadeAtrasada));
        table.addCell(String.valueOf(alunosSemMensalidadeAtrasada));

        document.add(sumarioFinanceiro);
        document.add(table);
        return this;
    }

    public void build() {
        document.close();
    }

}
