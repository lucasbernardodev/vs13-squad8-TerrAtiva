package util.validar;

import infra.exceptions.DataFormatInvalidException;
import infra.exceptions.InvalidParamException;

import javax.swing.*;
import java.time.LocalDate;

public class ValidarModel {

    
    public static final void ENDERECO(Integer usuarioID, String logradouro,
                               Integer numero, String complemento,
                               String bairro, Integer codigoMunicipioIBGE,
                               Integer cep) {

        if (usuarioID == null) throw new InvalidParamException("ID do Usuário não pode ser Nulo!");
        if (logradouro.trim().isBlank()) throw new InvalidParamException("Logradouro não pode estar vazio!");
        if (numero == null) throw new InvalidParamException("Número do Endereço não pode estar Nulo!");
        if (complemento.trim().isBlank()) throw new InvalidParamException("Complemento do Endereço não pode estar vazio!");
        if (bairro.trim().isBlank()) throw new InvalidParamException("Seu endereço deve possuir um bairro");
        if (codigoMunicipioIBGE == null) throw new InvalidParamException("Seu endereço deve possuir uma cidade associada!");
        if (cep == null) throw new InvalidParamException("O cep deve ser obrigatório!");
        if (cep.toString().trim().length() != 9) throw new DataFormatInvalidException("Seu CEP deve conter 9 dígitos");
    }
  
  public static final void TERRENO(String titulo, String descricao, Integer proprietarioID, Integer enderecoID, Double preco, String tamanho, String disponivel){
        if(titulo == null || titulo.trim().isBlank()) throw new InvalidParamException("Titulo é obrigado para terreno!");
        if(descricao == null || descricao.trim().isBlank()) throw new InvalidParamException("Descrição é obrigatória para terreno");
        if(proprietarioID == null) throw new InvalidParamException("ID do proprietário é obrigatória para terreno");
        if(enderecoID == null) throw new InvalidParamException("ID do endereço é obrigatória para terreno");
        if(preco == null) throw new InvalidParamException("ID do endereço é obrigatória para terreno");
        if(tamanho == null || tamanho.trim().isBlank()) throw new InvalidParamException("Tamanho é obrigatória para terreno");
        if(disponivel == null || disponivel.trim().isBlank()) throw new InvalidParamException("Disponibilidade é obrigatória para terreno");

    }
    
    public static final void ENDERECO_TERRENOS(String logradouro,
                                      Integer numero, String complemento,
                                      String bairro, Integer codigoMunicipioIBGE,
                                      Integer cep, String localizacao) {

        if (logradouro.trim().isBlank()) throw new InvalidParamException("Logradouro não pode estar vazio!");
        if (numero == null) throw new InvalidParamException("Número do Endereço não pode estar Nulo!");
        if (complemento.trim().isBlank()) throw new InvalidParamException("Complemento do Endereço não pode estar vazio!");
        if (bairro.trim().isBlank()) throw new InvalidParamException("Seu endereço deve possuir um bairro");
        if (codigoMunicipioIBGE == null) throw new InvalidParamException("Seu endereço deve possuir uma cidade associada!");
        if (cep == null) throw new InvalidParamException("O cep deve ser obrigatório!");
        if (cep.toString().length() != 9) throw new DataFormatInvalidException("Seu CEP deve conter 9 dígitos");
        if (localizacao.trim().isBlank()) throw new InvalidParamException("Complemento do Endereço não pode estar vazio!");
    }
    
    public static final void ALUGUEL_PAGAMENTOS(Integer mensalidadeID, Integer mesReferencia,
                                   LocalDate dataEmissao, LocalDate dataVencimento,
                                   double taxas, String codigoBarras, LocalDate dataPagamento) {
        if (mensalidadeID == null) throw new InvalidParamException("ID da Mensalidade não pode ser Nulo!");
        if (mesReferencia == null) throw new InvalidParamException("Mês Referência não pode ser Nulo!");
        if (dataEmissao == null) throw new InvalidParamException("Data de Emissão não pode ser Nula!");
        if (dataVencimento == null) throw new InvalidParamException("Data de Vencimento não pode ser Nula!");
        if (taxas < 0) throw new InvalidParamException("Taxas não podem ser negativas!");
        if (codigoBarras == null) throw new InvalidParamException("Código de Barras não pode ser Nulo!");
        if (dataPagamento == null) throw new InvalidParamException("Data de Vencimento não pode ser Nula!");
    }


    
    public void CONTRATOS() {

    }

    
    public void MENSALIDADES() {

    }

    
    public void TERRENOS() {

    }

    
    public void USUARIOS() {

    
}}
