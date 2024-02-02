package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity(name = "ESTADO_MUNICIPIOS")
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


    @JsonIgnore
    @OneToMany(mappedBy = "codIBGE")
    @ToString.Exclude
    private Set<Endereco> endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "codIBGE")
    @ToString.Exclude
    private Set<EnderecoTerrenos> enderecoTerrenos;

}
