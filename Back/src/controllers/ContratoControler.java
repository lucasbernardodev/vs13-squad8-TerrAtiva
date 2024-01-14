package controllers;

import infra.exceptions.*;
import services.ContratoService;

import java.sql.Date;

public class ContratoControler {
    private ContratoService contratoService = new ContratoService();

    public String cadastrarContrato(Integer proprietarioID, Integer terrenoID, String ativo,
                                    Date dataAssinatura, Date dataInicio, Date dataFinal,
                                    Date dataVencimentoAluguel) {
        try {
            contratoService.alterar( proprietarioID, terrenoID, ativo, dataAssinatura,
                    dataInicio, dataFinal, dataVencimentoAluguel);
            return "Contrato Cadastrado Com Sucesso!";
        } catch (InvalidParamException e) {
            return e.getMessage();
        } catch (DataFormatInvalidException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String atualizarContrato(Integer proprietarioID, Integer terrenoID, String ativo,
                                    Date dataAssinatura, Date dataInicio, Date dataFinal,
                                    Date dataVencimentoAluguel) {
        try {
            contratoService.alterar( proprietarioID, terrenoID, ativo, dataAssinatura,
                    dataInicio, dataFinal, dataVencimentoAluguel);
            return "Contrato Atualizado Com Sucesso!";
        } catch (InvalidParamException e) {
            return e.getMessage();
        } catch (DataFormatInvalidException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String resgatarContratoPorID(Integer id){
        try {
            return contratoService.resgatarContratoPorID(id).toString();
        } catch (DataNotFoundException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String deletarContrato(Integer id) {
        try {
            contratoService.deletar(id);
            return "Endereço deletado com Sucesso";
        } catch (DbException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e){
            return e.getMessage();
        }
    }
}
