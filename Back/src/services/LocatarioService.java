package services;

import interfaces.CrudConta;
import models.Locatario;
import models.Terreno;
import java.util.List;

public class LocatarioService implements CrudConta {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: Jogar em pasta de services
    public final void cadastrar(Locatario novoUsuario) {

    }
    public final boolean atualizarPerfil(String nomeUsuario, String email,
                                         String senha, String nome, String nascimento) {
        return true;
    }
    public final void deletarPerfil(int id) {

    }
    public final void cancelarcontrato(int idTerreno) {

    }

    public final List<Terreno> resgatarTerrenosArrendados() {
        return null;
    }
}
