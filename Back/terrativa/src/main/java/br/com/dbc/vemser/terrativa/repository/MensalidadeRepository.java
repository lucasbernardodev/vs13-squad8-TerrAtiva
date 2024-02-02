package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Integer> {

}


