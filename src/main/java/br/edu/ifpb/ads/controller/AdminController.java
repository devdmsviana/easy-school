package br.edu.ifpb.ads.controller;

import br.edu.ifpb.ads.dao.impl.AdministradorDaoImpl;
import br.edu.ifpb.ads.dto.AdministradorDTO;
import br.edu.ifpb.ads.model.Administrador;

public class AdminController {
    private static AdminController instance;
    private AdministradorDaoImpl administradorDAO;

    private AdminController() {
        administradorDAO = new AdministradorDaoImpl();
    }

    public static AdminController getInstance() {
        if (instance == null) {
            instance = new AdminController();
        }
        return instance;
    }

    public void salvarAdmin(AdministradorDTO administrador){
        administradorDAO.salvarAdmin(administrador);
    }

    public Administrador autenticarAdmin(AdministradorDTO administradorDTO){
        return administradorDAO.autenticarAdmin(administradorDTO.getEmail(), administradorDTO.getSenha());
    }

}