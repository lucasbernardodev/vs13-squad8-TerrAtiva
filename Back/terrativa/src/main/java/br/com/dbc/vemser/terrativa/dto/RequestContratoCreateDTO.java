package br.com.dbc.vemser.terrativa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Objeto de Transferência de Dados (DTO) para Solicitação de Contrato")
public class RequestContratoCreateDTO {

    @Schema(description = "Identificador único do Contrato.", example = "1")
    private Integer id;

    @NotNull(message = "O ID do proprietário não pode ser nulo")
    @Schema(description = "Identificador único do Proprietário.", example = "1")
    private Integer proprietarioID;

    @NotNull(message = "O ID do terreno não pode ser nulo")
    @Schema(description = "Identificador único do Terreno.", example = "1")
    private Integer terrenoID;

    @NotEmpty(message = "O campo ativo não pode estar vazio ou nulo")
    @Schema(description = "Status ativo do Contrato.", example = "S")
    private String ativo;

    @NotNull(message = "A data de assinatura não pode ser nula")
    @Schema(description = "Data de assinatura do Contrato.", example = "2022-01-01")
    private LocalDate dataAssinatura;

    @NotNull(message = "A data de início não pode ser nula")
    @Schema(description = "Data de início do Contrato.", example = "2022-01-01")
    private LocalDate dataInicio;

    @NotNull(message = "A data final não pode ser nula")
    @Schema(description = "Data final do Contrato.", example = "2023-01-01")
    private LocalDate dataFinal;

    @NotNull(message = "A data de vencimento do aluguel não pode ser nula")
    @Schema(description = "Data de vencimento do aluguel do Contrato.", example = "5")
    private Integer dataVencimentoAluguel;

}