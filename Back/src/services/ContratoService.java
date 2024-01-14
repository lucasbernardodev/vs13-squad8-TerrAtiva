package services;

import models.Aluguel;
import models.Contrato;
import repository.ContratoRepository;
import util.validar.ValidarModel;

import java.sql.Date;


public class ContratoService {
    private ContratoRepository contratoRepository = new ContratoRepository();

    public void adicionarContrato(Integer proprietarioID, Integer terrenoID,
                                  Date dataAssinatura, Date dataInicio, Date dataFinal,
                                  Date dataVencimentoAluguel)  {
        ValidarModel.CONTRATOS( proprietarioID, terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel.toLocalDate());
        contratoRepository.adicionar(new Contrato(proprietarioID, terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel.toLocalDate()));
    }

    public void alterar(Integer proprietarioID, Integer terrenoID,
                        Date dataAssinatura, Date dataInicio, Date dataFinal,
                        Date dataVencimentoAluguel) {
        ValidarModel.CONTRATOS(proprietarioID, terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel.toLocalDate());
        contratoRepository.alterar(proprietarioID, new Contrato(terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel.toLocalDate()));
    }
    public void deletar(Integer id) {
        contratoRepository.deletar(id);
    }

    public Contrato resgatarContratoPorId(Integer id) {
        return contratoRepository.resgatarDadosPorId(id);
    }
}