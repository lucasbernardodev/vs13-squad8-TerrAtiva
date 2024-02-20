package br.com.dbc.vemser.terrativa.dto.responses;

import br.com.dbc.vemser.terrativa.entity.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogContadorDTO {
    private TipoLog tipoLog;
    private Integer quantidade;
}
