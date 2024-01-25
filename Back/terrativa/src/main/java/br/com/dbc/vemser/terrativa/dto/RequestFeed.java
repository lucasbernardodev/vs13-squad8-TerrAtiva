package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestFeed {

    private int terrenoId;
    private String titulo;
    private String descricao;
    private double preco;
    private int tamanho;
    private String estado;
    private String cidade;
    private String cod_estado;

}
