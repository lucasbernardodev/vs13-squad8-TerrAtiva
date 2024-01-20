package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import br.com.dbc.vemser.terrativa.services.ContratoService;

import java.sql.Date;

public class ContratoController {
    private ContratoService contratoService = new ContratoService();

    public String atualizarContrato(Integer proprietarioID, Integer terrenoID,
                                    Date dataAssinatura, Date dataInicio, Date dataFinal,
                                    Integer dataVencimentoAluguel) {

            contratoService.alterar(proprietarioID, terrenoID, dataAssinatura,
                    dataInicio, dataFinal, dataVencimentoAluguel);
            return "Contrato Atualizado Com Sucesso!";

    }

    public String resgatarContratoPorID(Integer id){

            return contratoService.resgatarContratoPorId(id).toString();

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
