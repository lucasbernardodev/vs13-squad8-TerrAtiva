package br.com.dbc.vemser.terrativa.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestFeedCreateDTO {

    private int terrenoId;

    @Size(min = 2, max = 250, message = "O título deve ter entre 2 e 250 caracteres")
    @NotBlank
    private String titulo;

    @Size(min = 2, max = 650, message = "A descrição deve ter entre 2 e 650 caracteres")
    @NotBlank
    private String descricao;

    @Size(min = 2, max = 20, message = "O endereço deve ter entre 2 e 20 caracteres")
    @NotBlank
    private String preco;

    @NotBlank
    private String tamanho;

    @NotEmpty(message = "Estado não pode ser vazio ou nulo!")
    @NotBlank
    private String estado;

    @NotBlank
    @NotEmpty(message = "Cidade não pode ser vazio ou nulo!")
    private String cidade;

    @NotBlank
    private String cod_estado;

    @NotBlank
    private String quantidade;

    @NotBlank
    private String campoDeBusca;

}
