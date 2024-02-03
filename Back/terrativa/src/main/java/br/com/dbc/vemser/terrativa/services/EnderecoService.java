package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.entity.Endereco;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final EstadoMunicipiosService estadoMunicipioService;

    public ResponseEnderecoDTO resgatarPorId(Integer id) {
        Endereco endereco = enderecoRepository.findById(id).get();
        return EnderecoMapper.EnderecoParaResponseEndereco(endereco);
    }


    public ResponseEnderecoDTO adicionarEndereco(RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        Endereco enderecoNovo = EnderecoMapper.RequestEnderecoParaEndereco(endereco);
        enderecoNovo.setCodIBGE(estadoMunicipioService.buscarCodIBGE(endereco.getCodigoMunicipioIBGE()));
       return EnderecoMapper.EnderecoParaResponseEndereco(
               enderecoRepository.save(enderecoNovo));
    }

    public ResponseEnderecoDTO alterar(Integer id, RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        Endereco enderecoRecuperado = EnderecoMapper.RequestEnderecoParaEndereco(endereco);
        enderecoRecuperado.setId(id);
        enderecoRecuperado.setUsuarioID(id);
        enderecoRecuperado.setCodIBGE(estadoMunicipioService.buscarCodIBGE(endereco.getCodigoMunicipioIBGE()));
        return EnderecoMapper.EnderecoParaResponseEndereco(
        enderecoRepository.save(
                enderecoRecuperado));
    }

}