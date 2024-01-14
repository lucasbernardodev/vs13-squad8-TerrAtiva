package controllers;

import infra.exceptions.*;
import services.AluguelService;

import java.time.LocalDate;

public class AluguelController {
        AluguelService aluguelService  = new AluguelService();

        public String cadastrarAluguel(Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                                       LocalDate dataVencimento, double taxas, String codigoBarras,
                                       LocalDate dataPagamento) {
            try {
                aluguelService.cadastrarAluguel(mensalidadeID, mesReferencia, dataEmissao, dataVencimento,
                        taxas, codigoBarras, dataPagamento);
                return "Aluguel cadastrado com sucesso!";
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

        public String alterar(Integer pagamentoID, Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao,
                                       LocalDate dataVencimento, double taxas, String codigoBarras,
                                       LocalDate dataPagamento) {
            try {
                aluguelService.alterar(pagamentoID, mensalidadeID, mesReferencia, dataEmissao, dataVencimento,
                        taxas, codigoBarras, dataPagamento);
                return "Dados do Aluguel alterados com Sucesso!";

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

        public String resgatarAluguelPorID(int id) {
            try {
                return aluguelService.resgatarPorId(id).toString();
            } catch (DataNotFoundException e) {
                return e.getMessage();
            } catch (DbException e) {
                return e.getMessage();
            }
        }

        public String deletar(int id) {
            try {
                aluguelService.deletar(id);
                return "Aluguel deletado com Sucesso";

            } catch (DbException e) {
                return e.getMessage();
            } catch (UnauthorizedOperationException e){
                return e.getMessage();
            }
        }
}
