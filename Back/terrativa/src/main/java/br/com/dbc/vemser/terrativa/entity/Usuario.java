package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "USUARIOS")
public class Usuario implements UserDetails {

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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "endereco_id")
    @ToString.Exclude
    private Endereco enderecoID;

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

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USUARIO_CARGOS",
            joinColumns = @JoinColumn(name = "USUARIO_ID"),
            inverseJoinColumns = @JoinColumn(name = "CARGO_ID")
    )
    @ToString.Exclude
    private Set<Cargo> cargos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return cargos;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
