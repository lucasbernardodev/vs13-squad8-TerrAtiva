package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMensalidade {

    private Integer mensalidadeID;
    private Integer contratoID;
    private double valorMensal;
    private Integer anoExercicio;
    private LocalDate dataReajuste;

}
