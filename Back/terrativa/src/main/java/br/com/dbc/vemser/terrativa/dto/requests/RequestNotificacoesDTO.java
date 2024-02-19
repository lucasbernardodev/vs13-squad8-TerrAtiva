package br.com.dbc.vemser.terrativa.dto.requests;

import br.com.dbc.vemser.terrativa.entity.Usuario;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestNotificacoesDTO {

    @NotBlank
    private String titulo;
    @NotEmpty
    private List<Integer> usuarios;
}
