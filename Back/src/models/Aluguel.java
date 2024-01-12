package models;

import java.time.Instant;
import java.time.LocalDate;

public class Aluguel {
    private Integer aluguelID;
    private Integer mensalidadeID;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private double taxas;
    private String codigoBarras;
    private LocalDate dataPagamento;
    private String pago;
    private Instant criado;
    private Instant editado;

    public Aluguel(){}
    public Aluguel(Integer mensalidadeID, LocalDate dataEmissao, LocalDate dataVencimento,
                   double taxas, String codigoBarras, LocalDate dataPagamento) {

        this.mensalidadeID = mensalidadeID;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.taxas = taxas;
        this.codigoBarras = codigoBarras;
        this.dataPagamento = dataPagamento;
        this.pago = "N";
    }

    public Integer getAluguelID() {
        return aluguelID;
    }

    public void setAluguelID(Integer aluguelID) {
        this.aluguelID = aluguelID;
    }

    public Integer getMensalidadeID() {
        return mensalidadeID;
    }

    public void setMensalidadeID(Integer mensalidadeID) {
        this.mensalidadeID = mensalidadeID;
    }

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

    public Instant getCriado() {
        return criado;
    }

    public void setCriado(Instant criado) {
        this.criado = criado;
    }

    public Instant getEditado() {
        return editado;
    }

    public void setEditado(Instant editado) {
        this.editado = editado;
    }
}
