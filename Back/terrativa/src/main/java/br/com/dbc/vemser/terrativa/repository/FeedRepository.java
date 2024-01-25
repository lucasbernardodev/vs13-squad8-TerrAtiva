package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
@Slf4j
@RequiredArgsConstructor
public class FeedRepository {

    private static Connection connection;
    private final BancoDeDados bancoConection;


    public ArrayList<Feed> buscarTerrenos(String preco, String estado, String tamanho) {
        try {
            ArrayList<Feed> response = new ArrayList<>();
            connection = bancoConection.criaConexao();
            //localhost:8080/terrenos?pesquisa=terreno&valor=100000&tamanho=100&estado=SP
            String sqlQuery = """
                SELECT * FROM TERRENOS t\s
                JOIN ENDERECO_TERRENOS et\s
                ON t.ENDERECO_TERRENO_ID = et.ENDERECO_TERRENO_ID\s
                JOIN ESTADO_MUNICIPIOS em\s
                ON et.MUNICIPIO_COD_IBGE = em.MUNICIPIO_COD_IBGE
                WHERE DISPONIVEL = 'S'
                    AND  PRECO\s
                        BETWEEN\s
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') - 50 FROM DUAL), 0))
                        AND
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') +50 FROM DUAL), 10000000))
                    AND TAMANHO
                        BETWEEN\s
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') * 10000 - 20000 FROM DUAL), 0))
                        AND
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') * 10000 + 20000 FROM DUAL), 10000000))
                    AND REGEXP_LIKE (em.ESTADO_COD, NVL(?,'[0-9]'))
               """;

            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setString(1, preco);
            stmt.setString(2, preco);
            stmt.setString(3, tamanho);
            stmt.setString(4, tamanho);
            stmt.setString(5, estado);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Feed terreno = new Feed();
                terreno.setTerrenoId(resultSet.getInt("TERRENO_ID"));
                terreno.setTitulo(resultSet.getString("TITULO"));
                terreno.setDescricao(resultSet.getString("DESCRICAO"));
                terreno.setPreco(resultSet.getInt("PRECO"));
                terreno.setTamanho(resultSet.getInt("TAMANHO"));
                terreno.setEstado(resultSet.getString("NOME_ESTADO"));
                terreno.setCidade(resultSet.getString("NOME_MUNICIPIO"));
                response.add(terreno);
            }

            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    public ArrayList<Feed> buscarEstados() {
        try {
            ArrayList<Feed> response = new ArrayList<>();
            connection = bancoConection.criaConexao();

            String sqlQuery = """
               SELECT count(*) as QUANTIDADE, NOME_ESTADO, em.ESTADO_COD, t.DISPONIVEL  FROM TERRENOS t
               JOIN ENDERECO_TERRENOS et\s
               ON t.ENDERECO_TERRENO_ID = et.ENDERECO_TERRENO_ID\s
               JOIN ESTADO_MUNICIPIOS em\s
               ON et.MUNICIPIO_COD_IBGE = em.MUNICIPIO_COD_IBGE
               GROUP BY NOME_ESTADO, em.ESTADO_COD, t.DISPONIVEL\s
               HAVING t.DISPONIVEL = 'S'
               ORDER BY NOME_ESTADO
               """;
            
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Feed terreno = new Feed();
                terreno.setCod_estado(resultSet.getString("ESTADO_COD"));
                terreno.setEstado(resultSet.getString("NOME_ESTADO"));
                terreno.setQuantidade(resultSet.getString("QUANTIDADE"));
                response.add(terreno);
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }

    }

    public ArrayList<Terreno> mostrarTerrenosDisponiveis(Integer usuarioID) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE DISPONIVEL = 'S' AND DONO_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setInt(1, usuarioID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                response.add(terrenoMapper(resultSet));
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    public ArrayList<Terreno> mostrarTerrenosAlugados(Integer usuarioID) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS, CONTRATOS WHERE LOCATARIO_ID = ? AND CONTRATOS.TERRENO_ID = TERRENOS.TERRENO_ID";
            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setInt(1, usuarioID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                response.add(terrenoMapper(resultSet));
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    public ArrayList<Terreno> mostrarTerrenosDoUsuario(Integer usuarioID) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE DONO_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setInt(1, usuarioID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                response.add(terrenoMapper(resultSet));
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    public ArrayList<Terreno> mostrarTerrenosArrendados(Integer usuarioID) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE DISPONIVEL = 'N' AND DONO_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setInt(1, usuarioID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                response.add(terrenoMapper(resultSet));
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
    private Terreno terrenoMapper(ResultSet resultSet) throws SQLException {
        return new Terreno(
                resultSet.getInt("TERRENO_ID"),
                resultSet.getString("TITULO"),
                resultSet.getString("DESCRICAO"),
                resultSet.getInt("DONO_ID"),
                resultSet.getInt("ENDERECO_TERRENO_ID"),
                resultSet.getDouble("PRECO"),
                resultSet.getString("TAMANHO"),
                resultSet.getString("DISPONIVEL")
        );
    }


}
