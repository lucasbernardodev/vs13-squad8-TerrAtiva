package br.com.dbc.vemser.terrativa.database;

import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.util.PropertiesReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class BancoDeDados {

    private final PropertiesReader props;
    public BancoDeDados(PropertiesReader props){
        this.props = props;
    }
    public final Connection criaConexao(){
        try {
            Connection conn = DriverManager.getConnection(props.getDburl(), props.getDbuser(), props.getDbpassword());
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
}
