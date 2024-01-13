package repository;

import database.BancoDeDados;
import database.GeradorID;
import infra.exceptions.DataNotFoundException;
import infra.exceptions.DbException;
import infra.exceptions.UnauthorizedOperationException;
import models.Terreno;

import java.sql.*;
import java.time.Instant;

public class TerrenoRepository implements DaoRepository<Terreno>{
    Connection connection;
    @Override
    public void adicionar(Terreno terreno) {
        try {
            connection = BancoDeDados.criaConexao();
            Integer proximoId = GeradorID.getProximoTerrenoId(connection);
            terreno.setId(proximoId);

            String sqlQuery = """
                    INSERT INTO TERRENOS(
                    TERRENO_ID, TITULO, DESCRICAO, DONO_ID, ENDERECO_TERRENO_ID, PRECO, TAMANHO, DISPONIVEL, CRIADO, EDITADO 
                    )VALUES (?,?,?,?,?,?,?,?,?,?)
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, terreno.getId());
            stmt.setString(2, terreno.getTitulo());
            stmt.setString(3, terreno.getDescricao());
            stmt.setInt(4, terreno.getProprietarioID());
            stmt.setInt(5, terreno.getEnderecoID());
            stmt.setDouble(6, terreno.getPreco());
            stmt.setString(7, terreno.getTamanho());
            stmt.setString(8, terreno.getDisponivel());
            stmt.setString(9, Instant.now().toString());
            stmt.setString(10, Instant.now().toString());

            if (stmt.executeUpdate() == 0) throw new UnauthorizedOperationException("Não foi possível cadastrar novo Endereço");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public void alterar(int id, Terreno terreno) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = """
                    UPDATE TERRENOS
                        set
                         TITULO = ?,
                         DESCRICAO= ?,
                         DONO_ID= ?,
                         ENDERECO_TERRENO_ID= ?,
                         PRECO= ?,
                         TAMANHO= ?,
                         DISPONIVEL= ?,
                         EDITADO= ?                 
                    WHERE TERRENO_ID = ?
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setString(1, terreno.getTitulo());
            stmt.setString(2, terreno.getDescricao());
            stmt.setInt(3, terreno.getProprietarioID());
            stmt.setInt(4, terreno.getEnderecoID());
            stmt.setDouble(5, terreno.getPreco());
            stmt.setString(6, terreno.getTamanho());
            stmt.setString(7, terreno.getDisponivel());
            stmt.setString(8, Instant.now().toString());
            stmt.setInt(9, id);

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Dados do Usuário Não Encontrado. ID: " + id);

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public void deletar(int id) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = "UPDATE TERRENOS set DISPONIVEL= ? WHERE TERRENO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1,"N");

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Dados do Usuário Não Encontrado. ID: " + id);

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public Terreno resgatarDadosPorId(int id) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = "SELECT * FROM TERRENOS WHERE TERRENO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Terreno(
                        result.getInt("TERRENO_ID"),
                        result.getString("TITULO"),
                        result.getString("DESCRICAO"),
                        result.getInt("DONO_ID"),
                        result.getInt("ENDERECO_TERRENO_ID"),
                        result.getDouble("PRECO"),
                        result.getString("TAMANHO"),
                        result.getString("DISPONIVEL")
                );
            }
            throw new DataNotFoundException("Não foi possível resgatar dados");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
}
