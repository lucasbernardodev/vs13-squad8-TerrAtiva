package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.entity.Endereco;
import br.com.dbc.vemser.terrativa.entity.Usuario;
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
        Endereco endereco = enderecoRepository.findEnderecoByUsuarioID(id);
        return EnderecoMapper.EnderecoParaResponseEndereco(endereco);
    }

    public ResponseEnderecoDTO adicionarEndereco(RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        Endereco enderecoNovo = EnderecoMapper.RequestEnderecoParaEndereco(endereco);
        enderecoNovo.setCodIBGE(estadoMunicipioService.buscarCodIBGE(endereco.getCodigoMunicipioIBGE()));
       return EnderecoMapper.EnderecoParaResponseEndereco(
               enderecoRepository.save(enderecoNovo));
    }

    public ResponseEnderecoDTO alterar(Usuario usuario, RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        Endereco enderecoRecuperado = EnderecoMapper.RequestEnderecoParaEndereco(endereco);
        enderecoRecuperado.setId(usuario.getUsuarioId());
        enderecoRecuperado.setUsuarioID(usuario.getUsuarioId());
        enderecoRecuperado.setUsuario(usuario);
        enderecoRecuperado.setCodIBGE(estadoMunicipioService.buscarCodIBGE(endereco.getCodigoMunicipioIBGE()));
        return EnderecoMapper.EnderecoParaResponseEndereco(
        enderecoRepository.save(
                enderecoRecuperado));
    }

}