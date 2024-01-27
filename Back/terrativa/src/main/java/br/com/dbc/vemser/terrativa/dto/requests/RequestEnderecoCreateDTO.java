package br.com.dbc.vemser.terrativa.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class RequestEnderecoCreateDTO {

    private Integer id;

    private Integer usuarioID;
    @Size(max = 250, message = "O Logradouro deve ter no máximo 250 caracteres")
    @NotEmpty(message = "Logradouro não pode estar vazio ou nulo!")
    private String logradouro;

    @NotNull(message = "O número não pode ser Nulo!")
    private Integer numero;

    @Size(max = 250, message = "O Complemento deve ter no máximo 250 caracteres")
    private String complemento;

    @Size(max = 250, message = "O Bairro deve ter no máximo 250 caracteres")
    @NotEmpty(message = "Bairro não pode ser vazio ou nulo!")
    private String bairro;

    @NotNull
    private Integer codigoMunicipioIBGE;

    @NotNull(message = "O CEP não pode ser nulo.")
    @Digits(integer = 8, fraction = 0, message = "O CEP deve conter até 8 dígitos.")
    private Integer cep;

}
