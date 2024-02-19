package br.com.dbc.vemser.terrativa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogContador {
    @Id
    private TipoLog tipoLog;
    private Integer quantidade;
}
