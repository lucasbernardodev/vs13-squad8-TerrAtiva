package br.com.dbc.vemser.terrativa.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestMensalidade {

    private Integer mensalidadeID;
    @NotNull
    private Integer contratoID;
    @NotNull
    @Positive
    private double valorMensal;
    @NotNull
    private Integer anoExercicio;

}
