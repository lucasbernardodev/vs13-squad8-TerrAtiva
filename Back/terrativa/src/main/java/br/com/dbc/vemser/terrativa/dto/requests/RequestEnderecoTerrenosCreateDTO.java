package br.com.dbc.vemser.terrativa.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Objeto de Transferência de Dados (DTO) para Solicitação de Endereço")
public class RequestEnderecoTerrenosCreateDTO {

    private Integer id;

    @Size(max = 250, message = "O Logradouro deve ter no máximo 250 caracteres")
    @NotBlank(message = "Logradouro não pode estar vazio ou nulo!")
    @Schema(description = "Lohgradouro", required = true, example = "Avenida Paulista")
    private String logradouro;

    @NotNull
    @Schema(description = "Número", required = true, example = "1500")
    private Integer numero;

    @Size(max = 250, message = "O Complemento deve ter no máximo 250 caracteres")
    @Schema(description = "Complemento", required = true, example = "Apto 208")
    private String complemento;

    @Size(max = 250, message = "O Bairro deve ter no máximo 250 caracteres")
    @NotBlank(message = "Bairro não pode ser vazio ou nulo!")
    @Schema(description = "Bairro", required = true, example = "Centro")
    private String bairro;

    @NotNull(message = "Código do município não pode ser vazio")
    @Schema(description = "Código IBGE", required = true, example = "36901")
    private Integer codigoMunicipioIBGE;

    @NotNull(message = "O CEP não pode ser nulo.")
    @Digits(integer = 8, fraction = 0, message = "O CEP deve conter até 8 dígitos.")
    @Schema(description = "CEP", required = true, example = "46859727080")
    private Integer cep;

    @Size(max = 250, message = "A Localização deve ter no máximo 250 caracteres")
    @Schema(description = "Localização", required = true, example = "Prédio das rosas")
    private String localizacao;

}
