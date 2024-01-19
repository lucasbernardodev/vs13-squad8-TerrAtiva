package br.com.dbc.vemser.terrativa.backup.interfaces;

public interface CrudConta<T> {
    void atualizarPerfil(T data);
    void deletarPerfil(int id);
}
