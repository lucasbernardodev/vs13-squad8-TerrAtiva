package util.validar;

import infra.exceptions.DataFormatInvalidException;
import infra.exceptions.InvalidParamException;

import javax.swing.*;

public class ValidarModel {
    public static final void ENDERECO(Integer usuarioID, String logradouro,
                               Integer numero, String complemento,
                               String bairro, Integer codigoMunicipioIBGE,
                               Integer cep) {

        if (usuarioID == null) throw new InvalidParamException("ID do Usuário não pode ser Nulo!");
        if (logradouro.isBlank()) throw new InvalidParamException("Logradouro não pode estar vazio!");
        if (numero == null) throw new InvalidParamException("Número do Endereço não pode estar Nulo!");
        if (complemento.isBlank()) throw new InvalidParamException("Complemento do Endereço não pode estar vazio!");
        if (bairro.isBlank()) throw new InvalidParamException("Seu endereço deve possuir um bairro");
        if (codigoMunicipioIBGE == null) throw new InvalidParamException("Seu endereço deve possuir uma cidade associada!");
        if (cep == null) throw new InvalidParamException("O cep deve ser obrigatório!");
        if (cep.toString().length() != 9) throw new DataFormatInvalidException("Seu CEP deve conter 9 dígitos");
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
}
