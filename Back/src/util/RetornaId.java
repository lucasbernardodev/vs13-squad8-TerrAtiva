package util;

import database.BancoDeDados;
import models.Locador;
import models.Locatario;

public class RetornaId {

    public int retornaId(int area, String nome, String senha) {
        if (area == 1) {
            for (Locador locador : BancoDeDados.locadoresDataBase) {
                String nomeUsuario = locador.getNomeUsuario();
                String senhaUsuario = locador.getSenha();
                if (nome.equals(nomeUsuario) && senha.equals(senhaUsuario)) {
                    return locador.getId();
                }

            }
        }
        if (area == 2) for (
                Locatario locatario : BancoDeDados.locatariosDataBase) {
            String nomeUsuario = locatario.getNomeUsuario();
            String senhaUsuario = locatario.getSenha();
            if (nome.equals(nomeUsuario) && senha.equals(senhaUsuario)) {
                return locatario.getId();
            }
        }
        return 0;
    }
}
