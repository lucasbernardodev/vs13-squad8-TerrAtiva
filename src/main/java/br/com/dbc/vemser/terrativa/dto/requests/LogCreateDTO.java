package br.com.dbc.vemser.terrativa.dto.requests;

import br.com.dbc.vemser.terrativa.entity.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogCreateDTO {
    @Enumerated(EnumType.STRING)
    private TipoLog tipoLog;
    private String descricao;
    private String data;
}
