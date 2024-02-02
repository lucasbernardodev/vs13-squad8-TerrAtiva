package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.ContratoMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;

    public ResponseContratoDTO resgatarContratoPorId(Integer id) throws Exception {
        Contrato contrato = contratoRepository.findById(id).get();
        Usuario usuario = usuarioRepository.findById(contrato.getLocatarioID()).get();
        contrato.setUsuarioID(usuario);
        return ContratoMapper.contratoParaResponseContrato(contrato);
    }

    public ResponseContratoDTO createContrato(RequestContratoCreateDTO contratCrate){
        Contrato contrato = ContratoMapper.requestContratoParaContrato(contratCrate);
        Contrato contratSalvo = contratoRepository.save(contrato);
        return ContratoMapper.contratoParaResponseContrato(contrato);
    }

    public ResponseContratoDTO alterar(Integer id, RequestContratoCreateDTO contratoCreate)throws Exception {
        Contrato contrato = findByID(id);
        contrato.setLocatarioID(contratoCreate.getLocatarioID());
        contrato.setDataAssinatura(contratoCreate.getDataAssinatura());
        contrato.setDataInicio(contratoCreate.getDataInicio());
        contrato.setDataFinal(contratoCreate.getDataFinal());
        contrato.setDataVencimentoAluguel(contratoCreate.getDataVencimentoAluguel());
        contrato.setEditado(Instant.now().toString());
        contratoRepository.save(contrato);
        ResponseContratoDTO responseContratoDTO = ContratoMapper.contratoParaResponseContrato(contrato);
        return responseContratoDTO;
    }

    public void deletar(Integer id) {
        Contrato contrato = findByID(id);
        contrato.setAtivo("N");
        contratoRepository.save(contrato);
    }

    public List<Contrato> buscarContratoPorLocatario(Integer id){
        return contratoRepository.findAllByLocatarioID(id);
    }

    private Contrato findByID(Integer id){
        return contratoRepository.findById(id).get();
    }

}
