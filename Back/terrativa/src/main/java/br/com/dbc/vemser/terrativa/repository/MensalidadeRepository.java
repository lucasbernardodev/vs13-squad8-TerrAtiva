package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import br.com.dbc.vemser.terrativa.exceptions.UnvailableOperationException;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository

public class MensalidadeRepository implements DaoRepository<Mensalidade> {

    Connection connection;
    BancoDeDados bancoConection;

    public MensalidadeRepository(BancoDeDados bancoDeDados) {
        this.bancoConection = bancoDeDados;
    }

    @Override
    public void adicionar(Mensalidade mensalidade) {
        throw new UnvailableOperationException("Essa Funcionalidade não está Disponível");
    }

    @Override
    public void alterar(Mensalidade mensalidade) {
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = """
                    UPDATE MENSALIDADES
                        set
                        CONTRATO_ID = ?,
                        VALOR_MENSAL = ?,
                        ANO_EXERCICIO = ?             
                    WHERE MENSALIDADE_ID = ?
                    """;

            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, mensalidade.getContratoID());
            stmt.setDouble(2, mensalidade.getValorMensal());
            stmt.setInt(3, mensalidade.getAnoExercicio());
            stmt.setInt(4, mensalidade.getMensalidadeID());


            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Dados do Usuário Não Encontrado. ID");

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
            String sqlQuery = "DELETE FROM MENSALIDADES WHERE MENSALIDADE_ID = " + id;
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate(sqlQuery);
            if (rowsAffected == 0)
                throw new UnauthorizedOperationException("Operação de Deletar Mensalidade Não Autorizada");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public Mensalidade resgatarDadosPorId(int id) {
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = "SELECT * FROM MENSALIDADES WHERE MENSALIDADE_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();


            if (result.next()) {
               return new Mensalidade(
                  result.getInt("MENSALIDADE_ID"),
                  result.getInt("CONTRATO_ID"),
                  result.getInt("VALOR_MENSAL"),
                  result.getInt("ANO_EXERCICIO")
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


