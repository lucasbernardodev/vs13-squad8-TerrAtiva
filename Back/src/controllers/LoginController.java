package controllers;

import app.enums.TipoUsuario;

public class LoginController {

    private final LocadorService locadorService = new LocadorService();
    private final LocatarioService locatarioService = new LocatarioService();
    private final RetornaId retornaId = new RetornaId();

    private static Locador locadorLogado;
    private static Locatario locatarioLogado;
    private static TipoUsuario tipoUsuario;

    public Locador loginLocador(int area, String nome, String senha) {
        Locador locadorLogado = locadorService.resgatarLocador(retornaId.retornaId(area, nome, senha));
        this.locadorLogado = locadorLogado;
        tipoUsuario = TipoUsuario.LOCADOR;
        return locadorLogado;
    }

    public Locatario loginLocatario(int area, String nome, String senha) {
        Locatario locatarioLogado = locatarioService.resgatarLocatarios(retornaId.retornaId(area, nome, senha));
        this.locatarioLogado = locatarioLogado;
        tipoUsuario = TipoUsuario.LOCATARIO;
        return locatarioLogado;
    }

    public static TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public static Locador getLocadorLogado() {
        return locadorLogado;
    }

    public static Locatario getLocatarioLogado() {return locatarioLogado;}

}
