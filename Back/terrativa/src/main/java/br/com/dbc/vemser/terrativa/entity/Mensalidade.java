package br.com.dbc.vemser.terrativa.entity;

import java.time.LocalDate;

public class Mensalidade {
    private Integer mensalidadeID;
    private Integer contratoID;
    private double valorMensal;
    private Integer anoExercicio;
    private LocalDate dataReajuste;

    public Mensalidade() {
    }

    public Mensalidade(Integer contratoID, double valorMensal,
                       Integer anoExercicio) {
        this.contratoID = contratoID;
        this.valorMensal = valorMensal;
        this.anoExercicio = anoExercicio;
    }
    public Mensalidade(double valorMensal,
                       Integer anoExercicio) {
        this.valorMensal = valorMensal;
        this.anoExercicio = anoExercicio;
    }

    public Mensalidade(Integer mensalidadeID, Integer contratoID, double valorMensal,
                       Integer anoExercicio) {
        this.mensalidadeID = mensalidadeID;
        this.contratoID = contratoID;
        this.valorMensal = valorMensal;
        this.anoExercicio = anoExercicio;
    }

    public Integer getMensalidadeID() {
        return mensalidadeID;
    }

    public void setMensalidadeID(Integer mensalidadeID) {
        this.mensalidadeID = mensalidadeID;
    }

    public Integer getContratoID() {
        return contratoID;
    }

    public void setContratoID(Integer contratoID) {
        this.contratoID = contratoID;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public Integer getAnoExercicio() {
        return anoExercicio;
    }

    public void setAnoExercicio(Integer anoExercicio) {
        this.anoExercicio = anoExercicio;
    }

    public LocalDate getDataReajuste() {
        return dataReajuste;
    }

    public void setDataReajuste(LocalDate dataReajuste) {
        this.dataReajuste = dataReajuste;
    }


}
