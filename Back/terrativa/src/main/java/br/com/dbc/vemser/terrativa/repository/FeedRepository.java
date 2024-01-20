package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository

public class FeedRepository {
    String pesquisa = "";
    String valor = "";
    String tamanho = "";
    String estado = "";

    Connection connection;
    BancoDeDados bancoConection;

    public FeedRepository(BancoDeDados bancoDeDados) {
        this.bancoConection = bancoDeDados;
    }

    public ArrayList<Feed> buscarTerrenos() {
        try {
            ArrayList<Feed> response = new ArrayList<>();
            connection = bancoConection.criaConexao();

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

            stmt.setString(1, preparaPesquisa(pesquisa));
            stmt.setString(2, preparaPesquisa(pesquisa));
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

    public ArrayList<Terreno> mostrarTodosOsTerrenos(Integer usuarioID) {
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

    public ArrayList<Terreno> mostrarTerrenosPorPreco(double value) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE PRECO <= ? AND DISPONIVEL = 'S'";

            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setDouble(1, value);

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

    public ArrayList<Terreno> mostrarTerrenosPorTitulo(String titulo, Integer usuarioID) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();

            connection = bancoConection.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE lower(TITULO) LIKE LOWER(TRIM(?)) AND DISPONIVEL = 'S' AND DONO_ID = ?";

            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setString(1,  "%" + titulo + "%");
            stmt.setInt(2,  usuarioID);

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

    public String preparaPesquisa(String pesquisa) {

        if (pesquisa == "") {
            return "(+)";
        }

        String[] pesquisaFatiada = pesquisa.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String p : pesquisaFatiada) {
            resultado.append("[");
            resultado.append(p);
            resultado.append("(+)");
            resultado.append("]");
        }
        System.out.println(resultado.toString());
        return resultado.toString();
    }


}
