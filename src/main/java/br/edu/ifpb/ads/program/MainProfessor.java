package br.edu.ifpb.ads.program;

import br.edu.ifpb.ads.controller.ProfessorController;
import br.edu.ifpb.ads.dto.ProfessorDTO;

public class MainProfessor {
    public static void main(String[] args) {

        ProfessorController professorController  =  new ProfessorController();

        ProfessorDTO professor = new ProfessorDTO();
        professor.setMatricula("654646544654");
        professor.nome = "Cleyton";
        professor.email = "teste@gmail.com";

        professorController.salvarProfessor(professor);

        for (ProfessorDTO p : professorController.listarProfessores()){
            System.out.println(p.nome);
        }
    }
}
