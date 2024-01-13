package repository;

import database.BancoDeDados;
import infra.exceptions.DataNotFoundException;
import infra.exceptions.DbException;
import infra.exceptions.UnauthorizedOperationException;
import models.Contrato;


import java.sql.*;
import java.time.Instant;

public class ContratoRepository implements DaoRepository<Contrato>{

    Connection connection;
    @Override
    public void adicionar(Contrato ContratoRequest) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = """
                    INSERT INTO CONTRATOS
                        (CONTRATO_ID, LOCATARIO_ID, TERRENO_ID, ATIVO, DATA_ASSINATURA,
                        DATA_INICIO, DATA_FINAL, DIA_VENCIMENTO_ALUGUEL, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, ContratoRequest.getId());
            stmt.setInt(2, ContratoRequest.getProprietarioID());
            stmt.setInt(3, ContratoRequest.getTerrenoID());
            stmt.setString(4, ContratoRequest.getAtivo());
            stmt.setObject(5, ContratoRequest.getDataAssinatura());
            stmt.setObject(6, ContratoRequest.getDataInicio());
            stmt.setObject(7, ContratoRequest.getDataFinal());
            stmt.setObject(8, ContratoRequest.getDataVencimentoAluguel());
            stmt.setString(9, Instant.now().toString());
            stmt.setString(10, Instant.now().toString());

            if (stmt.executeUpdate() == 0) throw new UnauthorizedOperationException("Não foi possível cadastrar novo Contrato");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
    @Override
    public void alterar(int id, Contrato ContratoRequest) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = """
                    UPDATE CONTRATOS
                        set
                        INSERT INTO CONTRATOS
                        PROPRIETARIO_ID = ?,
                        TERRENO_ID = ?,
                        ATIVO = ?,
                        DATA_ASSINATURA = ?,
                        DATA_INICIO = ?,
                        DATA_FINAL = ?,
                        DATA_VENCIMENTO_ALUGUEL = ?,
                        EDITADO = ?
                                        
                    WHERE CONTRATO_ID = ?
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, ContratoRequest.getProprietarioID());
            stmt.setInt(2, ContratoRequest.getTerrenoID());
            stmt.setString(3, ContratoRequest.getAtivo());
            stmt.setObject(4, ContratoRequest.getDataAssinatura());
            stmt.setObject(5, ContratoRequest.getDataInicio());
            stmt.setObject(6, ContratoRequest.getDataFinal());
            stmt.setObject(7, ContratoRequest.getDataVencimentoAluguel());
            stmt.setString(9, Instant.now().toString());

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Dados do Contrato Não Encontrado. ID: " + id);

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
            String sqlQuery = "DELETE FROM CONTRATOS WHERE CONTRATO_ID = " + id;
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate(sqlQuery);
            if (rowsAffected == 0)
                throw new UnauthorizedOperationException("Operação Deletar Contrato Não Autorizada");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public Contrato resgatarDadosPorId(int id) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = "SELECT * FROM CONTRATOS WHERE ENDERECO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Contrato(
                        result.getInt("LOCATARIO_ID"),
                        result.getInt("TERRENO_ID"),
                        result.getString("ATIVO"),
                        result.getDate("DATA_ASSINATURA").toLocalDate(),
                        result.getDate("DATA_INICIO").toLocalDate(),
                        result.getDate("DATA_FINAL").toLocalDate(),
                        result.getDate("DIA_VENCIMENTO_ALUGUEL").toLocalDate()
                );
            }
            throw new DataNotFoundException("Não foi possível resgatar os dados do Contrato.");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
}
