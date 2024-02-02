package br.com.dbc.vemser.terrativa.entity;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity(name = "ESTADOS_MUNICIPIOS")
public class EstadosMunicipios {

    @Id
    @Column(name = "MUNICIPIO_COD_IBGE")
    private Integer municipioCodIBGE;

    @Column(name = "NOME_ESTADO")
    private String nomeEstado;

    @Column(name = "ESTADO_COD")
    private Integer estadoCod;

    @Column(name = "NOME_MUNICIPIO")
    private String nomeMunicipio;


    @OneToMany(mappedBy = "municipioCodIBGE")
    @ToString.Exclude
    private Set<Endereco> endereco;


    @OneToMany(mappedBy = "municipioCodIBGE")
    @ToString.Exclude
    private Set<EnderecoTerrenos> enderecoTerrenos;

}
