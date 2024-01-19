package br.com.dbc.vemser.terrativa.backup.controllers;


import br.com.dbc.vemser.terrativa.backup.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.backup.exceptions.DbException;
import br.com.dbc.vemser.terrativa.backup.exceptions.UnauthorizedOperationException;
import br.com.dbc.vemser.terrativa.services.AluguelService;

import java.time.LocalDate;

public class AluguelController {
        AluguelService aluguelService  = new AluguelService();

        public String alterar(Integer pagamentoID, Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                                       LocalDate dataVencimento, double taxas, String codigoBarras,
                                       LocalDate dataPagamento) {

                aluguelService.alterar(pagamentoID, mensalidadeID, mesReferencia, dataEmissao, dataVencimento,
                        taxas, codigoBarras, dataPagamento);
                return "Dados do Aluguel alterados com Sucesso!";

        }

        public String resgatarAluguelPorID(int id) {
            try {
                return aluguelService.resgatarPorId(id).toString();
            } catch (DataNotFoundException | DbException e) {
                return e.getMessage();
            }
        }

        public String deletar(int id) {
            try {
                aluguelService.deletar(id);
                return "Aluguel deletado com Sucesso";

            } catch (DbException | UnauthorizedOperationException e) {
                return e.getMessage();
            }
        }
}
