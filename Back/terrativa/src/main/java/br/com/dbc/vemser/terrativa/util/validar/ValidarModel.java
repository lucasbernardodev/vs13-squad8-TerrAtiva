package br.com.dbc.vemser.terrativa.util.validar;

import br.com.dbc.vemser.terrativa.exceptions.DataFormatInvalidException;
import br.com.dbc.vemser.terrativa.exceptions.InvalidParamException;

import java.time.LocalDate;

public class ValidarModel {

    public static final void ENDERECOS(Integer usuarioID, String logradouro,
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
  
  public static final void TERRENOS(String titulo, String descricao, Integer proprietarioID, Integer enderecoID, Double preco, String tamanho, String disponivel){
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
    
    public static final void ALUGUEL_PAGAMENTOS(Integer mesReferencia,
                                   LocalDate dataEmissao, LocalDate dataVencimento,
                                   double taxas, String codigoBarras, LocalDate dataPagamento) {
        if (mesReferencia == null) throw new InvalidParamException("Mês Referência não pode ser Nulo!");
        if (dataEmissao == null) throw new InvalidParamException("Data de Emissão não pode ser Nula!");
        if (dataVencimento == null) throw new InvalidParamException("Data de Vencimento não pode ser Nula!");
        if (taxas < 0) throw new InvalidParamException("Taxas não podem ser negativas!");
        if (codigoBarras == null) throw new InvalidParamException("Código de Barras não pode ser Nulo!");
        if (dataPagamento == null) throw new InvalidParamException("Data de Vencimento não pode ser Nula!");
    }

    public static final void CONTRATOS(Integer locatarioID, Integer terrenoID,
                                       LocalDate dataAssinatura,
                                       LocalDate dataInicio, LocalDate dataFinal,
                                       Integer dataVencimentoAluguel) {

        if (locatarioID == null) throw new InvalidParamException("ID do Locatário não pode ser Nulo!");
        if (terrenoID == null) throw new InvalidParamException("ID do Terreno não pode ser Nulo!");
        if (dataAssinatura == null) throw new InvalidParamException("Data de Assinatura não pode ser Nula!");
        if (dataInicio == null) throw new InvalidParamException("Data de Início não pode ser Nula!");
        if (dataFinal == null) throw new InvalidParamException("Data Final não pode ser Nula!");
        if (dataVencimentoAluguel == null) throw new InvalidParamException("Dia de Vencimento não pode ser Nulo!");

    }

    public static final void USUARIOS(String nome,
                                       String sobrenome,
                                       String email,
                                       String cpf,
                                       LocalDate dataNascimento,
                                       String sexo,
                                       String celular,
                                       String telefoneFixo) {

        if (nome.trim().isBlank()) throw new InvalidParamException("Nome não pode estar vazio!");
        if (sobrenome.trim().isBlank()) throw new InvalidParamException("Sobrenome não pode estar vazio!");
        if (email.trim().isBlank()) throw new InvalidParamException("Email não pode estar vazio!");
        if (cpf.trim().isBlank()) throw new InvalidParamException("CPF não pode estar vazio!");
        if (dataNascimento == null) throw new InvalidParamException("Data de Nascimento não pode estar vazio!");
        if (sexo.trim().isBlank()) throw new InvalidParamException("Sexo não pode estar vazio!");
        if (celular.trim().isBlank()) throw new InvalidParamException("Celular não pode estar vazio!");
        if (telefoneFixo.trim().isBlank()) throw new InvalidParamException("Telefone Fixo não pode estar vazio!");
    }

    public static final void MENSALIDADES(Double valorMensal, Integer anoExercicio) {

        if (valorMensal == null || valorMensal < 0) throw new InvalidParamException("O Valor mensal dever ser válido!");
        if (anoExercicio ==  null || anoExercicio < LocalDate.now().getYear()) throw new InvalidParamException("O ano de exercício deve ser válido");
    }
  
}
