package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.database.GeradorID;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class ContratoRepository implements DaoRepository<Contrato>{

    private static Connection connection;
    private final BancoDeDados bancoConection;

    //TODO: implementar o método de adicionar contrato
    @Override
    public Contrato adicionar(Contrato contratoRequest) {
        return contratoRequest;
    }

    public Integer getNextId() {
        try {
            connection = bancoConection.criaConexao();
            return GeradorID.getProximoTerrenoId(connection);

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public Contrato alterar(Contrato contratoRequest) {
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

            stmt.setInt(1, contratoRequest.getLocatarioID());
            stmt.setInt(2, contratoRequest.getTerrenoID());
            stmt.setDate(4, Date.valueOf(contratoRequest.getDataAssinatura()));
            stmt.setDate(5, Date.valueOf(contratoRequest.getDataInicio()));
            stmt.setDate(6, Date.valueOf(contratoRequest.getDataFinal()));
            stmt.setInt(7, contratoRequest.getDataVencimentoAluguel());
            stmt.setString(8, Instant.now().toString());
            stmt.setInt(9, contratoRequest.getId());

            if (stmt.executeUpdate() == 0) {
                throw new DataNotFoundException("Dados do Contrato Não Encontrado. ID");
            }
            BancoDeDados.fechaConexao(connection);
            return contratoRequest;

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
                LocalDate localDate;
                if (result.getDate("DATA_FINAL") == null){
                    localDate = null;
                }else {
                    localDate = result.getDate("DATA_FINAL").toLocalDate();
                }
                return new Contrato(
                        result.getInt("CONTRATO_ID"),
                        result.getInt("LOCATARIO_ID"),
                        result.getInt("TERRENO_ID"),
                        result.getString("ATIVO"),
                        result.getDate("DATA_ASSINATURA").toLocalDate(),
                        result.getDate("DATA_INICIO").toLocalDate(),
                        localDate,
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
