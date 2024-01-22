package br.com.dbc.vemser.terrativa.repository;

import java.sql.SQLException;

public interface DaoRepository<T> {
    T adicionar(T obj);
    T alterar(T obj) throws SQLException;
    void deletar(int id) throws SQLException;
    T resgatarDadosPorId(int id) throws SQLException;

}