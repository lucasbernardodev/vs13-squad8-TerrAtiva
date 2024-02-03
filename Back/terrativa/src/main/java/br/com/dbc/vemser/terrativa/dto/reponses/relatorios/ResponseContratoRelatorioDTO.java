package br.com.dbc.vemser.terrativa.dto.reponses.relatorios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseContratoRelatorioDTO {

    private Integer idContrato;
    private String ativo;
    private LocalDate dataAssinatura;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Integer dataVencimentoAluguel;
    private String nomeLocatario;
    private String sobrenomeLocatario;
    private String emailLocatario;
    private String cpfLocatario;
    private LocalDate dataNascimentoLocatario;
    private String sexoLocatario;
    private String celularLocatario;
    private String telefoneFixoLocatario;
    private Integer idTerreno;
    private Double preco;
    private String tamanho;
    private String nomeDono;
    private String sobrenomeDono;
    private String emailDono;
    private String cpfDono;
    private LocalDate dataNascimentoDono;
    private String sexoDono;
    private String celularDono;
    private String telefoneFixoDono;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer cep;
    private String localizacao;
    private String nomeEstado;
    private String nomeMunicipio;

}
