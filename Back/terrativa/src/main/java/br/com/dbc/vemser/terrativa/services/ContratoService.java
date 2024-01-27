package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.mappers.ContratoMapper;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@RequiredArgsConstructor
@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;

    public ResponseContratoDTO resgatarContratoPorId(Integer id) {
        Contrato contrato = contratoRepository.resgatarDadosPorId(id);
        return ContratoMapper.ContratoParaResponseContrato(contrato);
    }

    public ResponseContratoDTO criar(RequestContratoCreateDTO contrato) throws Exception {
        return ContratoMapper.ContratoParaResponseContrato(
                contratoRepository.adicionar(
                        ContratoMapper.RequestContratoParaContrato(contrato)));
    }
    public ResponseContratoDTO alterar(RequestContratoCreateDTO contrato)throws Exception {
    return ContratoMapper.ContratoParaResponseContrato(
            contratoRepository.alterar(
                    ContratoMapper.RequestContratoParaContrato(contrato)));
        }

    public void deletar(Integer id) {
        contratoRepository.deletar(id);
    }


}
