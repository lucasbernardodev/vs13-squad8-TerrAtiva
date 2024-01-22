package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import br.com.dbc.vemser.terrativa.util.validar.ValidarModel;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;

    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }
    public Contrato resgatarContratoPorId(Integer id) {
        return contratoRepository.resgatarDadosPorId(id);
    }
    public void alterar(Integer proprietarioID, Integer terrenoID,
                        Date dataAssinatura, Date dataInicio, Date dataFinal,
                        Integer dataVencimentoAluguel) {

                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel);

        contratoRepository.alterar(new Contrato(proprietarioID, terrenoID, dataAssinatura.toLocalDate(),
                dataInicio.toLocalDate(), dataFinal.toLocalDate(), dataVencimentoAluguel));
    }

    public void deletar(Integer id) {
        contratoRepository.deletar(id);
    }


}
