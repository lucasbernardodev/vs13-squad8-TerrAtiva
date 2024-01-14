package controllers;

import infra.exceptions.*;
import models.Mensalidade;
import services.MensalidadeService;

public class MensalidadeController {

    MensalidadeService mensalidadeService = new MensalidadeService();
    public String cadastrarMensalidade(Mensalidade men) {
        try {
            mensalidadeService.adicionarMensalidade(men.getContratoID(), men.getValorMensal(), men.getAnoExercicio());
            return "Mensalidade Cadastrada Com Sucesso!";
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

    public String atualizarMensalidade(Integer mensalidadeID, Integer contratoID, Double valorMensal, Integer anoExercicio) {
        try {
            mensalidadeService.alterarMensalidade(mensalidadeID, contratoID, valorMensal, anoExercicio);
            return "Mensalidade Atualizada com Sucesso!";
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

    public String resgatarEnderecoPorID(Integer id){
        try {
            return mensalidadeService.resgatarMensalidadePorId(id).toString();
        } catch (DataNotFoundException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String deletarMensalidade(Integer id) {
        try {
            mensalidadeService.deletarMensalidade(id);
            return "Mensalidade deletada com Sucesso";
        } catch (DbException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e){
            return e.getMessage();
        }
    }
}
