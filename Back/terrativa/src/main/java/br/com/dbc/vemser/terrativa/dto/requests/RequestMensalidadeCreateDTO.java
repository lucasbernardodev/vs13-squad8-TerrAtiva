package br.com.dbc.vemser.terrativa.dto.requests;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Objeto de Transferência de Dados (DTO) para Solicitação de Mensalidade")
public class RequestMensalidadeCreateDTO {

    @Schema(description = "Identificador único do Usuário.", example = "1")
    private Integer mensalidadeID;

    @Hidden
    @Schema(description = "Identificador único do contrato.", example = "1")
    private Integer contratoID;

    @NotNull
    @Positive
    @Schema(description = "Valor mensal a ser pago", example = "2000")
    private double valorMensal;

    @NotNull
    @Schema(description = "Ano de exercicio", example = "2023")
    private Integer anoExercicio;

}
