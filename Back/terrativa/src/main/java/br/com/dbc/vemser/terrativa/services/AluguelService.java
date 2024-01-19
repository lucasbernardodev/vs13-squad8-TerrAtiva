package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.repository.AluguelRepository;
import br.com.dbc.vemser.terrativa.models.Aluguel;

import java.time.LocalDate;

public class AluguelService {
    private AluguelRepository aluguelRepository = new AluguelRepository();


    public void alterar(Integer pagamentoID, Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                        LocalDate dataVencimento, double taxas, String codigoBarras,
                        LocalDate dataPagamento) {
        aluguelRepository.alterar(pagamentoID, new Aluguel(mensalidadeID, mesReferencia, dataEmissao, dataVencimento,
                taxas, codigoBarras, dataPagamento));
    }


    public void deletar(Integer id) {
        aluguelRepository.deletar(id);
    }

    public Aluguel resgatarPorId(Integer id) {
        return aluguelRepository.resgatarDadosPorId(id);
    }
}







