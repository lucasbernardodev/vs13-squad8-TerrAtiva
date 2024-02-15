package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.ContratoMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SessaoUsuarioService sessaoUsuarioService;

    private final String NOT_FOUND_MESSAGE = "Usuário não encontrado";
    private final String NOT_FOUND_CONTRACT = "Contrato já encerrado";
    private final String NOT_FOUND_CONTRACT_NULL = "Contrato não encontrado";

    private final String NOT_FOUND_DONO = "Você não tem acesso a este contrato.";

    public ResponseContratoRelatorioDTO resgatarContratoPorId(Integer id) throws RegraDeNegocioException {
        verificaUsuario(id);
        Contrato contrato = contratoRepository.retornaContratoPorID(id);
        Usuario usuario = usuarioRepository.findById(contrato.getLocatarioID()).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
        contrato.setLocatario(usuario);
        return ContratoMapper.responseContratoRelatorioDTO(contrato);
    }

    public ResponseContratoDTO createContrato(RequestContratoCreateDTO contratoCreate) {
        Contrato contrato = ContratoMapper.requestContratoParaContrato(contratoCreate);

        Contrato contratSalvo = contratoRepository.save(contrato);
        return ContratoMapper.contratoParaResponseContrato(contratSalvo);
    }

    public void deletar(Integer id) throws RegraDeNegocioException {
        verificaUsuario(id);
        Contrato contrato = findByID(id);
        if (contrato.getAtivo().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_CONTRACT);
        }
        contrato.setAtivo("N");
        contratoRepository.save(contrato);
    }

    public List<Contrato> buscarContratoPorLocatario(Integer id){
        return contratoRepository.findAllByLocatarioID(id);
    }

    public Contrato findByID(Integer id) throws RegraDeNegocioException {
        return contratoRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_CONTRACT_NULL));
    }

    private String verificaUsuario(Integer id) throws RegraDeNegocioException {
        Integer idUsuario = sessaoUsuarioService.getIdLoggedUserId();
        Contrato contrato = contratoRepository.findById(id).get();
        if(Objects.equals(contrato.getTerreno().getDono().getUsuarioId(), idUsuario) || Objects.equals(contrato.getLocatarioID(), idUsuario)){
            return null;
        } else {
            throw new RegraDeNegocioException(NOT_FOUND_DONO);
        }
    }

}
