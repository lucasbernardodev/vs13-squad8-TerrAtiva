package br.com.dbc.vemser.terrativa.database;

import br.com.dbc.vemser.terrativa.config.PropertiesReader;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Component
@RequiredArgsConstructor
public class BancoDeDados {

    private final PropertiesReader props;
    public final Connection criaConexao(){
        try {
            Connection conn = DriverManager.getConnection(props.getDburl(), props.getDbuser(), props.getDbpassword());
            if(conn == null) throw new DbException("Conexao nao sucedida");
            conn.createStatement().execute("alter session set current_schema = " + props.getSCHEMA());
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
