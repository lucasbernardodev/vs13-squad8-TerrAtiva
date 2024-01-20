package br.com.dbc.vemser.terrativa.util.validar;
import br.com.dbc.vemser.terrativa.exceptions.InvalidParamException;

import java.time.LocalDate;

public class ValidarModelContrato {
    public static final void CONTRATO(Integer locatarioID, Integer terrenoID,
                                      String ativo, LocalDate dataAssinatura,
                                      LocalDate dataInicio, LocalDate dataFinal,
                                      LocalDate dataVencimentoAluguel) {

        if (locatarioID == null) throw new InvalidParamException("ID do Locatário não pode ser Nulo!");
        if (terrenoID == null) throw new InvalidParamException("ID do Terreno não pode ser Nulo!");
        if (ativo.isBlank()) throw new InvalidParamException("Ativo não pode estar vazio!");
        if (dataAssinatura == null) throw new InvalidParamException("Data de Assinatura não pode ser Nula!");
        if (dataInicio == null) throw new InvalidParamException("Data de Início não pode ser Nula!");
        if (dataFinal == null) throw new InvalidParamException("Data Final não pode ser Nula!");
        if (dataVencimentoAluguel == null) throw new InvalidParamException("Dia de Vencimento não pode ser Nulo!");

    }

}








