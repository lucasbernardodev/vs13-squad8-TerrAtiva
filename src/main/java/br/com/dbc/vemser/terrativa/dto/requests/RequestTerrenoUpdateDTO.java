package br.com.dbc.vemser.terrativa.dto.requests;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Objeto de Transferência de Dados (DTO) para Solicitação de Terreno")
public class RequestTerrenoUpdateDTO {

    @Hidden
    @Schema(description = "Identificador único do Terreno.", example = "1")
    private Integer id;

    @NotEmpty(message = "Título é obrigatório")
    @Schema(description = "Título do Terreno.", example = "Terreno 1")
    private String titulo;

    @NotEmpty(message = "Descrição é obrigatória")
    @Schema(description = "Descrição do Terreno.", example = "Terreno localizado na região central")
    private String descricao;

    @NotNull(message = "O ID do proprietário não pode ser nulo")
    @Schema(description = "Identificador único do Proprietário.", example = "1")
    private Integer proprietarioID;

    private RequestEnderecoTerrenosCreateDTO endereco;

    @NotNull(message = "Preço é obrigatório")
    @Schema(description = "Preço do Terreno.", example = "500.00")
    private double preco;

    @NotEmpty(message = "Tamanho é obrigatório")
    @Schema(description = "Tamanho do Terreno.", example = "100000")
    private String tamanho;

    @Schema(description = "Disponibilidade do Terreno.", example = "S")
    private String disponivel;
}