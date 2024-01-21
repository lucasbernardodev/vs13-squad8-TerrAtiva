package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class ResponseEndereco {

    private Integer id;
    private Integer usuarioID;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer codigoMunicipioIBGE;
    private Integer cep;

    public ResponseEndereco() {
        Integer id;
        Integer usuarioID;
        String logradouro;
        Integer numero;
        String complemento;
        String bairro;
        Integer codigoMunicipioIBGE;
        Integer cep;
    }
}