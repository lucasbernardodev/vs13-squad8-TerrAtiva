package br.com.dbc.vemser.terrativa.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFeedDTO {

    private Integer terrenoId;
    private String titulo;
    private String descricao;
    private String propietario;
    private Double preco;
    private String tamanho;
    private String criado;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer cep;
    private String localizacao;
    private String cidade;
    private String estado;

}
