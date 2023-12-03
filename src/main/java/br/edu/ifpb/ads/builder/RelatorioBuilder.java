package br.edu.ifpb.ads.builder;

import com.itextpdf.layout.Document;

import java.io.FileNotFoundException;

public interface RelatorioBuilder {

    void addCabecalho();
    void addAluno();
    void addFinanceiro();

    Document getResult() throws FileNotFoundException;
}
