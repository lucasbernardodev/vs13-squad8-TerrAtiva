package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "ativo")
    private String ativo;

    @Column(name = "celular")
    private String celular;

    @Column(name = "telefone_fixo")
    private String telefoneFixo;

    @JsonIgnore
    @OneToMany(mappedBy = "dono", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Terreno> terrenos;

    @JsonIgnore
    @OneToMany(mappedBy = "locatario", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Contrato> contrato;

    @JsonIgnore
    @ToString.Exclude
    @Column(name = "criado", updatable = false)
    @CreationTimestamp
    private Timestamp criado;

    @JsonIgnore
    @ToString.Exclude
    @Column(name = "editado")
    @UpdateTimestamp
    private Timestamp editado;
}
