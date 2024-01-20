package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.repository.AluguelRepository;
import br.com.dbc.vemser.terrativa.entity.Aluguel;

import java.sql.SQLException;
import java.time.LocalDate;

public class AluguelService {
    private AluguelRepository aluguelRepository = new AluguelRepository();


    public void alterar(Integer pagamentoID, Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                        LocalDate dataVencimento, double taxas, String codigoBarras,
                        LocalDate dataPagamento) throws SQLException {
        aluguelRepository.alterar(pagamentoID, new Aluguel(mensalidadeID, mesReferencia, dataEmissao, dataVencimento,
                taxas, codigoBarras, dataPagamento));
    }


    public void deletar(Integer id) throws SQLException {
        aluguelRepository.deletar(id);
    }

    public Aluguel resgatarPorId(Integer id) throws SQLException {
        return aluguelRepository.resgatarDadosPorId(id);
    }
}







