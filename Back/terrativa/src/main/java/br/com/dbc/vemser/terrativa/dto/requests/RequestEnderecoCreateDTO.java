package br.com.dbc.vemser.terrativa.dto.requests;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@Schema(description = "Objeto de Transferência de Dados (DTO) para Solicitação de Endereço")
public class RequestEnderecoCreateDTO {

    @Hidden
    private Integer id;

    @NotNull(message = "O ID do usuário não pode ser nulo")
    @Schema(description = "Identificador único do Usuário.", example = "1")
    private Integer usuarioID;

    @Size(max = 250, message = "O Logradouro deve ter no máximo 250 caracteres")
    @NotEmpty(message = "Logradouro não pode estar vazio ou nulo!")
    @Schema(description = "Lohgradouro", required = true, example = "Avenida Paulista")
    private String logradouro;

    @NotNull(message = "O número não pode ser Nulo!")
    @Schema(description = "Número", required = true, example = "1500")
    private Integer numero;

    @Size(max = 250, message = "O Complemento deve ter no máximo 250 caracteres")
    @Schema(description = "Complemento", required = true, example = "Apto 208")
    private String complemento;

    @Size(max = 250, message = "O Bairro deve ter no máximo 250 caracteres")
    @NotEmpty(message = "Bairro não pode ser vazio ou nulo!")
    @Schema(description = "Bairro", required = true, example = "Centro")
    private String bairro;

    @NotNull
    @Schema(description = "Código IBGE", required = true, example = "1100015")
    private Integer codigoMunicipioIBGE;

    @NotNull(message = "O CEP não pode ser nulo.")
    @Digits(integer = 8, fraction = 0, message = "O CEP deve conter até 8 dígitos.")
    @Schema(description = "CEP", required = true, example = "90900000")
    private Integer cep;

}
