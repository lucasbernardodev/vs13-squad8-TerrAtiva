package br.com.dbc.vemser.terrativa.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSenhaDTO {

    @NotBlank
    @Schema(description = "Senha", required = true, example = "Senha atual")
    private String senhaAtual;

    @NotBlank
    @Schema(description = "Nova Senha", required = true, example = "Nova Senha")
    private String senhaNova;

    @NotBlank
    @Schema(description = "Nova Senha", required = true, example = "Nova Senha")
    private String senhaNovaConf;
}
