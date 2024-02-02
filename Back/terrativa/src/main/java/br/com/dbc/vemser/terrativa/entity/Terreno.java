package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Log4j2
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

    @Column(name = "criado", updatable = false)
    @CreationTimestamp
    private Timestamp criado;

    @Column(name = "editado")
    @UpdateTimestamp
    private Timestamp editado;

//    @PrePersist
//    protected void onCreate() {
//        log.info(LocalDateTime.now());
//        criado = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        log.info(LocalDateTime.now());
//        editado = LocalDateTime.now();
//    }

}
