package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUsuario {

    private Integer usuarioId;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    private String senha;

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF deve ser válido")
    private String cpf;

    @Past(message = "Data de Nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "Sexo é obrigatório")
    @Pattern(regexp="^[FM]$", message="Sexo deve ser 'F' ou 'M'")
    private String sexo;

    @NotEmpty(message = "Ativo é obrigatório")
    @Pattern(regexp="^[SN]$", message="Ativo deve ser 'S' ou 'N'")
    private String ativo;

    @Length(min = 10, max = 11, message = "Celular deve ter 10 ou 11 dígitos")
    private String celular;

    @Length(min = 10, max = 10, message = "Telefone Fixo deve ter 10 dígitos")
            private String telefoneFixo;

}
