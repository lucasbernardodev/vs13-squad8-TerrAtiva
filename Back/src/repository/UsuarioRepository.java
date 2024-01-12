package repository;

import database.BancoDeDados;
import database.GeradorID;
import infra.exceptions.DbException;
import infra.exceptions.UnauthorizedOperationException;
import models.Usuario;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements DaoRepository<Usuario> {
    Connection conn;

    @Override
    public void adicionar(Usuario obj) {
        try {
            conn = BancoDeDados.criaConeccao();

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
            stmt.setString(9, obj.getAtivo());
            stmt.setString(10, obj.getCelular());
            stmt.setString(11, obj.getTelefoneFixo());
            stmt.setTimestamp(12, Timestamp.from(obj.getCriado()));
            stmt.setTimestamp(13, Timestamp.from(obj.getEditado()));

            int res = stmt.executeUpdate();
            if (res == 0) throw new UnauthorizedOperationException("Não foi possivel adicionar");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(conn);
        }
    }

    @Override
    public void alterar(int id, Usuario obj) {
        try {
            conn = BancoDeDados.criaConeccao();
            String sqlQuery = """
                    update USUARIOS
                    set  (NOME= ?, SOBRENOME= ?, EMAIL= ?, SENHA= ?, CPF= ?, DATA_NASCIMENTO= ?, SEXO= ?, CELULAR= ?, TELEFONE_FIXO= ?, EDITADO= ?)
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
            stmt.setString(8, obj.getCelular());
            stmt.setString(9, obj.getTelefoneFixo());
            stmt.setTimestamp(10, Timestamp.from(Instant.now()));
            stmt.setInt(11, obj.getUsuarioId());
            int res = stmt.executeUpdate();
            if (res == 0) throw new UnauthorizedOperationException("Erro ocurrido na alteracao");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(conn);
        }
    }

    @Override
    public void deletar(int id) {
        try {
            conn = BancoDeDados.criaConeccao();

            String sqlQuery = """
            UPDATE FROM USUARIOS SET ATIVO = 'N' WHERE USUARIO_ID = ?
            """;

            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1,id);

            int res = stmt.executeUpdate();
            if (res == 0) throw new UnauthorizedOperationException("Não foi possivel remover");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(conn);
        }
    }

    @Override
    public Usuario resgatarDadosPorId(int id) {
        try {

            conn = BancoDeDados.criaConeccao();

            String sqlQuery = " SELECT * FROM USUARIOS WHERE Id = " + id;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                Usuario res = new Usuario();
                res.setUsuarioId(rs.getInt("USUARIO_ID"));
                res.setNome(rs.getString("NOME"));
                res.setSobrenome(rs.getString("SOBRENOME"));
                res.setEmail(rs.getString("EMAIL"));
                res.setCpf(rs.getString("CPF"));
                res.setDataNascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
                res.setSexo(rs.getString("SEXO"));
                res.setAtivo(rs.getString("ATIVO"));
                res.setCelular(rs.getString("CELULAR"));
                res.setTelefoneFixo(rs.getString("TELEFONE_FIXO"));
                return res;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(conn);
        }
    }

    public List<Usuario> resgatarUsuariosTodos() {
        ResultSet rs = null;
        Statement st = null;
        try {
            conn = BancoDeDados.criaConeccao();

            String sqlQuery = " SELECT * FROM LOCADOR WHERE ATIVO = 'S'";
            st = conn.createStatement();
            rs = st.executeQuery(sqlQuery);
            List<Usuario> usuarioLista = new ArrayList<>();
            while (rs.next()) {
                Usuario res = new Usuario();
                res.setUsuarioId(rs.getInt("USUARIO_ID"));
                res.setNome(rs.getString("NOME"));
                res.setSobrenome(rs.getString("SOBRENOME"));
                res.setEmail(rs.getString("EMAIL"));
                res.setCpf(rs.getString("CPF"));
                res.setDataNascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
                res.setSexo(rs.getString("SEXO"));
                res.setAtivo(rs.getString("ATIVO"));
                res.setCelular(rs.getString("CELULAR"));
                res.setTelefoneFixo(rs.getString("TELEFONE_FIXO"));
                usuarioLista.add(res);
            }
            return usuarioLista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaResultSet(rs);
            BancoDeDados.fechaStatment(st);
            BancoDeDados.fechaConexao(conn);
        }
    }
}
