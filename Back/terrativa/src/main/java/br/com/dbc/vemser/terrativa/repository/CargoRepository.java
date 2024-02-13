package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CargoRepository extends JpaRepository<Cargo, Integer>  {

    Set<Cargo> findCargosByIdCargo(Integer id);
}
