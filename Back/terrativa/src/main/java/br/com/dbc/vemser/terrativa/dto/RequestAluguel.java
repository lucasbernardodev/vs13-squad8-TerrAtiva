package br.com.dbc.vemser.terrativa.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class RequestAluguel {
    @NotBlank(message = "O ID do pagamento não pode ser nulo")
    private Integer pagamentoID;
    private Integer mensalidadeID;
    private Integer mesReferencia;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private double taxas;
    private String codigoBarras;
    private LocalDate dataPagamento;
    private String pago;

    public RequestAluguel(){}
    public RequestAluguel(Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento,
                   double taxas, String codigoBarras, LocalDate dataPagamento) {

        this.mensalidadeID = mensalidadeID;
        this.mesReferencia = mesReferencia;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.taxas = taxas;
        this.codigoBarras = codigoBarras;
        this.dataPagamento = dataPagamento;
        this.pago = "N";
    }

    public RequestAluguel(Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento,
                   double taxas, String codigoBarras, LocalDate dataPagamento) {

        this.mesReferencia = mesReferencia;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.taxas = taxas;
        this.codigoBarras = codigoBarras;
        this.dataPagamento = dataPagamento;
        this.pago = "N";
    }

}
