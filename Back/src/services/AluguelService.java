package services;

import models.Aluguel;
import repository.AluguelRepository;
import util.validar.ValidarModel;

import java.time.LocalDate;

public class AluguelService {
    private AluguelRepository aluguelRepository = new AluguelRepository();


    public void alterar(Integer pagamentoID, Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                        LocalDate dataVencimento, double taxas, String codigoBarras,
                        LocalDate dataPagamento) {
        ValidarModel.ALUGUEL_PAGAMENTOS(mesReferencia, dataEmissao, dataVencimento,
                taxas, codigoBarras, dataPagamento);
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







