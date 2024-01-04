import interfaces.CrudConta;

public class LocadorService implements CrudConta {
    //TODO: Lógica dos métodos (faltava o banco de dados no momento pra fazer)
    //TODO: Jogar em pasta de services

    public final void cadastrar(Locador novoUsuario) {

    }
    public final boolean atualizarPerfil(String nomeUsuario, String email,
                                         String senha, String nome, String nascimento)
    {
       return true;
    }
    public final void deletarPerfil(int id) {

    }
    public final void arrendarTerreno(int idTerreno) {

    }

    public final void cancelarcontrato(int idTerreno) {

    }

    public final List<Terreno> resgatarTerrenosArrendados() {

    }

}
