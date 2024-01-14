package repository;

import database.BancoDeDados;
import infra.exceptions.DbException;
import models.Feed;
import services.FeedService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class FeedRepository {

    String pesquisa = "";
    String valor = "";
    String tamanho = "";
    String estado = "";

    Connection connection;

    public ArrayList<Feed> buscarTerrenos() {
        try {
            ArrayList<Feed> response = new ArrayList<>();
            connection = BancoDeDados.criaConexao();

            String sqlQuery = """
                SELECT * FROM TERRENOS t\s
                JOIN ENDERECO_TERRENOS et\s
                ON t.ENDERECO_TERRENO_ID = et.ENDERECO_TERRENO_ID\s
                JOIN ESTADO_MUNICIPIOS em\s
                ON et.MUNICIPIO_COD_IBGE = em.MUNICIPIO_COD_IBGE
                WHERE DISPONIVEL = 'S'
                    AND (REGEXP_LIKE (t.TITULO, ?, 'i') OR REGEXP_LIKE (t.DESCRICAO, ?, 'i'))
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

            FeedService feedService = new FeedService();

            stmt.setString(1, feedService.preparaPesquisa(pesquisa));
            stmt.setString(2, feedService.preparaPesquisa(pesquisa));
            stmt.setString(3, valor);
            stmt.setString(4, valor);
            stmt.setString(5, tamanho);
            stmt.setString(6, tamanho);
            stmt.setString(7, estado);


            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Feed terreno = new Feed();
                terreno.setTerrenoId(resultSet.getInt("TERRENO_ID"));
                terreno.setTitulo(resultSet.getString("TITULO"));
                terreno.setDescricao(resultSet.getString("DESCRICAO"));
                terreno.setPreco((double) resultSet.getInt("PRECO"));
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
            connection = BancoDeDados.criaConexao();

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
    public void filtrarPorCaracteristicas(String caracteristicas) {
        this.pesquisa = caracteristicas;
    }

    public void filtrarPorValor(String valor) {
        this.valor = valor;
    }
    public void filtrarPorTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public void filtrarPorEstado(String estado) {
        this.estado = estado;
    }

    public void limparFiltros() {
        this.pesquisa = "";
        this.valor = "";
        this.estado  = "";
        this.tamanho  = "";
    }


}
