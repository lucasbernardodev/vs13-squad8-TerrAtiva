package br.com.dbc.vemser.terrativa.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFeedQuantidadeAnunciosDTO {

    private String nomeEstado;
    private Long quantidadeAnuncios;
    private Integer codigoEstado;
}
