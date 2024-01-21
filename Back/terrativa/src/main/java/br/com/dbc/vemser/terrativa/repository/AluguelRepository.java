package br.com.dbc.vemser.terrativa.repository;


import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.entity.Aluguel;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import br.com.dbc.vemser.terrativa.exceptions.UnvailableOperationException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository
public class AluguelRepository  implements DaoRepository<Aluguel>{
    Connection connection;
    BancoDeDados bancoConection;

    public AluguelRepository(BancoDeDados bancoDeDados) {
    this.bancoConection = bancoDeDados;
    }
    @Override
    public Aluguel adicionar(Aluguel AluguelRequest) {
        throw new UnvailableOperationException("Essa Funcionalidade não está Disponível");
    }
    @Override
    public void alterar(Aluguel AluguelRequest) throws SQLException {
            connection = bancoConection.criaConexao();
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
                        PAGO = ?,
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
        stmt.setInt(10, AluguelRequest.getPagamentoID());

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Dados do Aluguel Não Encontrado. ID")  ;

            BancoDeDados.fechaConexao(connection);

    }
    @Override
    public void deletar(int id) throws SQLException {
            connection = bancoConection.criaConexao();
            String sqlQuery = "DELETE FROM ALUGUEL_PAGAMENTOS WHERE PAGAMENTO_ID = " + id;
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate(sqlQuery);
            if (rowsAffected == 0)
                throw new UnauthorizedOperationException("Operação Deletar Aluguel Não Autorizada");


            BancoDeDados.fechaConexao(connection);
    }

    @Override
    public Aluguel resgatarDadosPorId(int id) throws SQLException {

            connection = bancoConection.criaConexao();
            String sqlQuery = "SELECT * FROM ALUGUEL_PAGAMENTOS WHERE PAGAMENTO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            Aluguel aluguel = new Aluguel(
//                        result.getInt("MENSALIDADE_ID"),
//                        result.getInt("MES_REFERENCIA").toString(),
//                        result.getDate("EMISSAO").toLocalDate(),
//                        result.getDate("VENCIMENTO").toLocalDate(),
//                        result.getDouble("TAXAS"),
//                        result.getString("CODIGO_BARRAS_BOLETO"),
//                        result.getDate("DATA_PAGAMENTO").toLocalDate(),
//                        result.getString("PAGO"),
//                        result.getInt("PAGAMENTO_ID")
                );

            BancoDeDados.fechaConexao(connection);

            return aluguel;
    }
}
