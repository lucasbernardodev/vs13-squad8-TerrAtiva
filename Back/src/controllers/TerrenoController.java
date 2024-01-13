package controllers;

import infra.exceptions.*;
import models.Terreno;
import services.TerrenoService;
import util.Formatador;

public class TerrenoController {

    TerrenoService terrenoService = new TerrenoService();

    public String cadastrarTerreno(String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {
        try {
            terrenoService.cadastrarTerreno(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
            return "Terreno cadastrado com sucesso!";
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

    public String atualizarTerreno(Integer id, String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {
        try {
            terrenoService.alterarTerreno(id,titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
            return "Dados do Terreno alterados com Sucesso!";

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

    public String resgatarTerrenoPorID(int id) {
        try {
            return terrenoService.buscarTerreno(id).toString();
        } catch (DataNotFoundException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String deletarDados(int idTerreno) {
        try {
            terrenoService.deletarTerreno(idTerreno);
            return "Terreno deletado com Sucesso";

        } catch (DbException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e){
            return e.getMessage();
        }
    }

}
