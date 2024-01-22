package br.com.dbc.vemser.terrativa.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mensalidade {

    private Integer mensalidadeID;
    private Integer contratoID;
    private double valorMensal;
    private Integer anoExercicio;


    public Mensalidade(double valorMensal, Integer anoExercicio ){
        this.valorMensal = valorMensal;
        this.anoExercicio = anoExercicio;
    }



}
