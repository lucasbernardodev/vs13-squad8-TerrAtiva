package br.com.dbc.vemser.terrativa.backup.controllers;


import br.com.dbc.vemser.terrativa.services.EnderecoService;

public class EnderecoController {

    private final EnderecoService enderecoService = new EnderecoService();

    public String cadastrarEndereco(Integer usuarioID, String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep) {

            enderecoService.adicionarEndereco(usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep);
            return "Endereço Cadastrado Com Sucesso!";
    }

    public String atualizarEndereco(Integer enderecoID, Integer usuarioID, String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep) {

            enderecoService.alterar(enderecoID, usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep);
            return "Endereço Atualizado Com Sucesso!";
    }

    public String resgatarEnderecoPorID(Integer id){

            return enderecoService.resgatarPorId(id).toString();
    }

    public String deletarEndereco(Integer id) {

            enderecoService.deletar(id);
            return "Endereço deletado com Sucesso";
    }
}
