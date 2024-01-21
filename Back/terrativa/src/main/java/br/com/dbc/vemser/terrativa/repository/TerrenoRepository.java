package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.database.GeradorID;
import br.com.dbc.vemser.terrativa.entity.*;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository

public class TerrenoRepository implements DaoRepository<Terreno> {
    Connection connection;
    BancoDeDados bancoConection;

    public TerrenoRepository(BancoDeDados bancoDeDados) {
        this.bancoConection = bancoDeDados;
    }
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
    public void alterar(Terreno terreno) {
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
            String sqlQuery = "UPDATE TERRENOS set DISPONIVEL= ? WHERE TERRENO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, "N");

            if (stmt.executeUpdate() == 0)
                throw new DataNotFoundException("Dados do Usuário Não Encontrado. ID: " + id);

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

    public void arrendarTerreno(Contrato contratoRequest, Mensalidade mensalidade, Aluguel aluguelRequest) {
        try {
            // PASSO 1: GERAR CONTRATO
            connection = bancoConection.criaConexao();
            int newContratoID = GeradorID.getProximoContrato(connection);
            int newMensalidadeID = GeradorID.getProximoMensalidade(connection);
            Integer newAluguelID = GeradorID.getProximoAluguel(connection);

            String sqlQueryContrato = """
                    INSERT INTO CONTRATOS
                        (CONTRATO_ID, LOCATARIO_ID, TERRENO_ID, ATIVO, DATA_ASSINATURA,
                        DATA_INICIO, DATA_FINAL, DIA_VENCIMENTO_ALUGUEL, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmtContrato = connection.prepareStatement(sqlQueryContrato);
            stmtContrato.setInt(1, newContratoID);
            stmtContrato.setInt(2, contratoRequest.getProprietarioID());
            stmtContrato.setInt(3, contratoRequest.getTerrenoID());
            stmtContrato.setString(4, contratoRequest.getAtivo());
            stmtContrato.setDate(5, Date.valueOf(contratoRequest.getDataAssinatura()));
            stmtContrato.setDate(6, Date.valueOf(contratoRequest.getDataInicio()));
            stmtContrato.setDate(7, Date.valueOf(contratoRequest.getDataFinal()));
            stmtContrato.setInt(8, contratoRequest.getDataVencimentoAluguel());
            stmtContrato.setString(9, Instant.now().toString());
            stmtContrato.setString(10, Instant.now().toString());

            if (stmtContrato.executeUpdate() == 0)
                throw new UnauthorizedOperationException("Não foi possível Criar um Novo Contrato");

            // PASSO 2: CRIAR ALUGUEL

            String sqlQueryMensalidade = """
                    INSERT INTO MENSALIDADES
                        (MENSALIDADE_ID, CONTRATO_ID, VALOR_MENSAL, ANO_EXERCICIO, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmtMensalidade = connection.prepareStatement(sqlQueryMensalidade);
            stmtMensalidade.setInt(1, newMensalidadeID);
            stmtMensalidade.setInt(2, newContratoID);
            stmtMensalidade.setDouble(3, mensalidade.getValorMensal());
            stmtMensalidade.setInt(4, mensalidade.getAnoExercicio());
            stmtMensalidade.setString(5, Instant.now().toString());
            stmtMensalidade.setString(6, Instant.now().toString());

            if (stmtMensalidade.executeUpdate() == 0)
                throw new UnauthorizedOperationException("Não foi possível Criar um Novo Contrato");

            // PASSO 3: ALUGUEL

            String sqlQueryAluguel = """
                    INSERT INTO ALUGUEL_PAGAMENTOS
                        (PAGAMENTO_ID, MENSALIDADE_ID, MES_REFERENCIA, EMISSAO, VENCIMENTO,
                        TAXAS, CODIGO_BARRAS_BOLETO, DATA_PAGAMENTO, PAGO, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmt = connection.prepareStatement(sqlQueryAluguel);

            stmt.setInt(1, newAluguelID);
            stmt.setInt(2, newMensalidadeID);
            stmt.setInt(3, aluguelRequest.getMesReferencia());
            stmt.setString(4, Date.valueOf(aluguelRequest.getDataEmissao()).toString());
            stmt.setString(5, Date.valueOf(aluguelRequest.getDataVencimento()).toString());
            stmt.setDouble(6, aluguelRequest.getTaxas());
            stmt.setString(7, aluguelRequest.getCodigoBarras());
            stmt.setDate(8, Date.valueOf(aluguelRequest.getDataPagamento()));
            stmt.setString(9, aluguelRequest.getPago());
            stmt.setString(10, Instant.now().toString());
            stmt.setString(11, Instant.now().toString());
            if (stmt.executeUpdate() == 0)
                throw new UnauthorizedOperationException("Não foi possível cadastrar novo Aluguel");


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
            stmtTerrenoIndisponivel.setInt(2, contratoRequest.getTerrenoID());

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

            PreparedStatement stmtTerrenoDisponivel = connection.prepareStatement("""
                                                                                        UPDATE TERRENOS
                                                                                            SET
                                                                                            DISPONIVEL = 'S',
                                                                                            EDITADO = ?
                                                                                         WHERE TERRENO_ID = ?
                                                                                        """);
            stmtTerrenoDisponivel.setString(1, Instant.now().toString());
            stmtTerrenoDisponivel.setInt(2, contratoID);
            stmtTerrenoDisponivel.executeUpdate();

            if (stmt.executeUpdate() == 0) throw new DbException("Não foi possível alterar o status do Terreno.");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
}
