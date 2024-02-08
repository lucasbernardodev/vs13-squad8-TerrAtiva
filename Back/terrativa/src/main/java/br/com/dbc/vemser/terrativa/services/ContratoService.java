package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.ContratoMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;

    private final String NOT_FOUND_MESSAGE = "Usuário não encontrado";
    private final String NOT_FOUND_CONTRACT = "Contrato já encerrado";
    private final String NOT_FOUND_CONTRACT_NULL = "Contrato não encontrado";

    public ResponseContratoRelatorioDTO resgatarContratoPorId(Integer id) throws RegraDeNegocioException {
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

    private Contrato findByID(Integer id) throws RegraDeNegocioException {
        return contratoRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_CONTRACT_NULL));
    }

}
