package br.com.dbc.vemser.terrativa.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFeedQuantidadeAnunciosDTO {

    private String estado;
    private String cod_estado;
    private String quantidade;
}
