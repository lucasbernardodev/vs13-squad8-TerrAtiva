package repository;

import database.BancoDeDados;
import infra.exceptions.DataNotFoundException;
import infra.exceptions.DbException;
import infra.exceptions.UnauthorizedOperationException;
import models.Endereco;
import models.EnderecoTerrenos;

import java.sql.*;
import java.time.Instant;

public class EnderecoTerrenosRepository implements DaoRepository<EnderecoTerrenos> {
    Connection connection;

    @Override
    public void adicionar(EnderecoTerrenos enderecoRequest) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = """
                    INSERT INTO ENDERECO_TERRENOS
                        (ENDERECO_TERRENO_ID, USUARIO_ID, LOGRADOURO, NUMERO, COMPLEMENTO,
                           BAIRRO, MUNICIPIO_COD_IBGE, CEP, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, enderecoTerrenosRequest.getId());
            stmt.setInt(2, enderecoTerrenosRequest.getUsuarioID());
            stmt.setString(3, enderecoTerrenosRequest.getLogradouro());
            stmt.setInt(4, enderecoTerrenosRequest.getNumero());
            stmt.setString(5, enderecoTerrenosRequest.getComplemento());
            stmt.setString(6, enderecoTerrenosRequest.getBairro());
            stmt.setInt(7, enderecoTerrenosRequest.getCodigoMunicipioIBGE());
            stmt.setInt(8, enderecoTerrenosRequest.getCep());
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
    public void alterar(int id, EnderecoTerrenos enderecoRequest) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = """
                    UPDATE ENDERECO_TERRENOS
                        set
                        LOGRADOURO = ?,
                        NUMERO = ?,
                        COMPLEMENTO = ?,
                        BAIRRO = ?,
                        MUNICIPIO_COD_IBGE = ?,
                        CEP = ?,
                        LOCALIZACAO = ?,
                        EDITADO = ?
                    WHERE ENDERECO_TERRENO_ID = ?
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, enderecoTerrenosRequest.getUsuarioID());
            stmt.setString(2, enderecoTerrenosRequest.getLogradouro());
            stmt.setInt(3, enderecoTerrenosRequest.getNumero());
            stmt.setString(4, enderecoTerrenosRequest.getComplemento());
            stmt.setString(5, enderecoTerrenosRequest.getBairro());
            stmt.setInt(6, enderecoTerrenosRequest.getCodigoMunicipioIBGE());
            stmt.setInt(7, enderecoTerrenosRequest.getCep());
            stmt.setString(8, Instant.now().toString());
            stmt.setInt(9, enderecoTerrenosRequest.getId());

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
            String sqlQuery = "DELETE FROM ENDERECO_TERRENOS WHERE ENDERECO_TERRENO_ID = " + id;
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate(sqlQuery);
            if (rowsAffected == 0)
                throw new UnauthorizedOperationException("Operação de Deletar Endereço Não Autorizada");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public EnderecoTerrenos resgatarDadosPorId(int id) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = "SELECT * FROM ENDERECO_TERRENOS WHERE ENDERECO_TERRENO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new EnderecoTerrenos(
                        result.getString("LOGRADOURO"),
                        result.getInt("NUMERO"),
                        result.getString("COMPLEMENTO"),
                        result.getString("BAIRRO"),
                        result.getInt("MUNICIPIO_COD_IBGE"),
                        result.getInt("CEP"),
                        result.getString("LOCALIZACAO")
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