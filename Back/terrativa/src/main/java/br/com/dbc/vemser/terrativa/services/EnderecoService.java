package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Endereco;
import br.com.dbc.vemser.terrativa.repository.EnderecoRepository;
import br.com.dbc.vemser.terrativa.util.validar.ValidarModel;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

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
