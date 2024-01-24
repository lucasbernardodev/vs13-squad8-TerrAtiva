package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestContrato {

    private Integer id;

    @NotNull(message = "O ID do proprietário não pode ser nulo")
    private Integer proprietarioID;

    @NotNull(message = "O ID do terreno não pode ser nulo")
    private Integer terrenoID;

    @NotEmpty(message = "O campo ativo não pode estar vazio ou nulo")
    private String ativo;

    @NotNull(message = "A data de assinatura não pode ser nula")
    private LocalDate dataAssinatura;

    @NotNull(message = "A data de início não pode ser nula")
    private LocalDate dataInicio;

    @NotNull(message = "A data final não pode ser nula")
    private LocalDate dataFinal;

    @NotNull(message = "A data de vencimento do aluguel não pode ser nula")
    private Integer dataVencimentoAluguel;

}
