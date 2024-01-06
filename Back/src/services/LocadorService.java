package services;

import database.BancoDeDados;
import interfaces.CrudConta;
import models.Locador;
import models.Terreno;
import java.util.List;
public class LocadorService implements CrudConta {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: editarSenha()

    TerrenoService terrenoService = new TerrenoService();

    public void cadastrar(String nomeUsuario,
                          String email,
                          String senha,
                          String nome,
                          String nascimento) {

        BancoDeDados.locadoresDataBase.add(new Locador(nomeUsuario,
                email, senha, nome, nascimento));

    }
    @Override
    public final boolean atualizarPerfil(int id, String nomeUsuario, String email,
                                         String nome, String nascimento)
    {
        Locador perfilAtual = resgatarLocadores(id);
        perfilAtual.setNomeUsuario(nomeUsuario);
        perfilAtual.setEmail(email);
        perfilAtual.setNome(nome);
        perfilAtual.setNascimento(nascimento);

        return true;
    }
    public final void deletarPerfil(int id) {
        BancoDeDados.locadoresDataBase.remove(resgatarLocadores(id));
    }
    public final void imprimirPerfil (int id) {
        Locador locadorAtual = resgatarLocadores(id);
        System.out.println("### PERFIL DE LOCATÁRIO ###");
        System.out.println("Usuário: " + locadorAtual.getNomeUsuario());
        System.out.println("Nome: " + locadorAtual.getNome());
        System.out.println("Email: " + locadorAtual.getEmail());
        System.out.println("Nascimento: " + locadorAtual.getNascimento());

    }
    public final Locador resgatarLocadores(int id) {

        return BancoDeDados.locadoresDataBase
                .stream()
                .filter(locador -> locador.getId() == id)
                .findFirst().get();
    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.

    public final boolean arrendarTerreno(int idTerreno, Locador locador) {
        Terreno novoContrato = terrenoService.buscarTerrenos(idTerreno);
        if (novoContrato != null) {
            novoContrato.setLocador(locador);
            novoContrato.setDisponivel(false);
            return true;
        } else {
            System.out.println("Não foi possível concluir o contrato. Por favor, selecione um terreno válido.");
            return false;
        }
    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.
    public final boolean cancelarcontrato(int idTerreno,  Locador locador) {
        Terreno contratoAtual = terrenoService.buscarTerrenos(idTerreno);
        if (contratoAtual != null && contratoAtual.getLocador() == locador) {
            contratoAtual.setLocador(null);
            contratoAtual.setDisponivel(true);
            return true;
        } else {
            return false;
        }
    }

    // TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber locador por parametro.
    public final List<Terreno> resgatarTerrenosArrendados(Locador locador) {

        return terrenoService.buscarTerrenos(locador);
    }

}
