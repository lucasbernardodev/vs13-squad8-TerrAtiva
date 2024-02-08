package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findAllByAtivoEquals(String ativo);

    Usuario findByEmailAndSenhaAndAtivoEquals(String email, String senha, String ativo);

    Usuario findByUsuarioIdAndAtivoEquals(Integer id, String s);

    Optional<Usuario> findByLoginAndSenha(String login, String senha);

    Optional<Usuario> findByLogin(String login);
}
