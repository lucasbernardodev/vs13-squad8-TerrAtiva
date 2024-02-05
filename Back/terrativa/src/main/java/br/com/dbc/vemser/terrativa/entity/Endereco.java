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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO")
    @SequenceGenerator(name = "SEQ_ENDERECO", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    @Column(name = "ENDERECO_ID")
    private Integer id;

    @Column(name = "usuario_id")
    private Integer usuarioID;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENDERECO_ID", referencedColumnName = "ENDERECO_TERRENO_ID")
    @ToString.Exclude
    private Terreno terreno;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MUNICIPIO_COD_IBGE", referencedColumnName = "MUNICIPIO_COD_IBGE")
    private EstadosMunicipios codIBGE;

    @Column(name = "cep")
    private Integer cep;

}
