package br.com.dbc.vemser.terrativa.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class DeletarContaDTO {

    @Schema(description = "Confirmação para deletar a conta.", example = "DELETAR MINHA CONTA")
    @Pattern(regexp="^DELETAR MINHA CONTA$", message="Confirmação deve ser 'DELETAR MINHA CONTA'")
    private String confirmacao;
}
