package br.com.dbc.vemser.terrativa.interfaces;

public interface CrudConta<T> {
    void atualizarPerfil(T data);
    void deletarPerfil(int id);
}
