package br.com.dbc.vemser.terrativa.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadosMunicipios {

    private String nomeEstado;
    private Integer estadoCod;
    private String nomeMunicipio;
    private Integer municipioCodIBGE;
}
