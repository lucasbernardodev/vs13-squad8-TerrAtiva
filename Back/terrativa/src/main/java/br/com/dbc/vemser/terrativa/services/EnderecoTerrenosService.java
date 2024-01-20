package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.util.validar.ValidarModel;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.repository.EnderecoTerrenosRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoTerrenosService {
    private EnderecoTerrenosRepository enderecoTerrenosRepository = new EnderecoTerrenosRepository();

    public void adicionarEnderecoTerrenos(String logradouro,
                                  Integer numero, String complemento,
                                  String bairro, Integer codigoMunicipioIBGE,
                                  Integer cep, String localizacao) {
        ValidarModel.ENDERECO_TERRENOS(logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep, localizacao);
        enderecoTerrenosRepository.adicionar(new EnderecoTerrenos(logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep, localizacao));
    }

    public void alterar(Integer id,String logradouro,
                        Integer numero, String complemento,
                        String bairro, Integer codigoMunicipioIBGE,
                        Integer cep, String localizacao) {
        ValidarModel.ENDERECO_TERRENOS(logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep, localizacao);
        enderecoTerrenosRepository.alterar(id,
                new EnderecoTerrenos(logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep, localizacao));
    }

    public void deletar(Integer id) {
        enderecoTerrenosRepository.deletar(id);
    }

    public EnderecoTerrenos resgatarPorId(Integer id) {
        return enderecoTerrenosRepository.resgatarDadosPorId(id);
    }
}
