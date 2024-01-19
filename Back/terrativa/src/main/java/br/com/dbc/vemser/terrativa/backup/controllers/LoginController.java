package br.com.dbc.vemser.terrativa.backup.controllers;

import br.com.dbc.vemser.terrativa.models.Usuario;
import br.com.dbc.vemser.terrativa.services.UsuarioService;

public class LoginController {
    UsuarioService usuarioService = new UsuarioService();

    public boolean loginUsuario(String email, String senha) {
        try {
            Usuario usuario = usuarioService.realizarLogin(email,senha);
            if (usuario != null) return true;
            return false;
        } catch (InvalidParamException e) {
            throw new InvalidParamException(e.getMessage());
        } catch (DataFormatInvalidException e) {
            throw new DataFormatInvalidException(e.getMessage());
        } catch (DbException e) {
            System.out.println("Dados incorretos, tente novamente");
            return false;
        }

    }
}
