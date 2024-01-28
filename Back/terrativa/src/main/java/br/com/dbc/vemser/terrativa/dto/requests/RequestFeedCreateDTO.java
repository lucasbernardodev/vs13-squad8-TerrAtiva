package br.com.dbc.vemser.terrativa.dto.requests;

import br.com.dbc.vemser.terrativa.entity.Estados;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Objeto de Transferência de Dados (DTO) para Solicitação de Feed")
public class RequestFeedCreateDTO {

    private int Id;

    @Size(min = 2, max = 250, message = "O título deve ter entre 2 e 250 caracteres")
    @NotBlank
    private String titulo;

    @Size(min = 2, max = 650, message = "A descrição deve ter entre 2 e 650 caracteres")
    @NotBlank
    private String descricao;

    @Size(min = 2, max = 20, message = "O endereço deve ter entre 2 e 20 caracteres")
    @NotBlank
    private Double preco;

    @NotBlank
    private String tamanho;

    @NotEmpty(message = "Estado não pode ser vazio ou nulo!")
    @NotBlank
    private String estado;

    @NotBlank
    @NotEmpty(message = "Cidade não pode ser vazio ou nulo!")
    private String cidade;


    private Estados cod_estado;


    private String quantidade;


    private String campoDeBusca;


    private Integer proprietarioID;


    private Integer enderecoID;


    private String disponivel;


    private String logradouro;


    private Integer numero;


    private String complemento;


    private String bairro;


    private Integer codigoMunicipioIBGE;


    private Integer cep;


    private String localizacao;

}
