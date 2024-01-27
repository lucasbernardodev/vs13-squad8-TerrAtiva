package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.entity.Endereco;
import br.com.dbc.vemser.terrativa.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    public List<ResponseEnderecoDTO> listarEnderecos() {
        return enderecoRepository.listarEnderecos().stream()
                .map(EnderecoMapper::EnderecoParaResponseEndereco)
                .toList();
    }

    public ResponseEnderecoDTO resgatarPorId(Integer id) {
        Endereco endereco = enderecoRepository.resgatarDadosPorId(id);
        return EnderecoMapper.EnderecoParaResponseEndereco(endereco);
    }

    public ResponseEnderecoDTO adicionarEndereco(RequestEnderecoCreateDTO endereco) throws Exception {
       return EnderecoMapper.EnderecoParaResponseEndereco(
               enderecoRepository.adicionar(
                       EnderecoMapper.RequestEnderecoParaEndereco(endereco)));
            }

    public ResponseEnderecoDTO alterar(RequestEnderecoCreateDTO endereco) throws Exception {
        return EnderecoMapper.EnderecoParaResponseEndereco(
        enderecoRepository.alterar(
                EnderecoMapper.RequestEnderecoParaEndereco(endereco)));
    }

    public void deletar(Integer id) {
        enderecoRepository.deletar(id);
    }

}