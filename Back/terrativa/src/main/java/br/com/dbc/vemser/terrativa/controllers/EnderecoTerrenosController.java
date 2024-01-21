package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.services.EnderecoTerrenosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnderecoTerrenosController {

    private final EnderecoTerrenosService enderecoTerrenosService;

    public String cadastrarEnderecoTerrenos(String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep, String localizacao) {

            enderecoTerrenosService.adicionarEnderecoTerrenos(logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep, localizacao);
            return "Endereço Cadastrado Com Sucesso!";
    }

    public String atualizarEndereco(Integer id,String logradouro,
                                    Integer numero, String complemento,
                                    String bairro, Integer codigoMunicipioIBGE,
                                    Integer cep, String localizacao) {

            enderecoTerrenosService.alterar( id, logradouro,  numero,  complemento,  bairro,  codigoMunicipioIBGE,  cep,  localizacao);
            return "Endereço Atualizado Com Sucesso!";

    }

    public String resgatarEnderecoPorID(Integer id){
            return enderecoTerrenosService.resgatarPorId(id).toString();

    }

    public String deletarEndereco(Integer id) {
            enderecoTerrenosService.deletar(id);
            return "Endereço deletado com Sucesso";
    }
}
