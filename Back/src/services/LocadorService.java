package services;

import database.BancoDeDados;
import interfaces.CrudConta;
import models.Locador;
import models.Terreno;
import java.util.List;
public class LocadorService implements CrudConta {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: editarSenha()

    public void cadastrar(String nomeUsuario,
                          String email,
                          String senha,
                          String nome,
                          String nascimento) {

        BancoDeDados.locadoresDataBase.add(new Locador(nomeUsuario,
                email, senha, nome, nascimento));

    }
    @Override
    public final boolean atualizarPerfil(int id, String nomeUsuario, String email, String senha,
                                         String nome, String nascimento)
    {
        Locador perfilAtual = resgatarLocadores(id);
        perfilAtual.setNomeUsuario(nomeUsuario);
        perfilAtual.setEmail(email);
        perfilAtual.setSenha(senha);
        perfilAtual.setNome(nome);
        perfilAtual.setNascimento(nascimento);

        return true;
    };
    public final void deletarPerfil(int id) {
        BancoDeDados.locadoresDataBase.remove(resgatarLocadores(id));
    }
    public final void imprimirPerfil (int id) {
        Locador locadorAtual = resgatarLocadores(id);
        System.out.println("### PERFIL DE LOCADOR ###");
        System.out.println("Usuário: " + locadorAtual.getNomeUsuario());
        System.out.println("Nome: " + locadorAtual.getNome());
        System.out.println("Email: " + locadorAtual.getEmail());
        System.out.println("Nascimento: " + locadorAtual.getNascimento());

    }
    public final Locador resgatarLocadores(int id) {

        Locador perfilAtual = BancoDeDados.locadoresDataBase
                .stream()
                .filter(locador -> locador.getId() == id)
                .findFirst().get();

        return perfilAtual;
    }
    public final void arrendarTerreno(int idTerreno) {

    }

    public final void cancelarcontrato(int idTerreno) {

    }

    public final List<Terreno> resgatarTerrenosArrendados() {

        return null;
    }

}
