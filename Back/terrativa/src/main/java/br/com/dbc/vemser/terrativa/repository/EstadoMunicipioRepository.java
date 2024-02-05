package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.EstadosMunicipios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoMunicipioRepository extends JpaRepository<EstadosMunicipios, Integer> {

}
