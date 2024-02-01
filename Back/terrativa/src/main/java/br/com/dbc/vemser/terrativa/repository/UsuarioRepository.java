package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.database.GeradorID;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioLoginDTO;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findAllByAtivoEquals(String ativo);

    Usuario findAllByUsuarioIdAndAtivoEquals(Integer id, String ativo);

    Usuario findByEmailAndSenhaAndAtivoEquals(String email, String senha, String ativo);

}
