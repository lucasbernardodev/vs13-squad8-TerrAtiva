package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUsuario {

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

}
