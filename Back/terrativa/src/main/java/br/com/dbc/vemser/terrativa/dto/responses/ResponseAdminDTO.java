package br.com.dbc.vemser.terrativa.dto.responses;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseAdminDTO {

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

    @NotBlank(message = "CPF é obrigatório")
    //@CPF(message = "CPF deve ser válido")
    @Schema(description = "CPF do Usuário.", example = "12345678900")
    private String cpf;

    @Past(message = "Data de Nascimento deve ser no passado")
    @Schema(description = "Data de nascimento do Usuário.", example = "2000-01-01")
    private LocalDate dataNascimento;

    @NotBlank(message = "Sexo é obrigatório")
    @Pattern(regexp="^[FM]$", message="Sexo deve ser 'F' ou 'M'")
    @Schema(description = "Sexo do Usuário.", example = "M")
    private String sexo;

    @Length(min = 10, max = 11, message = "Celular deve ter 10 ou 11 dígitos")
    @Schema(description = "Número de celular do Usuário.", example = "0123456789")
    private String celular;

    @Length(min = 10, max = 10, message = "Telefone Fixo deve ter 10 dígitos")
    @Schema(description = "Número de telefone fixo do Usuário.", example = "0123456789")
    private String telefoneFixo;

    @Schema(description = "Ativo.", example = "S")
    @Hidden
    private String ativo;

}
