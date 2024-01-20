package br.com.dbc.vemser.terrativa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
public class Aluguel {

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
    
    public Aluguel(Integer mensalidadeID, Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento,
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

    public Aluguel(Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento,
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
