package database;

import javax.swing.plaf.PanelUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeradorID {
    public static Integer getProximoUsuarioId(Connection connection) throws SQLException {
        String sql = "SELECT seq_pessoa2.nextval mysequence from DUAL";

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
}
