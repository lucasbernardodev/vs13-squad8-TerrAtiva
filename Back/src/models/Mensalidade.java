package models;

import java.time.Instant;
import java.time.LocalDate;

public class Mensalidade {
        private Integer mensalidadeID;
        private Integer contratoID;
        private double valorMensal;
        private Integer anoExercicio;
        private LocalDate dataReajuste;

    private Instant criado;
    private Instant editado;

    public Mensalidade() {}

    public Mensalidade(Integer contratoID, double valorMensal,
                       Integer anoExercicio) {
        this.contratoID = contratoID;
        this.valorMensal = valorMensal;
        this.anoExercicio = anoExercicio;
        this.criado = Instant.now();
        this.editado = Instant.now();
    }

    public Mensalidade(Integer mensalidadeID, Integer contratoID, double valorMensal,
                       Integer anoExercicio) {
        this.mensalidadeID = mensalidadeID;
        this.contratoID = contratoID;
        this.valorMensal = valorMensal;
        this.anoExercicio = anoExercicio;
        this.criado = Instant.now();
        this.editado = Instant.now();
    }


    public Mensalidade(Integer mensalidadeID, Integer contratoID, double valorMensal,
                       Integer anoExercicio, LocalDate dataReajuste) {

        this.mensalidadeID = mensalidadeID;
        this.contratoID = contratoID;
        this.valorMensal = valorMensal;
        this.anoExercicio = anoExercicio;
        this.dataReajuste = dataReajuste;
        this.criado = Instant.now();
        this.editado = Instant.now();
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


    @Override
    public String toString(){
        return "---------Mensalidade---------\nID da Mensalidades: " + mensalidadeID + "\nID do Contrato: " + contratoID + "\nValor mensal: R$ " + valorMensal + "\nAno de Exercicios: " + anoExercicio;
    }

}
