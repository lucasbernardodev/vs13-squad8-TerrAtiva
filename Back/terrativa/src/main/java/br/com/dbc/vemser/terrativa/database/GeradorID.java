package br.com.dbc.vemser.terrativa.database;

import br.com.dbc.vemser.terrativa.exceptions.EntityIdNullException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeradorID {
    public static Integer getProximoUsuarioId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_USUARIO.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        throw new EntityIdNullException("ID do próximo Usuário é nulo");
    }
    public static Integer getProximoTerrenoId(Connection conn) throws SQLException {
        String sql = "SELECT SEQ_TERRENO.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }
    public static Integer getProximoEnderecoId(Connection conn) throws SQLException {
        String sql = "SELECT SEQ_ENDERECO.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        throw new EntityIdNullException("ID do próximo Endereço do Usuário é nulo");
    }
    public static Integer getProximoEnderecoTerrenos(Connection conn ) throws SQLException {
        String sql = "SELECT SEQ_ENDERECO_TERRENO.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        throw new EntityIdNullException("ID do próximo Endereço do Terreno é nulo");
    }
    public static Integer getProximoContrato(Connection conn) throws SQLException {
        String sql = "SELECT SEQ_CONTRATO.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        throw new EntityIdNullException("ID do próximo Contrato é nulo");
    }
    public static Integer getProximoMensalidade(Connection conn) throws SQLException {
        String sql = "SELECT SEQ_MENSALIDADE.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        throw new EntityIdNullException("ID da próxima Mensalidade é nulo");
    }

    public static Integer getProximoAluguel(Connection conn) throws SQLException {
        String sql = "SELECT SEQ_ALUGUEL.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        throw new EntityIdNullException("ID do próximo Aluguel é nulo");
    }



}
