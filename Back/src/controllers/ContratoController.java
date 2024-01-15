package controllers;

import infra.exceptions.*;
import services.ContratoService;

import java.sql.Date;

public class ContratoController {
    private ContratoService contratoService = new ContratoService();

    public String atualizarContrato(Integer proprietarioID, Integer terrenoID,
                                    Date dataAssinatura, Date dataInicio, Date dataFinal,
                                    Integer dataVencimentoAluguel) {
        try {
            contratoService.alterar(proprietarioID, terrenoID, dataAssinatura,
                    dataInicio, dataFinal, dataVencimentoAluguel);
            return "Contrato Atualizado Com Sucesso!";

        } catch (InvalidParamException | UnauthorizedOperationException | DbException |
                 DataFormatInvalidException | EntityIdNullException e) {
            return e.getMessage();
        }
    }

    public String resgatarContratoPorID(Integer id){
        try {
            return contratoService.resgatarContratoPorId(id).toString();

        } catch (DataNotFoundException | DbException e) {
            return e.getMessage();
        }
    }

    public String deletarContrato(Integer id) {
        try {
            contratoService.deletar(id);
            return "Contrato deletado com Sucesso";

        } catch (DbException | UnauthorizedOperationException e) {
            return e.getMessage();
        }
    }
}
