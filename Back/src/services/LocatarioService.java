package services;

import database.BancoDeDados;
import interfaces.CrudConta;
import models.Locatario;
import models.Terreno;
import java.util.List;

public class LocatarioService implements CrudConta<Locatario> {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: Jogar em pasta de services

    TerrenoService terrenoService = new TerrenoService();

    public void cadastrar(String nomeUsuario,
                          String email,
                          String senha,
                          String nome,
                          String nascimento) {

        BancoDeDados.locatariosDataBase.add(new Locatario(nomeUsuario,
                email, senha, nome, nascimento));

    }
    @Override
    public final void atualizarPerfil(Locatario locatario)
    {
        Locatario perfilAtual = resgatarLocatarios(locatario.getId());
        perfilAtual.setNomeUsuario(locatario.getNomeUsuario());
        perfilAtual.setEmail(locatario.getEmail());
        perfilAtual.setNome(locatario.getNome());
        perfilAtual.setNascimento(locatario.getNascimento());

    }
    public final void deletarPerfil(int id) {

        BancoDeDados.locatariosDataBase.remove(resgatarLocatarios(id));
    }
    public final void imprimirPerfil (int id) {
        Locatario locatarioAtual = resgatarLocatarios(id);
        System.out.println("### PERFIL DE LOCATÁRIO ###");
        System.out.println("Usuário: " + locatarioAtual.getNomeUsuario());
        System.out.println("Nome: " + locatarioAtual.getNome());
        System.out.println("Email: " + locatarioAtual.getEmail());
        System.out.println("Nascimento: " + locatarioAtual.getNascimento());

    }
    public final Locatario resgatarLocatarios(int id) {

        return BancoDeDados.locatariosDataBase
                .stream()
                .filter(locatario -> locatario.getId() == id)
                .findFirst().get();

    }

    public final Locatario resgatarLocatarios(String email) {

        try {
        return BancoDeDados.locatariosDataBase
                .stream()
                .filter(locatario -> locatario.getEmail().equals(email))
                .findFirst().get();
        }
            catch (Exception e) {
            System.err.println("Email não existente!");
        }
            return null;
    }

//    TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber proprietario por parametro.
    public final boolean criarAnuncio(
            String titulo,
            String descricao,
            String localizacao,
            String tamanho,
            double preco,
            Locatario proprietario
    ) {
        terrenoService.cadastrarTerreno(titulo, descricao,localizacao, tamanho, preco, proprietario);
        return true;
    }

    public final boolean removerAnuncio(int id) {
        Terreno anuncio = terrenoService.buscarTerreno(id);

        if (anuncio.isDisponivel()) {
            terrenoService.deletarTerreno(id);
            return true;
        }

        System.out.println("Terrenos arrendados não podem ser removidos, " +
                "por favor realizar cancelamento do contrato para seguir com a ação");
        return false;

    }

    public final boolean cancelarcontrato(int idTerreno, Locatario proprietario) {
        Terreno contratoAtual = terrenoService.buscarTerreno(idTerreno);
        if (contratoAtual != null && contratoAtual.getProprietario() == proprietario) {
            contratoAtual.setLocador(null);
            contratoAtual.setDisponivel(true);
            return true;
        } else {
            return false;
        }
    }
    //    TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber proprietario por parametro.
    public final List<Terreno> resgatarTerrenosArrendados(Locatario proprietario) {
        return terrenoService.buscarTerreno(proprietario);
    }
}
