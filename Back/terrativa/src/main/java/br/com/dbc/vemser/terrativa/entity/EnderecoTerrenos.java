package br.com.dbc.vemser.terrativa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoTerrenos {
    private Integer id;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer codigoMunicipioIBGE;
    private Integer cep;
    private String localizacao;

}