package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestEnderecoTerrenos {

    private Integer id;

    @Size(max = 250, message = "O Logradouro deve ter no máximo 250 caracteres")
    @NotBlank(message = "Logradouro não pode estar vazio ou nulo!")
    private String logradouro;

    @NotNull
    private Integer numero;

    @Size(max = 250, message = "O Complemento deve ter no máximo 250 caracteres")
    private String complemento;

    @Size(max = 250, message = "O Bairro deve ter no máximo 250 caracteres")
    @NotBlank(message = "Bairro não pode ser vazio ou nulo!")
    private String bairro;

    @NotNull(message = "Código do município não pode ser vazio")
    private Integer codigoMunicipioIBGE;

    @NotNull(message = "O CEP não pode ser nulo.")
    @Digits(integer = 8, fraction = 0, message = "O CEP deve conter até 8 dígitos.")
    private Integer cep;

    @Size(max = 250, message = "A Localização deve ter no máximo 250 caracteres")
    private String localizacao;

}
