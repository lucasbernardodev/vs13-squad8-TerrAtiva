package controllers;

import app.menus.LocadorMenu;
import app.menus.LocatarioMenu;
import models.Locador;
import models.Locatario;
import services.LocadorService;
import services.LocatarioService;
import util.RetornaId;

public class LoginController {

    private final LocadorService locadorService = new LocadorService();
    private final LocatarioService locatarioService = new LocatarioService();
    private final RetornaId retornaId = new RetornaId();

    public Locador loginLocador(int area, String nome, String senha) {
        Locador locadorLogado = locadorService.resgatarLocador(retornaId.retornaId(area, nome, senha));
        LocadorMenu.locadorLogado = locadorLogado;
        return locadorLogado;
    }

    public  Locatario loginLocatario(int area, String nome, String senha) {
        Locatario locadorLogado = locatarioService.resgatarLocatarios(retornaId.retornaId(area, nome, senha));
        LocatarioMenu.locatarioLogado = locadorLogado;
        return locadorLogado;
    }
}
