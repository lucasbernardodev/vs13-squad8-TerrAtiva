package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "ENDERECO_TERRENOS")
public class EnderecoTerrenos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO_TERRENO")
    @SequenceGenerator(name = "SEQ_ENDERECO_TERRENO", sequenceName = "SEQ_ENDERECO_TERRENO", allocationSize = 1)
    @Column(name = "endereco_terreno_id")
    private Integer id;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "municipio_cod_ibge", insertable = false, updatable = false)
    private Integer codMunicipioIBGE;

    @Column(name = "cep")
    private Integer cep;

    @Column(name = "localizacao")
    private String localizacao;

    @JsonIgnore
    @OneToOne(mappedBy = "enderecoTerrenoID")
    @ToString.Exclude
    private Terreno terreno;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipio_cod_ibge", referencedColumnName = "municipio_cod_ibge")
    @ToString.Exclude
    private EstadosMunicipios codIBGE;

}
