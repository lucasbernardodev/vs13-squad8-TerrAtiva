package util;
import database.BancoDeDados;
import models.Locador;
import models.Locatario;

import java.util.ArrayList;

public class ConferenciaDeUsuario {

    public Boolean conferencia(int area, String nome, String senha) {
        if (area == 1) {
            for (Locador locador : BancoDeDados.locadoresDataBase) {
                String nomeUsuario = locador.getNomeUsuario();
                String senhaUsuario = locador.getSenha();
                if (nome.equals(nomeUsuario) && senha.equals(senhaUsuario)) {
                    return true;
                }

            }
        }
        if (area == 2) for (Locatario locatario : BancoDeDados.locatariosDataBase) {
            String nomeUsuario = locatario.getNomeUsuario();
            String senhaUsuario = locatario.getSenha();
            if (nome.equals(nomeUsuario) && senha.equals(senhaUsuario)) {
                return true;
            }
        }else{
            return false;
        }
        return false;
    }
}
