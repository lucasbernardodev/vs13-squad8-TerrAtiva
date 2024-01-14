package services;

import models.Endereco;
import repository.EnderecoRepository;
import util.validar.ValidarModel;

public class EnderecoService {
    private EnderecoRepository enderecoRepository = new EnderecoRepository();

    public void adicionarEndereco(Integer usuarioID, String logradouro,
                                  Integer numero, String complemento,
                                  String bairro, Integer codigoMunicipioIBGE,
                                  Integer cep) {
        ValidarModel.ENDERECOS(usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep);
        enderecoRepository.adicionar(new Endereco(usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep));
    }

    public void alterar(Integer id, Integer usuarioID, String logradouro,
                        Integer numero, String complemento,
                        String bairro, Integer codigoMunicipioIBGE,
                        Integer cep) {
        ValidarModel.ENDERECOS(usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep);
        enderecoRepository.alterar(id,
                new Endereco(usuarioID, logradouro, numero, complemento, bairro, codigoMunicipioIBGE, cep));
    }

    public void deletar(Integer id) {
        enderecoRepository.deletar(id);
    }

    public Endereco resgatarPorId(Integer id) {
        return enderecoRepository.resgatarDadosPorId(id);
    }
}
