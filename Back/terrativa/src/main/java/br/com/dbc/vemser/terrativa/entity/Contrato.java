package br.com.dbc.vemser.terrativa.entity;

import java.time.LocalDate;

public class Contrato {
    private Integer id;
    private Integer proprietarioID;
    private Integer terrenoID;
    private String ativo;
    private LocalDate dataAssinatura;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Integer dataVencimentoAluguel;

    public Contrato() {}

    public Contrato(Integer proprietarioID, Integer terrenoID, LocalDate dataAssinatura, LocalDate dataInicio,
                    LocalDate dataFinal, Integer dataVencimentoAluguel) {
        this.proprietarioID = proprietarioID;
        this.terrenoID = terrenoID;
        this.dataAssinatura = dataAssinatura;
        this.dataInicio = dataInicio;
        this.ativo = "S";
        this.dataFinal = dataFinal;
        this.dataVencimentoAluguel = dataVencimentoAluguel;
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

    public Integer getDataVencimentoAluguel() {
        return dataVencimentoAluguel;
    }

    public void setDataVencimentoAluguel(Integer dataVencimentoAluguel) {
        this.dataVencimentoAluguel = dataVencimentoAluguel;
    }
}