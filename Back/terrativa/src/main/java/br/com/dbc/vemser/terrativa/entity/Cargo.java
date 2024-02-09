package br.com.dbc.vemser.terrativa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CARGOS")
public class Cargo implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_CARGO")
    private int idCargo;

    @Column(name = "NOME")
    private String nome;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USUARIOS_CARGOS",
            joinColumns = @JoinColumn(name = "CARGO_ID"),
            inverseJoinColumns = @JoinColumn(name = "USUARIO_ID")
    )
    private Set<Usuario> usuarios;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return nome;
    }
}
