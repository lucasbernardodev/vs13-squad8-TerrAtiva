package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.util.validar.ValidarModel;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
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
