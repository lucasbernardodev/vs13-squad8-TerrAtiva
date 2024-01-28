package br.com.dbc.vemser.terrativa.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFeedDTO {

    private int terrenoId;
    private String titulo;
    private String descricao;
    private double preco;
    private int tamanho;
    private String estado;
    private String cidade;
}
