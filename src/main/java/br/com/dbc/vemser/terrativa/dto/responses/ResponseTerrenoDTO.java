package br.com.dbc.vemser.terrativa.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTerrenoDTO {

    private Integer id;
    private String titulo;
    private String descricao;
    private Integer proprietarioID;
    private ResponseEnderecoTerrenosDTO endereco;
    private double preco;
    private String tamanho;
    private String disponivel;

}
