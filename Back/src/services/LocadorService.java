package services;

import database.BancoDeDados;
import infra.exceptions.DataNotFoundException;
import infra.exceptions.EmptyDataException;
import interfaces.CrudConta;
import models.Locador;
import models.Terreno;

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

        if (nomeUsuario.isBlank()) throw new EmptyDataException("Seu NOME DE USUÁRIO não pode estar vazio!");
        if (email.isBlank()) throw new EmptyDataException("Seu EMAIL não pode estar vazio!");
        if (senha.isBlank()) throw new EmptyDataException("Sua SENHA não pode estar vazio!");
        if (nome.isBlank()) throw new EmptyDataException("Seu NOME não pode estar vazio!");
        if (nascimento.isBlank()) throw new EmptyDataException("Sua DATA DE NASCIMENTO não pode estar vazio!");

        BancoDeDados.locadoresDataBase.add(new Locador(nomeUsuario,
                email, senha, nome, nascimento));

    }

    @Override
    public final void atualizarPerfil(Locador locador) {

        if (locador.getNomeUsuario().isBlank()) throw new EmptyDataException("Seu NOME DE USUÁRIO não pode estar vazio!");
        if (locador.getEmail().isBlank()) throw new EmptyDataException("Seu EMAIL não pode estar vazio!");
        if (locador.getNome().isBlank()) throw new EmptyDataException("Seu NOME não pode estar vazio!");
        if (locador.getNascimento().isBlank()) throw new EmptyDataException("Sua DATA DE NASCIMENTO não pode estar vazio!");

        Locador perfilAtual = resgatarLocador(locador.getId());
        perfilAtual.setNomeUsuario(locador.getNomeUsuario());
        perfilAtual.setEmail(locador.getEmail());
        perfilAtual.setNome(locador.getNome());
        perfilAtual.setNascimento(locador.getNascimento());

    }

    public final void deletarPerfil(int id) {
        BancoDeDados.locadoresDataBase.remove(resgatarLocador(id));
    }

    public final void imprimirPerfil(int id) {
        Locador locadorAtual = resgatarLocador(id);
        System.out.println("### PERFIL DE LOCADOR ###");
        System.out.println("Usuário: " + locadorAtual.getNomeUsuario());
        System.out.println("Nome: " + locadorAtual.getNome());
        System.out.println("Email: " + locadorAtual.getEmail());
        System.out.println("Nascimento: " + locadorAtual.getNascimento());

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
