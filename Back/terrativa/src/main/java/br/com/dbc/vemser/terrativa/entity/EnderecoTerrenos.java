package br.com.dbc.vemser.terrativa.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "municipio_cod_ibge")
    private Integer codigoMunicipioIBGE;

    @Column(name = "cep")
    private Integer cep;

    @Column(name = "localizacao")
    private String localizacao;

}
