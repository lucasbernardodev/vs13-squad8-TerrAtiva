package util.validar;

import infra.exceptions.InvalidParamException;

import java.time.LocalDate;

public class ValidarModelAluguel {
    public static final void ALUGUEL(Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                                     LocalDate dataVencimento, double taxas, String codigoBarras,
                                     LocalDate dataPagamento) {
        if (mensalidadeID == null) throw new InvalidParamException("ID da Mensalidade não pode ser Nulo!");
        if (mesReferencia == null) throw new InvalidParamException("Mês Referência não pode ser Nulo!");
        if (dataEmissao == null) throw new InvalidParamException("Data de Emissão não pode ser Nula!");
        if (dataVencimento == null) throw new InvalidParamException("Data de Vencimento não pode ser Nula!");
        if (taxas < 0) throw new InvalidParamException("Taxas não podem ser negativas!");
        if (codigoBarras == null) throw new InvalidParamException("Código de Barras não pode ser Nulo!");
    }
}

