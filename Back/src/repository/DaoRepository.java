package repository;

import java.util.List;

public interface DaoRepository<T> {
    void adicionar(T obj);
    void alterar(int id, T obj);
    void deletar(int id);
    T resgatarDadosPorId(int id);

}
