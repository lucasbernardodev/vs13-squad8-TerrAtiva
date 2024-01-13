package services;

import models.Contrato;
import repository.ContratoRepository;
import util.validar.ValidarModelContrato;

import java.sql.Date;

public class ContratoService {
    private ContratoRepository contratoRepository = new ContratoRepository();

    public void adicionarContrato(Integer proprietarioID, Integer terrenoID, String ativo,
                                  Date dataAssinatura, Date dataInicio, Date dataFinal,
                                  Date dataVencimentoAluguel)  {
        ValidarModelContrato.CONTRATO( proprietarioID, terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel.toLocalDate());
        contratoRepository.adicionar(new Contrato(proprietarioID, terrenoID, ativo, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel.toLocalDate()));
    }

    public void alterar(Integer proprietarioID, Integer terrenoID,
                        Date dataAssinatura, Date dataInicio, Date dataFinal,
                        Date dataVencimentoAluguel)  {
        ValidarModelContrato.CONTRATO( proprietarioID, terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel.toLocalDate());
    }

    public void deletar(Integer id) {
        contratoRepository.deletar(id);
    }

    public Contrato resgatarPorId(Integer id) {
        return contratoRepository.resgatarDadosPorId(id);
    }
}
