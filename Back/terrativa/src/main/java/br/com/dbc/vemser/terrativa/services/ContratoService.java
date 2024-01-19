package br.com.dbc.vemser.terrativa.services;

import models.Contrato;
import repository.ContratoRepository;
import util.validar.ValidarModel;

import java.sql.Date;


public class ContratoService {
    private ContratoRepository contratoRepository = new ContratoRepository();

    public void alterar(Integer proprietarioID, Integer terrenoID,
                        Date dataAssinatura, Date dataInicio, Date dataFinal,
                        Integer dataVencimentoAluguel) {

        ValidarModel.CONTRATOS(proprietarioID, terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel);

        contratoRepository.alterar(proprietarioID, new Contrato(proprietarioID, terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel));
    }

    public void deletar(Integer id) {
        contratoRepository.deletar(id);
    }

    public Contrato resgatarContratoPorId(Integer id) {
        return contratoRepository.resgatarDadosPorId(id);
    }
}
