package br.com.dbc.vemser.terrativa.config.validar;

import br.com.dbc.vemser.terrativa.exceptions.InvalidParamException;

import java.time.LocalDate;

public class ValidarModel {
    
    public static final void ALUGUEL_PAGAMENTOS(Integer mesReferencia,
                                   LocalDate dataEmissao, LocalDate dataVencimento,
                                   double taxas, String codigoBarras, LocalDate dataPagamento) {
        if (mesReferencia == null) throw new InvalidParamException("Mês Referência não pode ser Nulo!");
        if (dataEmissao == null) throw new InvalidParamException("Data de Emissão não pode ser Nula!");
        if (dataVencimento == null) throw new InvalidParamException("Data de Vencimento não pode ser Nula!");
        if (taxas < 0) throw new InvalidParamException("Taxas não podem ser negativas!");
        if (codigoBarras == null) throw new InvalidParamException("Código de Barras não pode ser Nulo!");
        if (dataPagamento == null) throw new InvalidParamException("Data de Vencimento não pode ser Nula!");
    }

    public static final void CONTRATOS(Integer locatarioID, Integer terrenoID,
                                       LocalDate dataAssinatura,
                                       LocalDate dataInicio, LocalDate dataFinal,
                                       Integer dataVencimentoAluguel) {

        if (locatarioID == null) throw new InvalidParamException("ID do Locatário não pode ser Nulo!");
        if (terrenoID == null) throw new InvalidParamException("ID do Terreno não pode ser Nulo!");
        if (dataAssinatura == null) throw new InvalidParamException("Data de Assinatura não pode ser Nula!");
        if (dataInicio == null) throw new InvalidParamException("Data de Início não pode ser Nula!");
        if (dataFinal == null) throw new InvalidParamException("Data Final não pode ser Nula!");
        if (dataVencimentoAluguel == null) throw new InvalidParamException("Dia de Vencimento não pode ser Nulo!");

    }

    public static final void MENSALIDADES(Double valorMensal, Integer anoExercicio) {

        if (valorMensal == null || valorMensal < 0) throw new InvalidParamException("O Valor mensal dever ser válido!");
        if (anoExercicio ==  null || anoExercicio < LocalDate.now().getYear()) throw new InvalidParamException("O ano de exercício deve ser válido");
    }
  
}
