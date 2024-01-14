package repository;

import database.BancoDeDados;
import infra.exceptions.DbException;
import models.Terreno;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            String querySQL = "SELECT * FROM TERRENOS WHERE lower(TITULO) LIKE LOWER(TRIM(?)) AND DISPONIVEL = 'S'";

            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setString(1,  "%" + titulo + "%");

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

            connection = BancoDeDados.criaConexao();
            String querySQL ="""
                            SELECT T.TERRENO_ID, T.TITULO, T.DESCRICAO, T.DONO_ID, T.ENDERECO_TERRENO_ID, T.PRECO, T.TAMANHO
                            FROM TERRENOS T JOIN USUARIOS U ON T.DONO_ID = U.USUARIO_ID
                            WHERE LOWER(U.NOME) LIKE LOWER(TRIM(?)) and DISPONIVEL = 'S'
                            """;

            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setString(1, '%' + name.trim() + '%');

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

            connection = BancoDeDados.criaConexao();
            String querySQL = "SELECT * FROM TERRENOS WHERE lower(TAMANHO) like LOWER(TRIM(?))";

            PreparedStatement stmt = connection.prepareStatement(querySQL);
            stmt.setString(1, "%" + tamanho);

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
                resultSet.getString("TAMANHO")
        );
    }
}
