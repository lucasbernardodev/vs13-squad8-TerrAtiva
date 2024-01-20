package br.com.dbc.vemser.terrativa.controllers;


import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import br.com.dbc.vemser.terrativa.services.AluguelService;

import java.sql.SQLException;
import java.time.LocalDate;

public class AluguelController {
        AluguelService aluguelService  = new AluguelService();

        public String alterar(Integer pagamentoID, Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                                       LocalDate dataVencimento, double taxas, String codigoBarras,
                                       LocalDate dataPagamento) throws SQLException {

                aluguelService.alterar(pagamentoID, mensalidadeID, mesReferencia, dataEmissao, dataVencimento,
                        taxas, codigoBarras, dataPagamento);
                return "Dados do Aluguel alterados com Sucesso!";

        }

        public String resgatarAluguelPorID(int id) {
            try {
                return aluguelService.resgatarPorId(id).toString();
            } catch (DataNotFoundException | DbException e) {
                return e.getMessage();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public String deletar(int id) {
            try {
                aluguelService.deletar(id);
                return "Aluguel deletado com Sucesso";

            } catch (DbException | UnauthorizedOperationException | SQLException e) {
                return e.getMessage();
            }
        }
}
