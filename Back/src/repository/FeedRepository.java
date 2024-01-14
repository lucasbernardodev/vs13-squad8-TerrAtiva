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
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') - 100000 FROM DUAL), 0))
                        AND
                        (NVL((SELECT regexp_replace(?, '[^0-9]', '') + 200000 FROM DUAL), 10000000))
                    AND REGEXP_LIKE (em.ESTADO_COD, NVL(?,'[0-9]'))
               """;


            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            FeedService feedService = new FeedService();

            stmt.setString(1, feedService.preparaPesquisa(pesquisa));
            stmt.setString(2, feedService.preparaPesquisa(pesquisa));
            stmt.setString(3, pesquisa);
            stmt.setString(4, pesquisa);
            stmt.setString(5, tamanho);
            stmt.setString(6, tamanho);
            stmt.setString(7, estado);


            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Feed terreno = new Feed();
                terreno.setTerrenoId(resultSet.getInt("TERRENO_ID"));
                terreno.setTitulo(resultSet.getString("TITULO"));
                terreno.setDescricao(resultSet.getString("TITULO"));
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

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public ArrayList<Feed> mostrarTerrenosPorLocatario(String name) {
//        try {
//            ArrayList<Feed> response = new ArrayList<>();
//            name = "%" + name + "%";
//
//            connection = BancoDeDados.criaConexao();
//            String querySQL = """
//                        SELECT T.TERRENO_ID, T.TITULO,
//                               t.DESCRICAO, t.DONO_ID,
//                               t.ENDERECO_TERRENO_ID, t.PRECO,
//                               t.TAMANHO
//                        FROM TERRENOS T
//                              JOIN USUARIOS U ON T.DONO_ID = U.USUARIO_ID
//                        WHERE U.NOME LIKE ? and DISPONIVEL = 'S';
//                              """;
//
//            PreparedStatement stmt = connection.prepareStatement(querySQL);
//            stmt.setString(1, name);
//
//            ResultSet resultSet = stmt.executeQuery();
//
//            while (resultSet.next()) {
//                  response.add(terrenoMapper(resultSet));
//            }
//            return response;
//
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        } finally {
//            BancoDeDados.fechaConexao(connection);
//        }
//    }
//    public ArrayList<Feed> mostrarTerrenosPorTamanho(String tamanho) {
//        try {
//            ArrayList<Feed> response = new ArrayList<>();
//            tamanho = "%" + tamanho + "%";
//
//            connection = BancoDeDados.criaConexao();
//            String querySQL = "SELECT * FROM TERRENOS WHERE TAMANHO like ?";
//
//            PreparedStatement stmt = connection.prepareStatement(querySQL);
//            stmt.setString(1, tamanho);
//
//            ResultSet resultSet = stmt.executeQuery();
//
//            while (resultSet.next()) {
//                  response.add(terrenoMapper(resultSet));
//            }
//            return response;
//
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        } finally {
//            BancoDeDados.fechaConexao(connection);
//        }
//    }

//    private Terreno terrenoMapper(ResultSet resultSet) throws SQLException {
//        return new Terreno(
//                resultSet.getString("TITULO"),
//                resultSet.getString("DESCRICAO"),
//                resultSet.getInt("DONO_ID"),
//                resultSet.getInt("ENDERECO_TERRENO_ID"),
//                resultSet.getDouble("PRECO"),
//                resultSet.getString("TAMANHO")
//        );
//    }

}
