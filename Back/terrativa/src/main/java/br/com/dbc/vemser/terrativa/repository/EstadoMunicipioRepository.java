package br.com.dbc.vemser.terrativa.repository;
import br.com.dbc.vemser.terrativa.database.BancoDeDados;
import br.com.dbc.vemser.terrativa.entity.EstadosMunicipios;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class EstadoMunicipioRepository {

    private Connection connection;
    private final BancoDeDados bancoConection;


    public EstadosMunicipios buscarCodIBGE(int id){
        try {
            connection = bancoConection.criaConexao();
            String sqlQuery = "SELECT * FROM ESTADO_MUNICIPIOS WHERE MUNICIPIO_COD_IBGE = " + id;
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new EstadosMunicipios(
                        result.getString("NOME_ESTADO"),
                        result.getInt("ESTADO_COD"),
                        result.getString("NOME_MUNICIPIO"),
                        result.getInt("MUNICIPIO_COD_IBGE")
                );
            }
            throw new DataNotFoundException("Município não encontrado");

        } catch (SQLException e) {
            throw new DbException(e.getCause().getMessage());
        } finally {
            BancoDeDados.fechaConexao(connection);
        }
    }
}
