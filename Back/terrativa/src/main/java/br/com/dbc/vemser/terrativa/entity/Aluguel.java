package br.com.dbc.vemser.terrativa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aluguel {

    private Integer pagamentoID;
    private Integer mensalidadeID;
    private Integer mesReferencia;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private Double taxas;
    private String codigoBarras;
    private LocalDate dataPagamento;
    private String pago = "N";

    public Aluguel(Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento, double taxas, String codigoBarras, LocalDate dataPagamento) {
    }
}
