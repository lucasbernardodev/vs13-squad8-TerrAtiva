package br.com.dbc.vemser.terrativa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedUsuariosAlugados {

    private Integer contratoID;
    private Integer locatarioID;
    private Integer terrenoID;
    private String ativo;
    private Date dataAssinatura;
    private Date dataInicio;
    private Date dataFinal;
    private Integer dataVencimentoAluguel;
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
