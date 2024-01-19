package br.com.dbc.vemser.terrativa.backup.controllers;

import br.com.dbc.vemser.terrativa.models.Terreno;
import br.com.dbc.vemser.terrativa.services.TerrenoService;


import java.time.LocalDate;

public class TerrenoController {

    TerrenoService terrenoService = new TerrenoService();

    public String cadastrarTerreno(String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {

            terrenoService.cadastrarTerreno(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
            return "Terreno cadastrado com sucesso!";

    }

    public String atualizarTerreno(Integer id, String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {

            terrenoService.alterarTerreno(id,titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
            return "Dados do Terreno alterados com Sucesso!";


    }

    public Terreno resgatarTerrenoPorID(int id) {
            return terrenoService.buscarTerreno(id);
    }

    public String deletarDados(int idTerreno) {
            terrenoService.deletarTerreno(idTerreno);
            return "Terreno deletado com Sucesso";
    }

    public String arrendarTerreno(Integer proprietarioID, Integer terrenoID, LocalDate dataAssinatura, LocalDate dataInicio, LocalDate dataFinal,
                                  Integer dataVencimentoAluguel, // CONTRATO
                                  double valorMensal, Integer anoExercicio, // MENSALIDADE
                                  Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento,
                                  double taxas, String codigoBarras, LocalDate dataPagamento) {

            terrenoService.arrendarTerreno(proprietarioID, terrenoID, dataAssinatura, dataInicio, dataFinal, dataVencimentoAluguel,
                                            valorMensal, anoExercicio,
                                            mesReferencia, dataEmissao, dataVencimento, taxas, codigoBarras, dataPagamento);

            return "Terreno Arrendado com Sucesso!";
    }
    public String cancelarContratoTerreno(Integer usuarioID, Integer contratoID) {
            terrenoService.cancelarContratoTerreno(usuarioID, contratoID);
            return "Contrato cancelado com sucesso!";
    }

}
