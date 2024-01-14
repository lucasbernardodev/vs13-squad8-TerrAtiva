import database.BancoDeDados;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = BancoDeDados.criaConexao();
        if(conn == null) System.out.println("Erro");
        System.out.println("tudo certo");
    }
}