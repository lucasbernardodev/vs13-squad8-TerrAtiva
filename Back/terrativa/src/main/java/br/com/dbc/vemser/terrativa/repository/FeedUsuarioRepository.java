package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.entity.FeedUsuario;
import br.com.dbc.vemser.terrativa.entity.FeedUsuariosAlugados;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Repository
@RequiredArgsConstructor
public class FeedUsuarioRepository {

    private static Connection connection;
    private final BancoDeDados bancoConection;


    public ArrayList<FeedUsuario> mostrarTerrenosDisponiveis(Integer usuarioID) {
        try {
            ArrayList<FeedUsuario> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS LEFT JOIN ENDERECO_TERRENOS ON (ENDERECO_TERRENOS.ENDERECO_TERRENO_ID = TERRENOS.ENDERECO_TERRENO_ID) WHERE DISPONIVEL = 'S' AND DONO_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setInt(1, usuarioID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                response.add(feedUsuarioMapper(resultSet));
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    public ArrayList<FeedUsuariosAlugados> mostrarTerrenosAlugados(Integer usuarioID) {
        try {
            ArrayList<FeedUsuariosAlugados> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = """
                    SELECT * FROM CONTRATOS
                    LEFT JOIN TERRENOS ON (CONTRATOS.TERRENO_ID = TERRENOS.TERRENO_ID)
                    LEFT JOIN ENDERECO_TERRENOS ON (TERRENOS.ENDERECO_TERRENO_ID = ENDERECO_TERRENOS.ENDERECO_TERRENO_ID)
                    WHERE CONTRATOS.LOCATARIO_ID = ?""";
            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setInt(1, usuarioID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                response.add(feedUsuarioContratoMapper(resultSet));
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    public ArrayList<FeedUsuario> mostrarTerrenosDoUsuario(Integer usuarioID) {
        try {
            ArrayList<FeedUsuario> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS LEFT JOIN ENDERECO_TERRENOS ON (ENDERECO_TERRENOS.ENDERECO_TERRENO_ID = TERRENOS.ENDERECO_TERRENO_ID) WHERE DONO_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setInt(1, usuarioID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                response.add(feedUsuarioMapper(resultSet));
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    public ArrayList<FeedUsuariosAlugados> mostrarTerrenosArrendados(Integer usuarioID) {
        try {
            ArrayList<FeedUsuariosAlugados> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = """
                    SELECT * FROM CONTRATOS
                    LEFT JOIN TERRENOS ON (CONTRATOS.TERRENO_ID = TERRENOS.TERRENO_ID)
                    LEFT JOIN ENDERECO_TERRENOS ON (TERRENOS.ENDERECO_TERRENO_ID = ENDERECO_TERRENOS.ENDERECO_TERRENO_ID)
                    WHERE TERRENOS.DISPONIVEL = 'N' AND TERRENOS.DONO_ID = ?""";
            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setInt(1, usuarioID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                response.add(feedUsuarioContratoMapper(resultSet));
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }


    private FeedUsuario feedUsuarioMapper(ResultSet resultSet) throws SQLException {
        return new FeedUsuario(
                resultSet.getInt("TERRENO_ID"),
                resultSet.getString("TITULO"),
                resultSet.getString("DESCRICAO"),
                resultSet.getInt("DONO_ID"),
                resultSet.getInt("ENDERECO_TERRENO_ID"),
                resultSet.getDouble("PRECO"),
                resultSet.getString("TAMANHO"),
                resultSet.getString("DISPONIVEL"),
                resultSet.getString("LOGRADOURO"),
                resultSet.getInt("NUMERO"),
                resultSet.getString("COMPLEMENTO"),
                resultSet.getString("BAIRRO"),
                resultSet.getInt("MUNICIPIO_COD_IBGE"),
                resultSet.getInt("CEP"),
                resultSet.getString("LOCALIZACAO")

        );
    }

    private FeedUsuariosAlugados feedUsuarioContratoMapper(ResultSet resultSet) throws SQLException {
        return new FeedUsuariosAlugados(
                resultSet.getInt("CONTRATO_ID"),
                resultSet.getInt("LOCATARIO_ID"),
                resultSet.getInt("TERRENO_ID"),
                resultSet.getString("ATIVO"),
                resultSet.getDate("DATA_ASSINATURA"),
                resultSet.getDate("DATA_INICIO"),
                resultSet.getDate("DATA_FINAL"),
                resultSet.getInt("DIA_VENCIMENTO_ALUGUEL"),
                resultSet.getString("TITULO"),
                resultSet.getString("DESCRICAO"),
                resultSet.getInt("DONO_ID"),
                resultSet.getInt("ENDERECO_TERRENO_ID"),
                resultSet.getDouble("PRECO"),
                resultSet.getString("TAMANHO"),
                resultSet.getString("DISPONIVEL"),
                resultSet.getString("LOGRADOURO"),
                resultSet.getInt("NUMERO"),
                resultSet.getString("COMPLEMENTO"),
                resultSet.getString("BAIRRO"),
                resultSet.getInt("MUNICIPIO_COD_IBGE"),
                resultSet.getInt("CEP"),
                resultSet.getString("LOCALIZACAO")
        );
    }
}
