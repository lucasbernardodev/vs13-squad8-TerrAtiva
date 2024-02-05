package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @Column(name = "dono_id", insertable = false, updatable = false)
    private Integer proprietarioID;

    @Column(name = "preco")
    private double preco;

    @Column(name = "tamanho")
    private String tamanho;

    @Column(name = "disponivel")
    private String disponivel;

    @Column(name = "criado", updatable = false)
    @CreationTimestamp
    private Timestamp criado;

    @Column(name = "editado")
    @UpdateTimestamp
    private Timestamp editado;

    @Column(name = "ENDERECO_TERRENO_ID", insertable = false, updatable = false)
    private Integer enderecoID;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dono_id", referencedColumnName = "USUARIO_ID")
    @ToString.Exclude
    private Usuario dono;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENDERECO_TERRENO_ID", referencedColumnName = "endereco_terreno_id")
    @ToString.Exclude
    private EnderecoTerrenos enderecoTerrenoID;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(mappedBy = "terreno")
    @ToString.Exclude
    private Contrato contrato;

}
