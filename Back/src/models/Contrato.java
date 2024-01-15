package models;

import java.time.Instant;
import java.time.LocalDate;

public class Contrato {
    private Integer id;
    private Integer proprietarioID;
    private Integer terrenoID;
    private String ativo;
    private LocalDate dataAssinatura;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private LocalDate dataVencimentoAluguel;

    public Contrato() {}

    public Contrato(Integer proprietarioID, Integer terrenoID, String ativo,
                    LocalDate dataAssinatura, LocalDate dataInicio, LocalDate dataFinal,
                    LocalDate dataVencimentoAluguel) {

        this.proprietarioID = proprietarioID;
        this.terrenoID = terrenoID;
        this.ativo = ativo;
        this.dataAssinatura = dataAssinatura;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.dataVencimentoAluguel = dataVencimentoAluguel;
    }

    public Contrato(Integer terrenoID, String ativo, LocalDate toLocalDate, LocalDate toLocalDate1, LocalDate toLocalDate2, LocalDate toLocalDate3) {
    }

    public Contrato(Integer proprietarioID, Integer terrenoID, LocalDate toLocalDate, LocalDate toLocalDate1, LocalDate toLocalDate2, LocalDate toLocalDate3) {
    }

    public Contrato(Integer terrenoID, LocalDate toLocalDate, LocalDate toLocalDate1, LocalDate toLocalDate2, LocalDate toLocalDate3) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProprietarioID() {
        return proprietarioID;
    }

    public void setProprietarioID(Integer proprietarioID) {
        this.proprietarioID = proprietarioID;
    }

    public Integer getTerrenoID() {
        return terrenoID;
    }

    public void setTerrenoID(Integer terrenoID) {
        this.terrenoID = terrenoID;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(LocalDate dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataVencimentoAluguel() {
        return dataVencimentoAluguel;
    }

    public void setDataVencimentoAluguel(LocalDate dataVencimentoAluguel) {
        this.dataVencimentoAluguel = dataVencimentoAluguel;
    }

}