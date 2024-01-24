package br.com.dbc.vemser.terrativa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Objeto de Transferência de Dados (DTO) para Solicitação de Usuário")
public class RequestUsuario {

    @Schema(description = "Identificador único do Usuário.", example = "1")
    private Integer usuarioId;

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Primeiro nome do Usuário.", example = "João")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    @Schema(description = "Sobrenome do Usuário.", example = "Silva")
    private String sobrenome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Schema(description = "Email do Usuário.", example = "joao.silva@exemplo.com")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    @Schema(description = "Senha do Usuário.", example = "senha123")
    private String senha;

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF deve ser válido")
    @Schema(description = "CPF do Usuário.", example = "123.456.789-00")
    private String cpf;

    @Past(message = "Data de Nascimento deve ser no passado")
    @Schema(description = "Data de nascimento do Usuário.", example = "2000-01-01")
    private LocalDate dataNascimento;

    @NotBlank(message = "Sexo é obrigatório")
    @Pattern(regexp="^[FM]$", message="Sexo deve ser 'F' ou 'M'")
    @Schema(description = "Sexo do Usuário.", example = "M")
    private String sexo;

    @NotEmpty(message = "Ativo é obrigatório")
    @Pattern(regexp="^[SN]$", message="Ativo deve ser 'S' ou 'N'")
    @Schema(description = "Status ativo do Usuário.", example = "S")
    private String ativo;

    @Length(min = 10, max = 11, message = "Celular deve ter 10 ou 11 dígitos")
    @Schema(description = "Número de celular do Usuário.", example = "999999999")
    private String celular;

    @Length(min = 10, max = 10, message = "Telefone Fixo deve ter 10 dígitos")
    @Schema(description = "Número de telefone fixo do Usuário.", example = "888888888")
    private String telefoneFixo;
}