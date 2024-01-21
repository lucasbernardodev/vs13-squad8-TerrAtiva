package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import br.com.dbc.vemser.terrativa.exceptions.UnvailableOperationException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository

public class ContratoRepository implements DaoRepository<Contrato>{

    Connection connection;
    BancoDeDados bancoConection;

    public ContratoRepository(BancoDeDados bancoDeDados) {
        this.bancoConection = bancoDeDados;
    }
    @Override
    public Contrato adicionar(Contrato ContratoRequest) {
        throw new UnvailableOperationException("Essa Funcionalidade não está Disponível");
    }
    @Override
    public void alterar(Contrato ContratoRequest) {
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = """

                UPDATE CONTRATOS
                SET
                    LOCATARIO_ID = ?,
                    TERRENO_ID = ?,
                    ATIVO = ?,
                    DATA_ASSINATURA = ?,
                    DATA_INICIO = ?,
                    DATA_FINAL = ?,
                    DIA_VENCIMENTO_ALUGUEL = ?,
                    EDITADO = ?
                WHERE CONTRATO_ID = ?
                """;
                      
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, ContratoRequest.getProprietarioID());
            stmt.setInt(2, ContratoRequest.getTerrenoID());
            stmt.setDate(4, Date.valueOf(ContratoRequest.getDataAssinatura()));
            stmt.setDate(5, Date.valueOf(ContratoRequest.getDataInicio()));
            stmt.setDate(6, Date.valueOf(ContratoRequest.getDataFinal()));
            stmt.setInt(7, ContratoRequest.getDataVencimentoAluguel());
            stmt.setString(8, Instant.now().toString());
            stmt.setInt(9, ContratoRequest.getId());

            if (stmt.executeUpdate() == 0) {
                throw new DataNotFoundException("Dados do Contrato Não Encontrado. ID");
            }

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
            String sqlQuery = "UPDATE CONTRATOS " +
                    "SET ATIVO = 'N'" +
                    "WHERE CONTRATO_ID = " + id;
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
            connection = bancoConection.criaConexao();

            String sqlQuery = "SELECT * FROM CONTRATOS WHERE CONTRATO_ID = " + id;

            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Contrato(
                        result.getInt("LOCATARIO_ID"),
                        result.getInt("TERRENO_ID"),
                        result.getDate("DATA_ASSINATURA").toLocalDate(),
                        result.getDate("DATA_INICIO").toLocalDate(),
                        result.getDate("DATA_FINAL").toLocalDate(),
                        result.getInt("DIA_VENCIMENTO_ALUGUEL")
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
