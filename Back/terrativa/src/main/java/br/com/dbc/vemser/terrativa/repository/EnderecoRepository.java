package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.database.GeradorID;
import br.com.dbc.vemser.terrativa.entity.Endereco;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository

public class EnderecoRepository implements DaoRepository<Endereco> {
    Connection connection;
    BancoDeDados bancoConection;

    public EnderecoRepository(BancoDeDados bancoDeDados) {
        this.bancoConection = bancoDeDados;
    }


    @Override
    public Endereco resgatarDadosPorId(int id) {
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = "SELECT * FROM ENDERECOS WHERE ENDERECO_ID = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Endereco(
                        result.getInt("ENDERECO_ID"),
                        result.getInt("USUARIO_ID"),
                        result.getString("LOGRADOURO"),
                        result.getInt("NUMERO"),
                        result.getString("COMPLEMENTO"),
                        result.getString("BAIRRO"),
                        result.getInt("MUNICIPIO_COD_IBGE"),
                        result.getInt("CEP")
                );
            }
            throw new DataNotFoundException("Não foi possível resgatar dados");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
    @Override
    public Endereco adicionar(Endereco enderecoRequest) {
        try {
            connection = bancoConection.criaConexao();

            Integer proximoId = GeradorID.getProximoEnderecoId(connection);
            enderecoRequest.setId(proximoId);

            Integer usuarioId = enderecoRequest.getUsuarioID();
            if (usuarioId == null) {
                throw new IllegalArgumentException("UsuarioID não pode ser nulo.");
            }
            String sqlQuery = """
                    INSERT INTO ENDERECOS
                        (ENDERECO_ID, USUARIO_ID, LOGRADOURO, NUMERO, COMPLEMENTO,
                           BAIRRO, MUNICIPIO_COD_IBGE, CEP, CRIADO, EDITADO)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, enderecoRequest.getId());
            stmt.setInt(2, enderecoRequest.getUsuarioID());
            stmt.setString(3, enderecoRequest.getLogradouro());
            stmt.setInt(4, enderecoRequest.getNumero());
            stmt.setString(5, enderecoRequest.getComplemento());
            stmt.setString(6, enderecoRequest.getBairro());
            stmt.setInt(7, enderecoRequest.getCodigoMunicipioIBGE());
            stmt.setInt(8, enderecoRequest.getCep());
            stmt.setString(9, Instant.now().toString());
            stmt.setString(10, Instant.now().toString());

            if (stmt.executeUpdate() == 0) throw new UnauthorizedOperationException("Não foi possível cadastrar novo Endereço");
            BancoDeDados.fechaConexao(connection);
            return enderecoRequest;

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

    @Override
    public Endereco alterar(Endereco enderecoRequest) {
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = """
                    UPDATE ENDERECOS
                        set
                        USUARIO_ID = ?,
                        LOGRADOURO = ?,
                        NUMERO = ?,
                        COMPLEMENTO = ?,
                        BAIRRO = ?,
                        MUNICIPIO_COD_IBGE = ?,
                        CEP = ?,
                        EDITADO = ?
                                        
                    WHERE ENDERECO_ID = ?
                    """;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, enderecoRequest.getUsuarioID());
            stmt.setString(2, enderecoRequest.getLogradouro());
            stmt.setInt(3, enderecoRequest.getNumero());
            stmt.setString(4, enderecoRequest.getComplemento());
            stmt.setString(5, enderecoRequest.getBairro());
            stmt.setInt(6, enderecoRequest.getCodigoMunicipioIBGE());
            stmt.setInt(7, enderecoRequest.getCep());
            stmt.setString(8, Instant.now().toString());
            stmt.setInt(9, enderecoRequest.getId());

            if (stmt.executeUpdate() == 0) throw new DataNotFoundException("Dados do Usuário Não Encontrado. ID: ");
            BancoDeDados.fechaConexao(connection);
            return enderecoRequest;

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
            String sqlQuery = "DELETE FROM ENDERECOS WHERE ENDERECO_ID = " + id;
            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate(sqlQuery);
            if (rowsAffected == 0)
                throw new UnauthorizedOperationException("Operação de Deletar Endereço Não Autorizada");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }

}
