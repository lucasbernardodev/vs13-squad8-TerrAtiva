package repository;

import database.BancoDeDados;
import infra.exceptions.DbException;
import models.Terreno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class FeedRepository {

    Connection connection;

    public ArrayList<Terreno> mostrarTerrenosDisponiveis() {
        try {
            ArrayList<Terreno> response = new ArrayList<>();

            connection = BancoDeDados.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE DISPONIVEL = 'S'";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(querySQL);

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

            connection = BancoDeDados.criaConexao();
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

    public ArrayList<Terreno> mostrarTerrenosPorTitulo(String titulo) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();

            connection = BancoDeDados.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE TITULO LIKE ? AND DISPONIVEL = 'S'";
            titulo = "%" + titulo + "%";

            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setString(1, titulo);

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

    public ArrayList<Terreno> mostrarTerrenosPorLocatario(String name) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();
            name = "%" + name + "%";

            connection = BancoDeDados.criaConexao();
            String querySQL = """
                        SELECT T.TERRENO_ID, T.TITULO,
                               t.DESCRICAO, t.DONO_ID,
                               t.ENDERECO_TERRENO_ID, t.PRECO,
                               t.TAMANHO
                        FROM TERRENOS T
                              JOIN USUARIOS U ON T.DONO_ID = U.USUARIO_ID
                        WHERE U.NOME LIKE ? and DISPONIVEL = 'S';
                              """;

            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setString(1, name);

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
    public ArrayList<Terreno> mostrarTerrenosPorTamanho(String tamanho) {
        try {
            ArrayList<Terreno> response = new ArrayList<>();
            tamanho = "%" + tamanho + "%";

            connection = BancoDeDados.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE TAMANHO like ?";

            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setString(1, tamanho);

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
                resultSet.getString("TITULO"),
                resultSet.getString("DESCRICAO"),
                resultSet.getInt("DONO_ID"),
                resultSet.getInt("ENDERECO_TERRENO_ID"),
                resultSet.getDouble("PRECO"),
                resultSet.getString("TAMANHO")
        );
    }

}
