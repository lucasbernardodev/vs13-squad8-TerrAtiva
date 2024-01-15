package controllers;

import infra.exceptions.*;
import models.Terreno;
import services.TerrenoService;

import java.time.DateTimeException;
import java.time.LocalDate;

public class TerrenoController {

    TerrenoService terrenoService = new TerrenoService();

    public String cadastrarTerreno(String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {
        try {
            terrenoService.cadastrarTerreno(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
            return "Terreno cadastrado com sucesso!";
        } catch (InvalidParamException | DataFormatInvalidException | UnauthorizedOperationException |
                 DbException | EntityIdNullException e) {
            return e.getMessage();
        }
    }

    public String atualizarTerreno(Integer id, String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {
        try {
            terrenoService.alterarTerreno(id,titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
            return "Dados do Terreno alterados com Sucesso!";

        } catch (InvalidParamException | DataFormatInvalidException | UnauthorizedOperationException | DbException e) {
            return e.getMessage();
        }
    }

    public Terreno resgatarTerrenoPorID(int id) {
        try {

            return terrenoService.buscarTerreno(id);
        } catch (DataNotFoundException | DbException e) {
            return null;
        }
    }

    public String deletarDados(int idTerreno) {
        try {
            terrenoService.deletarTerreno(idTerreno);
            return "Terreno deletado com Sucesso";

        } catch (DbException | UnauthorizedOperationException e) {
            return e.getMessage();
        }
    }

    public String arrendarTerreno(Integer proprietarioID, Integer terrenoID, LocalDate dataAssinatura, LocalDate dataInicio, LocalDate dataFinal,
                                  Integer dataVencimentoAluguel, // CONTRATO
                                  double valorMensal, Integer anoExercicio, // MENSALIDADE
                                  Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento,
                                  double taxas, String codigoBarras, LocalDate dataPagamento) {
        try {
            terrenoService.arrendarTerreno(proprietarioID, terrenoID, dataAssinatura, dataInicio, dataFinal, dataVencimentoAluguel,
                                            valorMensal, anoExercicio,
                                            mesReferencia, dataEmissao, dataVencimento, taxas, codigoBarras, dataPagamento);

            return "Terreno Arrendado com Sucesso!";
        } catch (DbException | UnauthorizedOperationException | DataFormatInvalidException | DateTimeException | InvalidParamException e) {
            return e.getMessage();
        }
    }
    public String cancelarContratoTerreno(Integer usuarioID, Integer contratoID) {
        try {
            terrenoService.cancelarContratoTerreno(usuarioID, contratoID);
            return "Contrato cancelado com sucesso!";
        } catch (DbException | UnauthorizedOperationException | DataFormatInvalidException | DateTimeException | InvalidParamException e) {
            return e.getMessage();
        }
    }

}
