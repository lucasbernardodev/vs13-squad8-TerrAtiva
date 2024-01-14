package controllers;

import infra.exceptions.*;
import models.Usuario;
import services.UsuarioService;

import java.util.Objects;

public class LoginController {
    UsuarioService usuarioService = new UsuarioService();

    public boolean loginUsuario(String email, String senha) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(email,senha);
            if (usuario != null) return true;
            return false;
        } catch (InvalidParamException e) {
            throw new InvalidParamException(e.getMessage());
        } catch (DataFormatInvalidException e) {
            throw new DataFormatInvalidException(e.getMessage());
        } catch (DbException e) {
            throw new DbException(e.getMessage());
        }

    }
//    private final LocadorService locadorService = new LocadorService();
//    private final LocatarioService locatarioService = new LocatarioService();
//    private final RetornaId retornaId = new RetornaId();
//
//    private static Locador locadorLogado;
//    private static Locatario locatarioLogado;
//    private static TipoUsuario tipoUsuario;
//
//    public Locador loginLocador(int area, String nome, String senha) {
//        Locador locadorLogado = locadorService.resgatarLocador(retornaId.retornaId(area, nome, senha));
//        this.locadorLogado = locadorLogado;
//        tipoUsuario = TipoUsuario.LOCADOR;
//        return locadorLogado;
//    }
//
//    public Locatario loginLocatario(int area, String nome, String senha) {
//        Locatario locatarioLogado = locatarioService.resgatarLocatarios(retornaId.retornaId(area, nome, senha));
//        this.locatarioLogado = locatarioLogado;
//        tipoUsuario = TipoUsuario.LOCATARIO;
//        return locatarioLogado;
//    }
//
//    public static TipoUsuario getTipoUsuario() {
//        return tipoUsuario;
//    }
//
//    public static Locador getLocadorLogado() {
//        return locadorLogado;
//    }
//
//    public static Locatario getLocatarioLogado() {return locatarioLogado;}

}
