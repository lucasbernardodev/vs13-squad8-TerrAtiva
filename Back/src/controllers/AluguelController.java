package controllers;

import infra.exceptions.*;
import services.AluguelService;

import java.time.LocalDate;

public class AluguelController {
        AluguelService aluguelService  = new AluguelService();

        public String alterar(Integer pagamentoID, Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                                       LocalDate dataVencimento, double taxas, String codigoBarras,
                                       LocalDate dataPagamento) {
            try {
                aluguelService.alterar(pagamentoID, mensalidadeID, mesReferencia, dataEmissao, dataVencimento,
                        taxas, codigoBarras, dataPagamento);
                return "Dados do Aluguel alterados com Sucesso!";

            } catch (InvalidParamException | DataFormatInvalidException | UnauthorizedOperationException |
                     DbException | EntityIdNullException e) {
                return e.getMessage();
            }
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
