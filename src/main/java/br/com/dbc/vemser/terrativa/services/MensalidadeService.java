package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.MensalidadeMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseMensalidadeDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.MensalidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MensalidadeService {

    private final MensalidadeRepository mensalidadeRepository;
    private final ContratoService contratoService;
    private final SessaoUsuarioService sessaoUsuarioService;

    private final String NOT_FOUND_MESSAGE = "Mensalidade não encontrada";
    private final String NOT_FOUND_DONO = "Você não tem acesso a esta mensalidade";



    public void criarMensalidade(RequestMensalidadeCreateDTO requestMensalidade) throws RegraDeNegocioException {
        contratoService.resgatarContratoPorId(requestMensalidade.getContratoID());
        Mensalidade mensalidade = MensalidadeMapper.RequestMensalidadeParaMensalidade(requestMensalidade);
        mensalidadeRepository.save(mensalidade);
    }

    public ResponseMensalidadeDTO alterarMensalidade(Integer id, RequestMensalidadeCreateDTO requestMensalidade) throws RegraDeNegocioException {
        verificaUsuario(id);
        Mensalidade mensalidade = mensalidadeRepository.findById(id).get();
        contratoService.resgatarContratoPorId(mensalidade.getContratoID());
        mensalidade.setAnoExercicio(requestMensalidade.getAnoExercicio());
        mensalidade.setValorMensal(requestMensalidade.getValorMensal());
        return MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidadeRepository.save(mensalidade));
    }

    public ResponseMensalidadeDTO resgatarMensalidadePorId(Integer id) throws RegraDeNegocioException {
        verificaUsuario(id);
        Mensalidade mensalidade = mensalidadeRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));

        return MensalidadeMapper.MensalidadeParaResponseMensalidade(mensalidade);
    }

    public void verificaUsuario(Integer id) throws RegraDeNegocioException {
        Integer idUsuario = sessaoUsuarioService.getIdLoggedUserId();
        Mensalidade mensalidade = mensalidadeRepository.findById(id).get();
        Contrato contrato = contratoService.findByID(mensalidade.getContratoID());
        if(!Objects.equals(contrato.getTerreno().getDono().getUsuarioId(), idUsuario) || !Objects.equals(contrato.getLocatarioID(), idUsuario)){
            throw new RegraDeNegocioException(NOT_FOUND_DONO);
        }
    }

}
