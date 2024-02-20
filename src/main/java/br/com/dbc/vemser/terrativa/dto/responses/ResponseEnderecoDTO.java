package br.com.dbc.vemser.terrativa.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseEnderecoDTO {

    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer cep;
    private String estado;
    private String cidade;

}