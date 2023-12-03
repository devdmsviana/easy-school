package br.edu.ifpb.ads.builder;

import com.itextpdf.layout.Document;

import java.io.FileNotFoundException;

public class Diretor {

    public Document construct(RelatorioFinanceiro relatorioFinanceiro) throws FileNotFoundException {
        relatorioFinanceiro.addCabecalho();
        relatorioFinanceiro.addAluno();
        relatorioFinanceiro.addFinanceiro();
        return relatorioFinanceiro.getResult();
    }
}
