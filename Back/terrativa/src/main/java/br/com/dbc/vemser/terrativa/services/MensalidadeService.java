package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.ResponseMensalidadeDTO;
import br.com.dbc.vemser.terrativa.dto.mappers.MensalidadeMapper;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.repository.MensalidadeRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class MensalidadeService {

    private final MensalidadeRepository mensalidadeRepository;
    private final ContratoService contratoService;


    public ResponseMensalidadeDTO alterarMensalidade(Integer id, RequestMensalidadeCreateDTO requestMensalidade) {
        contratoService.resgatarContratoPorId(requestMensalidade.getContratoID());
        Mensalidade mensalidade = MensalidadeMapper.RequestMensalidadeParaMensalidade(requestMensalidade);
        return MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidadeRepository.alterar(mensalidade));
    }

    public void deletarMensalidade(Integer id) {
        mensalidadeRepository.deletar(id);
    }

    public ResponseMensalidadeDTO resgatarMensalidadePorId(Integer id) {
        ResponseMensalidadeDTO responseMensalidade = MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidadeRepository.resgatarDadosPorId(id));
        return responseMensalidade;
    }
}
