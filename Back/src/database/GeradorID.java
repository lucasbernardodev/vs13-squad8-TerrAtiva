package database;

import javax.swing.plaf.PanelUI;
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

        return null;
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
        return null;
    }
    public static Integer getProximoEnderecoTerrenos(Connection conn ) throws SQLException {
        String sql = "SELECT SEQ_ENDERECO_TERRENO.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }
    public static Integer getProximoContrato(Connection conn) throws SQLException {
        String sql = "SELECT SEQ_CONTRATO.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }
    public static Integer getProximoMensalidade(Connection conn) throws SQLException {
        String sql = "SELECT SEQ_MENSALIDADE.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }

    public static Integer getProximoAluguel(Connection conn) throws SQLException {
        String sql = "SELECT SEQ_MENSALIDADE.nextval mysequence from DUAL";

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }



}
