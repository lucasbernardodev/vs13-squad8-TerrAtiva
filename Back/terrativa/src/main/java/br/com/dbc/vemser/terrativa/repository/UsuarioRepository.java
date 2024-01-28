package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.database.GeradorID;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioLoginDTO;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UsuarioRepository implements DaoRepository<Usuario> {

    private static Connection conn;
    private final BancoDeDados bancoConection;

    public List<Usuario> listarUsuarios() {
        ResultSet rs;
        Statement st;
        try {
            conn = bancoConection.criaConexao();
            String sqlQuery = " SELECT * FROM USUARIOS WHERE ATIVO = 'S'";
            st = conn.createStatement();
            rs = st.executeQuery(sqlQuery);
            List<Usuario> usuarioLista = new ArrayList<>();

            while (rs.next()) {
                usuarioLista.add(mapperUsuario(rs));
            }
            BancoDeDados.fechaConexao(conn);
            return usuarioLista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Usuario resgatarDadosPorId(int id) {
        try {

            conn = bancoConection.criaConexao();

            String sqlQuery = "SELECT * FROM USUARIOS WHERE USUARIO_ID = " + id;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);

            if (rs.next()) {
                return mapperUsuario(rs);
            }

            throw new UnauthorizedOperationException("Usuario não encontrado");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(conn);
        }
    }
    @Override
    public Usuario adicionar(Usuario obj) {
        try {
            conn = bancoConection.criaConexao();

            Integer proximoId = GeradorID.getProximoUsuarioId(conn);
            obj.setUsuarioId(proximoId);
            String sqlQuery = """
                    insert into
                    USUARIOS (USUARIO_ID, NOME, SOBRENOME, EMAIL, SENHA, CPF, DATA_NASCIMENTO, SEXO, ATIVO, CELULAR, TELEFONE_FIXO, CRIADO, EDITADO)
                    values (?,?,?,?,?,?,?,?,?,?,?,?,?)
                    """;


            PreparedStatement stmt = conn.prepareStatement(sqlQuery);

            stmt.setInt(1, obj.getUsuarioId());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getSobrenome());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCpf());
            stmt.setDate(7, Date.valueOf(obj.getDataNascimento()));
            stmt.setString(8, obj.getSexo());
            stmt.setString(9, "S");
            stmt.setString(10, obj.getCelular());
            stmt.setString(11, obj.getTelefoneFixo());
            stmt.setString(12, Instant.now().toString());
            stmt.setString(13, Instant.now().toString());

            int res = stmt.executeUpdate();
            if (res == 0) throw new UnauthorizedOperationException("Não foi possivel adicionar");
            BancoDeDados.fechaConexao(conn);
            return obj;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Usuario alterar(Usuario obj) {
        try {
            conn = bancoConection.criaConexao();
            String sqlQuery = """
                    update USUARIOS
                    set  NOME= ?,
                         SOBRENOME= ?,
                         EMAIL= ?,
                         SENHA= ?,
                         CPF= ?,
                         DATA_NASCIMENTO= ?,
                         SEXO= ?,
                         ATIVO= ?,
                         CELULAR= ?,
                         TELEFONE_FIXO= ?,
                         EDITADO= ?
                    where USUARIO_ID = ?
                    """;

            PreparedStatement stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSobrenome());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getSenha());
            stmt.setString(5, obj.getCpf());
            stmt.setDate(6, Date.valueOf(obj.getDataNascimento()));
            stmt.setString(7, obj.getSexo());
            stmt.setString(8, obj.getAtivo());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getTelefoneFixo());
            stmt.setString(11, Instant.now().toString());
            stmt.setInt(12, obj.getUsuarioId());

            int res = stmt.executeUpdate();
            if (res == 0) throw new UnauthorizedOperationException("Erro ocurrido na alteracao");
            BancoDeDados.fechaConexao(conn);
            return obj;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(conn);
        }
    }

    @Override
    public void deletar(int id) throws UnauthorizedOperationException{
        try {
            conn = bancoConection.criaConexao();

            String sqlQuery = "UPDATE USUARIOS SET ATIVO = 'N' WHERE USUARIO_ID = ?";

            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            if (res == 0) throw new UnauthorizedOperationException("Não foi possivel remover");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(conn);
        }
    }

    public Usuario loginUsuario(RequestUsuarioLoginDTO usuario) {
        try {

            conn = bancoConection.criaConexao();

            String sqlQuery = """
                    SELECT * FROM USUARIOS WHERE EMAIL = ? AND SENHA = ?
                    """;
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuarioResponse = new Usuario();
                usuarioResponse.setUsuarioId(rs.getInt("USUARIO_ID"));
                usuarioResponse.setNome(rs.getString("NOME"));
                usuarioResponse.setSobrenome(rs.getString("SOBRENOME"));
                usuarioResponse.setEmail(rs.getString("EMAIL"));
                usuarioResponse.setCpf(rs.getString("CPF"));
                usuarioResponse.setDataNascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
                usuarioResponse.setSexo(rs.getString("SEXO"));
                usuarioResponse.setAtivo(rs.getString("ATIVO"));
                usuarioResponse.setCelular(rs.getString("CELULAR"));
                usuarioResponse.setTelefoneFixo(rs.getString("TELEFONE_FIXO"));
                return usuarioResponse;
            }
            throw new DbException("Usuario não encontrado");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(conn);
        }
    }

    private Usuario mapperUsuario(ResultSet rs) throws SQLException {
        Usuario usuarioResponse = new Usuario();
        usuarioResponse.setUsuarioId(rs.getInt("USUARIO_ID"));
        usuarioResponse.setNome(rs.getString("NOME"));
        usuarioResponse.setSobrenome(rs.getString("SOBRENOME"));
        usuarioResponse.setEmail(rs.getString("EMAIL"));
        usuarioResponse.setSenha(rs.getString("SENHA"));
        usuarioResponse.setCpf(rs.getString("CPF"));
        usuarioResponse.setDataNascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
        usuarioResponse.setSexo(rs.getString("SEXO"));
        usuarioResponse.setAtivo(rs.getString("ATIVO"));
        usuarioResponse.setCelular(rs.getString("CELULAR"));
        usuarioResponse.setTelefoneFixo(rs.getString("TELEFONE_FIXO"));
        return usuarioResponse;
    }

}
