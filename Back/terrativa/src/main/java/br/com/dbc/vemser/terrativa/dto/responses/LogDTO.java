package br.com.dbc.vemser.terrativa.dto.responses;

import br.com.dbc.vemser.terrativa.entity.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {

    private String id;
    private TipoLog tipoLog;
    private String descricao;
    private String data;
}