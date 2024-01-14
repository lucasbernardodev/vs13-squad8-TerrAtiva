package repository;

import database.BancoDeDados;
import infra.exceptions.DataNotFoundException;
import infra.exceptions.DbException;
import infra.exceptions.UnauthorizedOperationException;
import models.Aluguel;

import java.sql.*;
import java.time.Instant;

public class AluguelRepository  implements DaoRepository<Aluguel>{
    Connection connection;
    @Override
    public void adicionar(Aluguel AluguelRequest) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = """
                    INSERT INTO ALUGUEL_PAGAMENTOS
                        (PAGAMENTO_ID, MENSALIDADE_ID, MES_REFERENCIA, EMISSAO, VENCIMENTO,
                        TAXAS, CODIGO_BARRAS_BOLETO, DATA_PAGAMENTO, PAGO, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, AluguelRequest.getPagamentoID());
            stmt.setInt(2, AluguelRequest.getMensalidadeID());
            stmt.setInt(3, AluguelRequest.getMesReferencia());
            stmt.setObject(4, AluguelRequest.getDataEmissao());
            stmt.setObject(5, AluguelRequest.getDataVencimento());
            stmt.setDouble(6, AluguelRequest.getTaxas());
            stmt.setString(7, AluguelRequest.getCodigoBarras());
            stmt.setObject(8, AluguelRequest.getDataPagamento());
            stmt.setString(3, AluguelRequest.getPago());
            stmt.setString(9, Instant.now().toString());
            stmt.setString(10, Instant.now().toString());

            if (stmt.executeUpdate() == 0) throw new UnauthorizedOperationException("Não foi possível cadastrar novo Aluguel");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
    @Override
    public void alterar(int id, Aluguel AluguelRequest) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = """
                    UPDATE ALUGUEL_PAGAMENTOS
                        set
                        MENSALIDADE_ID = ?,
                        MES_REFERENCIA = ?,
                        EMISSAO = ?,
                        VENCIMENTO = ?,
                        TAXAS = ?,
                        CODIGO_BARRAS_BOLETO = ?,
                        DATA_PAGAMENTO = ?,
                        PAGO = ?
                        EDITADO = ?
                                        
                    WHERE PAGAMENTO_ID = ?
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, AluguelRequest.getMensalidadeID());
            stmt.setInt(2, AluguelRequest.getMesReferencia());
            stmt.setObject(3, AluguelRequest.getDataEmissao());
            stmt.setObject(4, AluguelRequest.getDataVencimento());
            stmt.setDouble(5, AluguelRequest.getTaxas());
            stmt.setString(6, AluguelRequest.getCodigoBarras());
            stmt.setObject(7, AluguelRequest.getDataPagamento());
            stmt.setString(8, AluguelRequest.getPago());
            stmt.setString(9, Instant.now().toString());

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Dados do Aluguel Não Encontrado. ID: " + id);

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
            String sqlQuery = "DELETE FROM ALUGUEL_PAGAMENTOS WHERE PAGAMENTO_ID = " + id;
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate(sqlQuery);
            if (rowsAffected == 0)
                throw new UnauthorizedOperationException("Operação Deletar Aluguel Não Autorizada");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public Aluguel resgatarDadosPorId(int id) {
        try {
            connection = BancoDeDados.criaConexao();
            String sqlQuery = "SELECT * FROM ALUGUEL_PAGAMENTOS WHERE PAGAMENTO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Aluguel(
                        result.getInt("MENSALIDADE_ID"),
                        result.getInt("MES_REFERENCIA"),
                        result.getDate("EMISSAO").toLocalDate(),
                        result.getDate("VENCIMENTO").toLocalDate(),
                        result.getDouble("TAXAS"),
                        result.getString("CODIGO_BARRAS_BOLETO"),
                        result.getDate("DATA_PAGAMENTO").toLocalDate()
                );
            }
            throw new DataNotFoundException("Não foi possível resgatar os dados do Aluguel.");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
}
