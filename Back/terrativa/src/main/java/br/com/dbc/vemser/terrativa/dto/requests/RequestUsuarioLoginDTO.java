package br.com.dbc.vemser.terrativa.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Objeto de Transferência de Dados (DTO) para Solicitação de Usuário")
public class RequestUsuarioLoginDTO {

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Schema(description = "Email do Usuário.", example = "joao.silva@exemplo.com")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    @Schema(description = "Senha do Usuário.", example = "senha123")
    private String senha;

}