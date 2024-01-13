package services;

import models.EnderecoTerrenos;
import repository.EnderecoTerrenosRepository;
import util.validar.ValidarModel;

public class EnderecoTerrenosService {
    private EnderecoTerrenosRepository enderecoTerrenosRepository = new EnderecoTerrenosRepository();

    public void adicionarEnderecoTerrenos(Integer usuarioID, String logradouro,
                                  Integer numero, String complemento,
                                  String bairro, Integer codigoMunicipioIBGE,
                                  Integer cep) {
        ValidarModel.ENDERECO(usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep);
        enderecoTerrenosRepository.adicionar(new EnderecoTerrenos(usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep));
    }

    public void alterar(String logradouro,
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