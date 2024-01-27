package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.database.GeradorID;
import br.com.dbc.vemser.terrativa.entity.Aluguel;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;

@Repository
@RequiredArgsConstructor
public class TerrenoRepository implements DaoRepository<Terreno> {
    private static Connection connection;
    private final BancoDeDados bancoConection;

    @Override
    public Terreno adicionar(Terreno terreno) {
        try {
            connection = bancoConection.criaConexao();
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

            if (stmt.executeUpdate() == 0)
                throw new UnauthorizedOperationException("Não foi possível cadastrar novo Endereço");
            BancoDeDados.fechaConexao(connection);
            return terreno;

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public Terreno alterar(Terreno terreno) {
        try {
            connection = bancoConection.criaConexao();
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
            stmt.setInt(9, terreno.getId());

            if (stmt.executeUpdate() == 0)
                throw new DataNotFoundException("Dados do Usuário Não Encontrado. ID");
            BancoDeDados.fechaConexao(connection);
            return terreno;

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public void deletar(int id) throws UnauthorizedOperationException{
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = "UPDATE TERRENOS set DISPONIVEL= ? WHERE TERRENO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, "N");

            int res = stmt.executeUpdate();
            if (res == 0) throw new UnauthorizedOperationException("Não foi possivel remover");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public Terreno resgatarDadosPorId(int id) {
        try {
            connection = bancoConection.criaConexao();
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

    public void arrendarTerreno(Contrato contrato, Terreno terreno) {
        try {
            // PASSO 1: GERAR CONTRATO
            connection = bancoConection.criaConexao();
            int newContratoID = GeradorID.getProximoContrato(connection);
            int newMensalidadeID = GeradorID.getProximoMensalidade(connection);

            String sqlQueryContrato = """
                    INSERT INTO CONTRATOS
                        (CONTRATO_ID, LOCATARIO_ID, TERRENO_ID, ATIVO, DATA_ASSINATURA,
                        DATA_INICIO, DATA_FINAL, DIA_VENCIMENTO_ALUGUEL, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmtContrato = connection.prepareStatement(sqlQueryContrato);
            stmtContrato.setInt(1, newContratoID);
            stmtContrato.setInt(2, contrato.getLocatarioID());
            stmtContrato.setInt(3, terreno.getId());
            stmtContrato.setString(4, "S");
            stmtContrato.setDate(5, Date.valueOf(contrato.getDataAssinatura()));
            stmtContrato.setDate(6, Date.valueOf(contrato.getDataInicio()));
            stmtContrato.setDate(7, Date.valueOf(contrato.getDataFinal()));
            stmtContrato.setInt(8, contrato.getDataVencimentoAluguel());
            stmtContrato.setString(9, Instant.now().toString());
            stmtContrato.setString(10, Instant.now().toString());

            if (stmtContrato.executeUpdate() == 0)
                throw new UnauthorizedOperationException("Não foi possível Criar um Novo Contrato");

            // PASSO 2: CRIAR Mensalidade

            String sqlQueryMensalidade = """
                    INSERT INTO MENSALIDADES
                        (MENSALIDADE_ID, CONTRATO_ID, VALOR_MENSAL, ANO_EXERCICIO, CRIADO, EDITADO, ATIVO)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmtMensalidade = connection.prepareStatement(sqlQueryMensalidade);
            stmtMensalidade.setInt(1, newMensalidadeID);
            stmtMensalidade.setInt(2, newContratoID);
            stmtMensalidade.setDouble(3, terreno.getPreco());
            stmtMensalidade.setInt(4, Calendar.getInstance().get(Calendar.YEAR));
            stmtMensalidade.setString(5, Instant.now().toString());
            stmtMensalidade.setString(6, Instant.now().toString());
            stmtMensalidade.setString(7,"S" );

            if (stmtMensalidade.executeUpdate() == 0)
                throw new UnauthorizedOperationException("Não foi possível Criar um Novo Contrato");


            // ATUALIZA STATUS DO TERRENO
            PreparedStatement stmtTerrenoIndisponivel = connection.prepareStatement(
            """
            UPDATE TERRENOS
                SET
                DISPONIVEL = 'N',
                EDITADO = ?
             WHERE TERRENO_ID = ?
            """);

            stmtTerrenoIndisponivel.setString(1, Instant.now().toString());
            stmtTerrenoIndisponivel.setInt(2, terreno.getId());

            if (stmtTerrenoIndisponivel.executeUpdate() == 0)
                throw new UnauthorizedOperationException("Não foi possível modificar o terreno para indisponivel");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    public void cancelarContratoTerreno(Integer usuarioID, Integer contratoID) {
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = """
                    UPDATE CONTRATOS
                        set
                         ATIVO = 'N',
                         EDITADO = ?
                    WHERE TERRENO_ID = ? AND LOCATARIO_ID = ?
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setString(1, Instant.now().toString());
            stmt.setInt(2, contratoID);
            stmt.setInt(3, usuarioID);

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Incosistência de dados. Registros não Encontrados.");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
}
