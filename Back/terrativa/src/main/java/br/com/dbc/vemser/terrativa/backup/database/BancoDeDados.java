package br.com.dbc.vemser.terrativa.backup.database;

import br.com.dbc.vemser.terrativa.backup.exceptions.DbException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class BancoDeDados {
    public static Connection criaConexao(){
            try {
                Properties props = carregaPropriedades();
                String url = props.getProperty("dburl");
                 Connection conn = DriverManager.getConnection(url, props);
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
}
