package br.com.dbc.vemser.terrativa.dto.reponses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFeedUsuarioDTO {

    private Integer id;
    private String titulo;
    private String descricao;
    private Integer proprietarioID;
    private Integer enderecoID;
    private double preco;
    private String tamanho;
    private String disponivel;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer codigoMunicipioIBGE;
    private Integer cep;
    private String localizacao;

}
