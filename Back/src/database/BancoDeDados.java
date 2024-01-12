package database;

import infra.exceptions.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class BancoDeDados {

    private static Connection conn = null;

    public static Connection criaConeccao(){
            try {

                Properties props = carregaPropriedades();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
                if(conn == null) throw new DbException("Conexao nao sucedida");
                conn.createStatement().execute("alter session set current_schema = VS_13_EQUIPE_8");
                return conn;
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
    }
    public static void fechaConexao(Connection conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
    }
    private static Properties carregaPropriedades() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
    public static void fechaStatment(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void fechaResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
