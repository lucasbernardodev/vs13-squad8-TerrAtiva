package services;

import database.BancoDeDados;
import interfaces.CrudConta;
import models.Locador;
import models.Terreno;
import util.ValoresRandomicos;

import java.util.List;

public class LocadorService implements CrudConta {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: Jogar em pasta de services
    public void cadastrar(Locador novoUsuario) {
        boolean existeId;
        int id;
        do{
            id = ValoresRandomicos.gerarValorPositivo();
            int finalId = id;
            existeId = BancoDeDados.locadoresDataBase.stream().anyMatch(locador -> locador.getId() == finalId);
        } while (existeId);
        novoUsuario.setId(id);
        BancoDeDados.locadoresDataBase.add(novoUsuario);

    }
    public final boolean atualizarPerfil(int id, String nomeUsuario, String email,
                                         String nome, String nascimento)
    {
        List<Locador> listaDeLocadoresDb = BancoDeDados.locadoresDataBase;

        Locador perfilAtual = listaDeLocadoresDb
                .stream()
                .filter(locador -> locador.getId() == id)
                .toList()
                .get(0);

        perfilAtual.setNomeUsuario(nomeUsuario);
        perfilAtual.setEmail(email);
        perfilAtual.setNome(nome);
        perfilAtual.setNascimento(nascimento);


       return true;
    };

    @Override
    public boolean atualizarPerfil(String nomeUsuario, String email, String senha, String nome, String nascimento) {
        return false;
    }

    public final void deletarPerfil(int id) {

    }
    public final void arrendarTerreno(int idTerreno) {

    }

    public final void cancelarcontrato(int idTerreno) {

    }

    public final List<Terreno> resgatarTerrenosArrendados() {
        return null;
    }

}
