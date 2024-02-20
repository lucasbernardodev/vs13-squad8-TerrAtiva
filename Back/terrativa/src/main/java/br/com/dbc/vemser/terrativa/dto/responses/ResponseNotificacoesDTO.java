package br.com.dbc.vemser.terrativa.dto.responses;

import br.com.dbc.vemser.terrativa.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNotificacoesDTO {

        private String id;
        private String titulo;
        private List<Integer> usuarios;
}
