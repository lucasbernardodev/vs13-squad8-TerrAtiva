package controllers;

import infra.exceptions.*;
import services.EnderecoTerrenosService;

public class EnderecoTerrenosController {

    private EnderecoTerrenosService enderecoTerrenosService = new EnderecoTerrenosService();

    public String cadastrarEnderecoTerrenos(String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep, String localizacao) {
        try {
            enderecoTerrenosService.adicionarEnderecoTerrenos(logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep, localizacao);
            return "Endereço Cadastrado Com Sucesso!";
        } catch (InvalidParamException | DbException | UnauthorizedOperationException |
                 EntityIdNullException | DataFormatInvalidException e) {
            return e.getMessage();
        }
    }

    public String atualizarEndereco(Integer id,String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep, String localizacao) {
        try {
            enderecoTerrenosService.alterar( id, logradouro,  numero,  complemento,  bairro,  codigoMunicipioIBGE,  cep,  localizacao);
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
            return enderecoTerrenosService.resgatarPorId(id).toString();
        } catch (DataNotFoundException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String deletarEndereco(Integer id) {
        try {
            enderecoTerrenosService.deletar(id);
            return "Endereço deletado com Sucesso";
        } catch (DbException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e){
            return e.getMessage();
        }
    }
}
