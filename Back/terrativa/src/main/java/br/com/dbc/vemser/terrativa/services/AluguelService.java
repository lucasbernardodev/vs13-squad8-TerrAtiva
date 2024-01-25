package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.RequestAluguelCreateDTO;
import br.com.dbc.vemser.terrativa.dto.ResponseAluguelDTO;
import br.com.dbc.vemser.terrativa.dto.mappers.AluguelMapper;
import br.com.dbc.vemser.terrativa.entity.Aluguel;
import br.com.dbc.vemser.terrativa.repository.AluguelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AluguelService {
    private final AluguelRepository aluguelRepository;
    public ResponseAluguelDTO resgatarAluguelPorId(Integer id) throws Exception {
        Aluguel aluguel = aluguelRepository.resgatarDadosPorId(id);
        return AluguelMapper.AluguelParaResponseAluguel(aluguel);
    }
    public ResponseAluguelDTO alterar(RequestAluguelCreateDTO aluguel) throws SQLException {
        return AluguelMapper.AluguelParaResponseAluguel(
                aluguelRepository.alterar(
                        AluguelMapper.RequestAluguelParaAluguel(aluguel)));

    }

    public void deletar(Integer id) throws SQLException {
        aluguelRepository.deletar(id);
    }


    public ResponseAluguelDTO criar(RequestAluguelCreateDTO aluguel) {
        return AluguelMapper.AluguelParaResponseAluguel(
                aluguelRepository.adicionar(
                        AluguelMapper.RequestAluguelParaAluguel(aluguel)));
    }
}







