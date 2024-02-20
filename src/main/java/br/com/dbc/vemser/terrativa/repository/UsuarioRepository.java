package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsuarioIdAndAtivoEquals(Integer id, String s);

    Optional<Usuario> findByEmail(String email);

}
