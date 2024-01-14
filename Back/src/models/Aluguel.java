package models;

import java.time.Instant;
import java.time.LocalDate;

public class Aluguel {
    private Integer pagamentoID;
    private Integer mensalidadeID;
    private Integer mesReferencia;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private double taxas;
    private String codigoBarras;
    private LocalDate dataPagamento;
    private String pago;

    public Aluguel(){}
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

    public Integer getPagamentoID() {
        return pagamentoID;
    }

    public void setPagamentoID(Integer pagamentoID) {
        this.pagamentoID = pagamentoID;
    }

    public Integer getMensalidadeID() {
        return mensalidadeID;
    }

    public void setMensalidadeID(Integer mensalidadeID) {
        this.mensalidadeID = mensalidadeID;
    }

    public int getMesReferencia() { return mesReferencia; }

    public void setMesReferencia(int mesReferencia) { this.mesReferencia = mesReferencia; }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getTaxas() {
        return taxas;
    }

    public void setTaxas(double taxas) {
        this.taxas = taxas;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Aluguel{" +
                "pagamentoID=" + pagamentoID +
                ", mensalidadeID=" + mensalidadeID +
                ", mesReferencia=" + mesReferencia +
                ", dataEmissao=" + dataEmissao +
                ", dataVencimento=" + dataVencimento +
                ", taxas=" + taxas +
                ", codigoBarras='" + codigoBarras + '\'' +
                ", dataPagamento=" + dataPagamento +
                ", pago='" + pago + '\'' +
                '}';
    }
}
