package interfaces;

public interface CrudConta {
    boolean atualizarPerfil(int id, String nomeUsuario, String email, String nome, String nascimento);
    void deletarPerfil(int id);
}
