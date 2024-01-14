package util.validar;

import infra.exceptions.DataFormatInvalidException;
import infra.exceptions.InvalidParamException;

import java.time.LocalDate;

import javax.swing.*;

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
    
    public void ALUGUEL_PAGAMENTOS() {

    }


    public static final void CONTRATOS(Integer locatarioID, Integer terrenoID,
                                       LocalDate dataAssinatura,
                                       LocalDate dataInicio, LocalDate dataFinal,
                                       LocalDate dataVencimentoAluguel) {

        if (locatarioID == null) throw new InvalidParamException("ID do Locatário não pode ser Nulo!");
        if (terrenoID == null) throw new InvalidParamException("ID do Terreno não pode ser Nulo!");
        if (dataAssinatura == null) throw new InvalidParamException("Data de Assinatura não pode ser Nula!");
        if (dataInicio == null) throw new InvalidParamException("Data de Início não pode ser Nula!");
        if (dataFinal == null) throw new InvalidParamException("Data Final não pode ser Nula!");
        if (dataVencimentoAluguel == null) throw new InvalidParamException("Dia de Vencimento não pode ser Nulo!");

    }
    
    public void TERRENOS() {

    }

    public static final void USUARIOS(String nome,
                 String sobrenome, 
                 String email, 
                 String senha, 
                 String cpf, 
                 LocalDate dataNascimento, 
                 String sexo, 
                 String ativo,
                 String celular,
                 String telefoneFixo) {

        if (nome.trim().isBlank()) throw new InvalidParamException("Nome não pode estar vazio!");
        if (sobrenome.trim().isBlank()) throw new InvalidParamException("Sobrenome não pode estar vazio!");
        if (email.trim().isBlank()) throw new InvalidParamException("Email não pode estar vazio!");
        if (senha.trim().isBlank()) throw new InvalidParamException("Senha não pode estar vazio!");
        if (cpf.trim().isBlank()) throw new InvalidParamException("CPF não pode estar vazio!");
        if (dataNascimento == null) throw new InvalidParamException("Data de Nascimento não pode estar vazio!");
        if (sexo.trim().isBlank()) throw new InvalidParamException("Sexo não pode estar vazio!");
        if (ativo.trim().isBlank()) throw new InvalidParamException("Ativo não pode estar vazio!");
        if (celular.trim().isBlank()) throw new InvalidParamException("Celular não pode estar vazio!");
        if (telefoneFixo.trim().isBlank()) throw new InvalidParamException("Telefone Fixo não pode estar vazio!");
    }


    public static final void MENSALIDADES(Integer contratoID, Double valorMensal, Integer anoExercicio) {

        if (contratoID == null) throw new InvalidParamException("ID do Contrato não pode ser Nulo!");
        if (valorMensal == null) throw new InvalidParamException("Valor não pode ser nulo");
        if (anoExercicio == null) throw new InvalidParamException("O ano de exercício não pode ser nulo");
    }
  
}
