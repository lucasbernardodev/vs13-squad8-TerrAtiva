package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.RequestContrato;
import br.com.dbc.vemser.terrativa.dto.ResponseContrato;
import br.com.dbc.vemser.terrativa.dto.mappers.ContratoMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoMapper;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
@Slf4j
@RequiredArgsConstructor
@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;

    public ResponseContrato resgatarContratoPorId(Integer id) {
        Contrato contrato = contratoRepository.resgatarDadosPorId(id);
        return ContratoMapper.ContratoParaResponseContrato(contrato);
    }
    public ResponseContrato alterar(RequestContrato contrato)throws Exception {
    return ContratoMapper.ContratoParaResponseContrato(
            contratoRepository.alterar(
                    ContratoMapper.RequestContratoParaContrato(contrato)));
        }

    public void deletar(Integer id) {
        contratoRepository.deletar(id);
    }


}
