package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.MensalidadeMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseMensalidadeDTO;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.MensalidadeRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class MensalidadeService {

    private final MensalidadeRepository mensalidadeRepository;
    private final ContratoService contratoService;

    private final String NOT_FOUND_MESSAGE = "Mensalidade não encontrada";


    public void criarMensalidade(RequestMensalidadeCreateDTO requestMensalidade) throws RegraDeNegocioException {
        contratoService.resgatarContratoPorId(requestMensalidade.getContratoID());
        Mensalidade mensalidade = MensalidadeMapper.RequestMensalidadeParaMensalidade(requestMensalidade);
        MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidadeRepository.save(mensalidade));
    }

    public ResponseMensalidadeDTO alterarMensalidade(Integer id, RequestMensalidadeCreateDTO requestMensalidade) throws RegraDeNegocioException {
        requestMensalidade.setMensalidadeID(id);
        contratoService.resgatarContratoPorId(requestMensalidade.getContratoID());
        Mensalidade mensalidade = MensalidadeMapper.RequestMensalidadeParaMensalidade(requestMensalidade);
        return MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidadeRepository.save(mensalidade));
    }

    public ResponseMensalidadeDTO resgatarMensalidadePorId(Integer id) throws RegraDeNegocioException {
        Mensalidade mensalidade = mensalidadeRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));

        return MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidade);
    }

}
