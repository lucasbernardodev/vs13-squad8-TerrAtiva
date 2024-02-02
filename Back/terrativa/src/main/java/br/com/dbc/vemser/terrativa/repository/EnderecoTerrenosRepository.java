package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoTerrenosRepository extends JpaRepository<EnderecoTerrenos, Integer> {
    EnderecoTerrenos getById(Integer id);
}