package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.database.GeradorID;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository
public class EnderecoTerrenosRepository implements DaoRepository<EnderecoTerrenos> {
    Connection connection;
    BancoDeDados bancoConection;

    public EnderecoTerrenosRepository(BancoDeDados bancoDeDados) {
        this.bancoConection = bancoDeDados;
    }
    @Override
    public EnderecoTerrenos adicionar(EnderecoTerrenos enderecoTerrenosRequest){
        try {
            connection = bancoConection.criaConexao();
            Integer proximoId = GeradorID.getProximoEnderecoTerrenos(connection);
            enderecoTerrenosRequest.setId(proximoId);
            String sqlQuery = """
                    INSERT INTO ENDERECO_TERRENOS
                        (ENDERECO_TERRENO_ID, LOGRADOURO, NUMERO, COMPLEMENTO,
                           BAIRRO, MUNICIPIO_COD_IBGE, CEP, LOCALIZACAO, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, enderecoTerrenosRequest.getId());
            stmt.setString(2, enderecoTerrenosRequest.getLogradouro());
            stmt.setInt(3, enderecoTerrenosRequest.getNumero());
            stmt.setString(4, enderecoTerrenosRequest.getComplemento());
            stmt.setString(5, enderecoTerrenosRequest.getBairro());
            stmt.setInt(6, enderecoTerrenosRequest.getCodigoMunicipioIBGE());
            stmt.setInt(7, enderecoTerrenosRequest.getCep());
            stmt.setString(8, enderecoTerrenosRequest.getLocalizacao());
            stmt.setString(9, Instant.now().toString());
            stmt.setString(10, Instant.now().toString());

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Não foi possível cadastrar novo Endereço");
            BancoDeDados.fechaConexao(connection);
            return enderecoTerrenosRequest;

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }




    public EnderecoTerrenos alterar(Integer id, EnderecoTerrenos enderecoTerrenosRequest) {
        try {
            connection = bancoConection.criaConexao();
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

            stmt.setString(1, enderecoTerrenosRequest.getLogradouro());
            stmt.setInt(2, enderecoTerrenosRequest.getNumero());
            stmt.setString(3, enderecoTerrenosRequest.getComplemento());
            stmt.setString(4, enderecoTerrenosRequest.getBairro());
            stmt.setInt(5, enderecoTerrenosRequest.getCodigoMunicipioIBGE());
            stmt.setInt(6, enderecoTerrenosRequest.getCep());
            stmt.setString(7, enderecoTerrenosRequest.getLocalizacao());
            stmt.setString(8, Instant.now().toString());
            stmt.setInt(9, id);

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Dados do Usuário Não Encontrado. ID");

            BancoDeDados.fechaConexao(connection);
            return enderecoTerrenosRequest;

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public void deletar(int id) {
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = "DELETE FROM ENDERECO_TERRENOS WHERE ENDERECO_TERRENO_ID = " + id;
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate(sqlQuery);
            if (rowsAffected == 0)
                throw new DataNotFoundException("Operação de Deletar Endereço Não Autorizada");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public EnderecoTerrenos resgatarDadosPorId(int id){
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = "SELECT * FROM ENDERECO_TERRENOS WHERE ENDERECO_TERRENO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new EnderecoTerrenos(
                        result.getInt("ENDERECO_TERRENO_ID"),
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

    public EnderecoTerrenos alterar(EnderecoTerrenos obj) throws SQLException {
        return null;
    }
}