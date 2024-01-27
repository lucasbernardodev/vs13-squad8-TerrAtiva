package br.com.dbc.vemser.terrativa.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMensalidadeDTO {

    private Integer mensalidadeID;
    private Integer contratoID;
    private double valorMensal;
    private Integer anoExercicio;

}
