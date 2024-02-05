package br.com.dbc.vemser.terrativa.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFeedFiltradoDTO {

    private Integer terrenoId;
    private String titulo;
    private Double preco;
    private String tamanho;
    private Timestamp criado;
    private String cidade;
    private String estado;

}
