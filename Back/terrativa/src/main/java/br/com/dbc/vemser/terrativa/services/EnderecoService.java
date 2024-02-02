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
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public ResponseEnderecoDTO resgatarPorId(Integer id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        Endereco endereco = enderecoOptional.orElse(null);
        return EnderecoMapper.EnderecoParaResponseEndereco(endereco);
    }


    public ResponseEnderecoDTO adicionarEndereco(RequestEnderecoCreateDTO endereco) {
       return EnderecoMapper.EnderecoParaResponseEndereco(
               enderecoRepository.save(
                       EnderecoMapper.RequestEnderecoParaEndereco(endereco)));
            }

    public ResponseEnderecoDTO alterar(RequestEnderecoCreateDTO endereco) throws Exception {
        return EnderecoMapper.EnderecoParaResponseEndereco(
        enderecoRepository.save(
                EnderecoMapper.RequestEnderecoParaEndereco(endereco)));
    }

    public void deletar(Integer id) {
        enderecoRepository.deleteById(id);
    }

}