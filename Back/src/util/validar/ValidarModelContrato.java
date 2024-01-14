package util.validar;
import infra.exceptions.InvalidParamException;

import java.time.LocalDate;

public class ValidarModelContrato {
    public static final void CONTRATO(Integer proprietarioID, Integer terrenoID,
                                      LocalDate dataAssinatura,
                                      LocalDate dataInicio, LocalDate dataFinal,
                                      LocalDate dataVencimentoAluguel) {

        if (proprietarioID == null) throw new InvalidParamException("ID do Locatário não pode ser Nulo!");
        if (terrenoID == null) throw new InvalidParamException("ID do Terreno não pode ser Nulo!");
        if (dataAssinatura == null) throw new InvalidParamException("Data de Assinatura não pode ser Nula!");
        if (dataInicio == null) throw new InvalidParamException("Data de Início não pode ser Nula!");
        if (dataFinal == null) throw new InvalidParamException("Data Final não pode ser Nula!");
        if (dataVencimentoAluguel == null) throw new InvalidParamException("Dia de Vencimento não pode ser Nulo!");

    }

}








