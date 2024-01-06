package services;

import database.BancoDeDados;
import interfaces.CrudConta;
import models.Locatario;
import models.Terreno;
import java.util.List;

public class LocatarioService implements CrudConta {
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
    public final boolean atualizarPerfil(int id, String nomeUsuario, String email, String senha,
                                         String nome, String nascimento)
    {
        Locatario perfilAtual = resgatarLocatarios(id);
        perfilAtual.setNomeUsuario(nomeUsuario);
        perfilAtual.setEmail(email);
        perfilAtual.setSenha(senha);
        perfilAtual.setNome(nome);
        perfilAtual.setNascimento(nascimento);

        return true;
    };
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

        Locatario perfilAtual = BancoDeDados.locatariosDataBase
                .stream()
                .filter(locatario -> locatario.getId() == id)
                .findFirst().get();

        return perfilAtual;
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
        Terreno anuncio = terrenoService.busrcarTerrenos(id);

        if (anuncio.isDisponivel()) {
            terrenoService.deletarTerreno(id);
            return true;
        }

        System.out.println("Terrenos Arrendados Não Podem Ser Removidos, Por Favor Realizar Cancelamento Do Contrato Para Seguir Com a Ação");
        return false;

    }

    public final boolean cancelarcontrato(int idTerreno) {
        Terreno contratoAtual = terrenoService.busrcarTerrenos(idTerreno);
        if (contratoAtual != null) {
            contratoAtual.setLocador(null);
            contratoAtual.setDisponivel(true);
            return true;
        } else {
            return false;
        }
    }
    //    TODO: Definir forma de login e atrelar usuario logado atomaticamente ao inves de receber proprietario por parametro.
    public final List<Terreno> resgatarTerrenosArrendados(Locatario proprietario) {
        return terrenoService.busrcarTerrenos(proprietario);
    }
}
