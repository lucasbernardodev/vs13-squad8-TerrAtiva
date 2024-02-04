package br.com.dbc.vemser.terrativa.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFeedDTO {

    private Integer terrenoId;
    private String titulo;
    private Double preco;
    private String tamanho;
    private String criado;
    private String cidade;
    private String estado;

}
