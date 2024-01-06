package services;

import database.BancoDeDados;
import infra.exceptions.DataNotFoundException;
import infra.exceptions.EmptyDataException;
import interfaces.CrudConta;
import models.Locador;
import models.Terreno;
import util.Validacao;

import java.util.List;

public class LocadorService implements CrudConta<Locador> {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: editarSenha()

    TerrenoService terrenoService = new TerrenoService();

    public void cadastrar(String nomeUsuario,
                          String email,
                          String senha,
                          String nome,
                          String nascimento) {

        Validacao.validarInfoUsuario(nomeUsuario, email, senha, nome, nascimento);

        BancoDeDados.locadoresDataBase.add(new Locador(nomeUsuario,
                email, senha, nome, nascimento));

    }

    @Override
    public final void atualizarPerfil(Locador locador) {

        Validacao.validarInfoUsuario(locador.getNomeUsuario(), locador.getEmail(), locador.getSenha(), locador.getNome(), locador.getNascimento());

        Locador perfilAtual = resgatarLocador(locador.getId());
        perfilAtual.setNomeUsuario(locador.getNomeUsuario());
        perfilAtual.setEmail(locador.getEmail());
        perfilAtual.setNome(locador.getNome());
        perfilAtual.setNascimento(locador.getNascimento());
    }

    public final void deletarPerfil(int id) {
        BancoDeDados.locadoresDataBase.remove(resgatarLocador(id));
    }

    public final String imprimirPerfil(int id) {
        Locador locadorAtual = resgatarLocador(id);
        return locadorAtual.toString();
    }



    public final Locador resgatarLocadores(String email) {

        try {
            return BancoDeDados.locadoresDataBase
                    .stream()
                    .filter(locador -> locador.getEmail().equals(email))
                    .findFirst().get();
        }
        catch (Exception e) {
            System.err.println("Email não existente!");
        }
        return null;

    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.

    public final void arrendarTerreno(int idTerreno, Locador locador) {
        Terreno novoContrato = terrenoService.buscarTerreno(idTerreno);
        novoContrato.setLocador(locador);
        novoContrato.setDisponivel(false);
    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.
    public final void cancelarcontrato(int idTerreno, Locador locador) {
        Terreno contratoAtual = terrenoService.buscarTerreno(idTerreno);
        contratoAtual.setLocador(null);
        contratoAtual.setDisponivel(true);
    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.
    public final List<Terreno> resgatarTerrenosArrendados(Locador locador) {
        return terrenoService.buscarTerreno(locador);
    }

    // MÉTODOS PRIVADOS
    public final Locador resgatarLocador(int id) {

        return BancoDeDados.locadoresDataBase
                .stream()
                .filter(locador -> locador.getId() == id)
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Dados de Locador não Encontrado"));
    }

}
