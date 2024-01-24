package br.com.dbc.vemser.terrativa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    private Integer usuarioId;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private String ativo;
    private String celular;
    private String telefoneFixo;

    public Usuario(Integer id, String nome, String sobrenome, String email, String cpf, LocalDate dataNascimento, String sexo, String celular, String telefoneFixo) {
    }
}
