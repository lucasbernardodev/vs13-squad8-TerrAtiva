package br.com.dbc.vemser.terrativa.dto.reponses;

import br.com.dbc.vemser.terrativa.entity.Terreno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUsuarioDTO {

    private Integer usuarioId;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private String celular;
    private String telefoneFixo;
    private ResponseEnderecoDTO endereco;
    private Set<Terreno> terrenos;

}
