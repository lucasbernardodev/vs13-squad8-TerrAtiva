package interfaces;

public interface CrudConta {
    boolean atualizarPerfil(int id, String nomeUsuario, String email, String senha, String nome, String nascimento);
    void deletarPerfil(int id);
}
