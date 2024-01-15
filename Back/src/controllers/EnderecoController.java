package controllers;

import infra.exceptions.*;
import services.EnderecoService;

public class EnderecoController {

    private final EnderecoService enderecoService = new EnderecoService();

    public String cadastrarEndereco(Integer usuarioID, String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep) {
        try {
            enderecoService.adicionarEndereco(usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep);
            return "Endereço Cadastrado Com Sucesso!";
        } catch (InvalidParamException | UnauthorizedOperationException | DbException | EntityIdNullException e) {
            return e.getMessage();
        }
    }

    public String atualizarEndereco(Integer enderecoID, Integer usuarioID, String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep) {
        try {
            enderecoService.alterar(enderecoID, usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep);
            return "Endereço Atualizado Com Sucesso!";
        } catch (InvalidParamException | DataFormatInvalidException | UnauthorizedOperationException | DbException e) {
            return e.getMessage();
        }
    }

    public String resgatarEnderecoPorID(Integer id){
        try {
            return enderecoService.resgatarPorId(id).toString();
        } catch (DataNotFoundException | DbException e) {
            return e.getMessage();
        }
    }

    public String deletarEndereco(Integer id) {
        try {
            enderecoService.deletar(id);
            return "Endereço deletado com Sucesso";
        } catch (DbException | UnauthorizedOperationException e) {
            return e.getMessage();
        }
    }
}
