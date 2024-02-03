package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.Terreno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrenoRepository extends JpaRepository<Terreno, Integer> {

    List<Terreno> findAllByProprietarioID(Integer donoID);

    List<Terreno> findAllByDisponivelEqualsAndProprietarioID(String disponivel, Integer donoID);

}