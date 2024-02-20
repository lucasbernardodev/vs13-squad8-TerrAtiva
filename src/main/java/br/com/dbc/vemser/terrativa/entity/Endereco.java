package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "ENDERECOS")
public class Endereco {

    @Id
    @Column(name = "ENDERECO_ID")
    private Integer id;

    @JsonIgnore
    @Column(name = "usuario_id")
    @ToString.Exclude
    private Integer usuarioID;

    @JsonIgnore
    @OneToOne(mappedBy = "enderecoID")
    @ToString.Exclude
    private Usuario usuario;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "municipio_cod_ibge", insertable = false, updatable = false)
    private Integer codMunIBGE;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MUNICIPIO_COD_IBGE", referencedColumnName = "MUNICIPIO_COD_IBGE")
    private EstadosMunicipios codIBGE;

    @Column(name = "cep")
    private Integer cep;

}
