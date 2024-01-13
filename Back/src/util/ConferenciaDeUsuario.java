package util;
import database.BancoDeDados;

public class ConferenciaDeUsuario {

    public Boolean conferencia(int area, String nome, String senha) {
        if (area == 1) {
            for (Locador locador : BancoDeDados.locadoresDataBase) {
                if (nome.equals(locador.getNomeUsuario())
                        && senha.equals(locador.getSenha()))
                    return true;
            }
        }
        else if (area == 2) {
            for (Locatario locatario : BancoDeDados.locatariosDataBase) {
                if (nome.equals(locatario.getNomeUsuario())
                        && senha.equals(locatario.getSenha()))
                    return true;
            }
        }
        return false;
    }
}

