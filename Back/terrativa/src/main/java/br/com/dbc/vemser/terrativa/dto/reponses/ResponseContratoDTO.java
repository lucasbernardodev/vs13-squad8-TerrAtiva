package br.com.dbc.vemser.terrativa.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseContratoDTO {

    private Integer id;
    private Integer proprietarioID;
    private Integer terrenoID;
    private String ativo;
    private LocalDate dataAssinatura;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Integer dataVencimentoAluguel;
}
