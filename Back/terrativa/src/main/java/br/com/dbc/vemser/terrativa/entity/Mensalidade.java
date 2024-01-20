package br.com.dbc.vemser.terrativa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mensalidade {

    private Integer mensalidadeID;
    private Integer contratoID;
    private double valorMensal;
    private Integer anoExercicio;
    private LocalDate dataReajuste;

    public Mensalidade(Integer mensalidadeID, Integer contratoID, double valorMensal, Integer anoExercicio) {
        this.mensalidadeID = mensalidadeID;
        this.contratoID = contratoID;
        this.valorMensal = valorMensal;
        this.anoExercicio = anoExercicio;
    }

    public Mensalidade(double valorMensal, Integer anoExercicio) {
    }
}
