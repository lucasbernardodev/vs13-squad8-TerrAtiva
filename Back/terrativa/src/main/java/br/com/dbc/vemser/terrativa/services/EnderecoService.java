package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.RequestEndereco;
import br.com.dbc.vemser.terrativa.dto.ResponseEndereco;
import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoMapper;
import br.com.dbc.vemser.terrativa.entity.Endereco;
import br.com.dbc.vemser.terrativa.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    public List<ResponseEndereco> listarEnderecos() {
        return enderecoRepository.listarEnderecos().stream()
                .map(EnderecoMapper::EnderecoParaResponseEndereco)
                .toList();
    }

    public ResponseEndereco resgatarPorId(Integer id) {
        Endereco endereco = enderecoRepository.resgatarDadosPorId(id);
        return EnderecoMapper.EnderecoParaResponseEndereco(endereco);
    }

    public ResponseEndereco adicionarEndereco(RequestEndereco endereco) throws Exception {
       return EnderecoMapper.EnderecoParaResponseEndereco(
               enderecoRepository.adicionar(
                       EnderecoMapper.RequestEnderecoParaEndereco(endereco)));
            }

    public ResponseEndereco alterar(RequestEndereco endereco) throws Exception {
        return EnderecoMapper.EnderecoParaResponseEndereco(
        enderecoRepository.alterar(
                EnderecoMapper.RequestEnderecoParaEndereco(endereco)));
    }

    public void deletar(Integer id) {
        enderecoRepository.deletar(id);
    }

}