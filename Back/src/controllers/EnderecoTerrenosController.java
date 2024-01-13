package controllers;

import infra.exceptions.*;
import services.EnderecoService;

public class EnderecoTerrenosController {

    private EnderecoService enderecoService = new EnderecoService();

    public String cadastrarEndereco(Integer enderecoID, Integer usuarioID, String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep) {
        try {
            enderecoService.alterar(enderecoID, usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep);
            return "Endereço Cadastrado Com Sucesso!";
        } catch (InvalidParamException e) {
            return e.getMessage();
        } catch (DataFormatInvalidException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e) {
            return e.getMessage();
        } catch (DbException e) {
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
        } catch (InvalidParamException e) {
            return e.getMessage();
        } catch (DataFormatInvalidException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String resgatarEnderecoPorID(Integer id){
        try {
            return enderecoService.resgatarPorId(id).toString();
        } catch (DataNotFoundException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String deletarEndereco(Integer id) {
        try {
            enderecoService.deletar(id);
            return "Endereço deletado com Sucesso";
        } catch (DbException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e){
            return e.getMessage();
        }
    }
}
