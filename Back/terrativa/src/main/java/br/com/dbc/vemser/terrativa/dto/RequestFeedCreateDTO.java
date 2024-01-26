package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestFeedCreateDTO {

    private int terrenoId;
    private String titulo;
    private String descricao;
    private String preco;
    private String tamanho;
    private String estado;
    private String cidade;
    private String cod_estado;
    private String quantidade;
    private String campoDeBusca;

}
