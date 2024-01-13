package util.validar;

import infra.exceptions.DataFormatInvalidException;
import infra.exceptions.InvalidParamException;

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

    
    public static final void ENDERECO_TERRENOS(String logradouro,
                                      Integer numero, String complemento,
                                      String bairro, Integer codigoMunicipioIBGE,
                                      Integer cep, String localizacao) {

        if (logradouro.isBlank()) throw new InvalidParamException("Logradouro não pode estar vazio!");
        if (numero == null) throw new InvalidParamException("Número do Endereço não pode estar Nulo!");
        if (complemento.isBlank()) throw new InvalidParamException("Complemento do Endereço não pode estar vazio!");
        if (bairro.isBlank()) throw new InvalidParamException("Seu endereço deve possuir um bairro");
        if (codigoMunicipioIBGE == null) throw new InvalidParamException("Seu endereço deve possuir uma cidade associada!");
        if (cep == null) throw new InvalidParamException("O cep deve ser obrigatório!");
        if (cep.toString().length() != 9) throw new DataFormatInvalidException("Seu CEP deve conter 9 dígitos");
        if (localizacao.isBlank()) throw new InvalidParamException("Complemento do Endereço não pode estar vazio!");
    }


    
    public void ALUGUEL_PAGAMENTOS() {

    }

    
    public void CONTRATOS() {

    }

    
    public void MENSALIDADES() {

    }

    
    public void TERRENOS() {

    }

    
    public void USUARIOS() {

    }
}
