package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedQuantidadeAnunciosDTO;
import br.com.dbc.vemser.terrativa.entity.Estados;
import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class FeedRepository {

    private static Connection connection;
    private final BancoDeDados bancoConection;

    public List<Feed> buscarTerrenos(String preco, Estados estado, String tamanho) {
        try {
            List<Feed> response = new ArrayList<>();
            connection = bancoConection.criaConexao();

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
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') + 50 FROM DUAL), 10000000))
                    AND TAMANHO
                        BETWEEN\s
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') - 10000 FROM DUAL), 0))
                        AND
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') + 10000 FROM DUAL), 10000000))
               """;

            PreparedStatement stmt;

            if (estado != null) {
                sqlQuery += " AND em.ESTADO_COD = ?";
                stmt = connection.prepareStatement(sqlQuery);
                stmt.setInt(5, estado.getCode());
            } else {
                stmt = connection.prepareStatement(sqlQuery);
            }

            stmt.setString(1, preco);
            stmt.setString(2, preco);
            stmt.setString(3, tamanho);
            stmt.setString(4, tamanho);


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

    public List<ResponseFeedQuantidadeAnunciosDTO> quantidadeAnuncios() {
        try {
            List<ResponseFeedQuantidadeAnunciosDTO> response = new ArrayList<>();
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
                ResponseFeedQuantidadeAnunciosDTO terreno = new ResponseFeedQuantidadeAnunciosDTO();
                terreno.setEstado(resultSet.getString("NOME_ESTADO"));
                terreno.setTotalAnuncios(resultSet.getString("QUANTIDADE"));
                response.add(terreno);
            }
            return response;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }

    }




}
