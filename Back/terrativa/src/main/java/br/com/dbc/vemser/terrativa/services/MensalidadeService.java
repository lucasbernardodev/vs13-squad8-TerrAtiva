package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.RequestMensalidade;
import br.com.dbc.vemser.terrativa.dto.ResponseMensalidade;
import br.com.dbc.vemser.terrativa.dto.mappers.MensalidadeMapper;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.repository.MensalidadeRepository;
import br.com.dbc.vemser.terrativa.util.validar.ValidarModel;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;

@Service
@Data
public class MensalidadeService {

    private final MensalidadeRepository mensalidadeRepository;
    private final ContratoService contratoService;


    public ResponseMensalidade alterarMensalidade(Integer id, RequestMensalidade requestMensalidade) {
        contratoService.resgatarContratoPorId(requestMensalidade.getContratoID());
        Mensalidade mensalidade = MensalidadeMapper.RequestMensalidadeParaMensalidade(requestMensalidade);
        return MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidadeRepository.alterar(mensalidade));
    }

    public void deletarMensalidade(Integer id) {
        mensalidadeRepository.deletar(id);
    }

    public ResponseMensalidade resgatarMensalidadePorId(Integer id) {
        ResponseMensalidade responseMensalidade = MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidadeRepository.resgatarDadosPorId(id));
        return responseMensalidade;
    }
}
