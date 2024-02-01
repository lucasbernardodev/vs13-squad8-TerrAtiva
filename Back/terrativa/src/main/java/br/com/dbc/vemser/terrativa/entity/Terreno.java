package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "TERRENOS")
public class Terreno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TERRENO")
    @SequenceGenerator(name = "SEQ_TERRENO", sequenceName = "SEQ_TERRENO", allocationSize = 1)
    @Column(name = "terreno_id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dono_id", insertable = false, updatable = false)
    private Integer proprietarioID;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dono_id", referencedColumnName = "USUARIO_ID")
    private Usuario dono;

    @Column(name = "ENDERECO_TERRENO_ID")
    private Integer enderecoID;

    @Column(name = "preco")
    private double preco;

    @Column(name = "tamanho")
    private String tamanho;

    @Column(name = "disponivel")
    private String disponivel;

}
