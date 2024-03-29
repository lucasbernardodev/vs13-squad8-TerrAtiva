package br.com.dbc.vemser.terrativa.services;


import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoUpdateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.*;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TerrenoService {
   private final TerrenoRepository terrenoRepository;
   private final ContratoService contratoService;
   private final UsuarioRepository usuarioRepository;
   private final EnderecoTerrenosService enderecoTerrenosService;
   private final MensalidadeService mensalidadeService;
   private final SessaoUsuarioService sessaoUsuarioService;
   private final LogService logService;

    private final String NOT_FOUND_MESSAGE_TERRENO = "Terreno não encontrado";
    private final String NOT_FOUND_MESSAGE_TERRENO_EXIST = "Terreno não existe ou está alugado";
    private final String NOT_FOUND_MESSAGE_USUARIO = "Usuário não encontrado";
    private final String NOT_FOUND_MESSAGE_INATIVO = "Usuário inativo";


    public ResponseTerrenoDTO buscarTerreno(Integer idTerreno) throws RegraDeNegocioException {
        Log log = new Log();

        Terreno terreno = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO));
        if (terreno.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO_EXIST );
        }
        log.setTipoLog(TipoLog.USER);
        log.setDescricao("Busca de terreno realizada com sucesso");
        log.setData(LocalDate.now().toString());

        logService.save(log);

        return TerrenoMapper.terrenoParaResponseTerreno(
                terreno, enderecoTerrenosService.resgatarPorId(terreno.getEnderecoID()));
    }


    public ResponseTerrenoDTO cadastrarTerreno(RequestTerrenoCreateDTO requestTerreno) throws RegraDeNegocioException {
        requestTerreno.setId(null);
        requestTerreno.setDisponivel("S");
        Usuario usuario = usuarioRepository.findById(requestTerreno.getProprietarioID()).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO));
        if (usuario.getAtivo().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_INATIVO);
        }
        EnderecoTerrenos enderecoTerrenos = enderecoTerrenosService.adicionarEnderecoTerrenos(requestTerreno.getEndereco());
        requestTerreno.setEnderecoID(enderecoTerrenos.getId());
        Terreno terrenoCadastro = TerrenoMapper.requestTerrenoParaTerreno(requestTerreno);
        terrenoCadastro.setDono(usuarioRepository.findById(requestTerreno.getProprietarioID()).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO)));
        terrenoCadastro.setEnderecoTerrenoID(enderecoTerrenos);
        return TerrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.save(terrenoCadastro), EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenos));
    }

    public ResponseTerrenoDTO alterarTerreno(Integer idTerreno, RequestTerrenoUpdateDTO requestTerreno) throws RegraDeNegocioException {
        Terreno terreno = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO));
        requestTerreno.setId(terreno.getId());
        requestTerreno.setProprietarioID(terreno.getDono().getUsuarioId());
        Integer usuarioId = sessaoUsuarioService.getIdLoggedUserId();
        if (!terreno.getDono().getUsuarioId().equals(usuarioId)) {
            throw new RegraDeNegocioException("Usuário não é o proprietário do terreno");
        }
        if (terreno.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO_EXIST);
        }
        requestTerreno.getEndereco().setId(idTerreno);
        EnderecoTerrenos responseEnderecoTerrenos = enderecoTerrenosService.alterar(requestTerreno.getEndereco());
        Terreno terrenoCadastro =  TerrenoMapper.requestTerrenoParaTerreno(requestTerreno);
        terrenoCadastro.setDono(usuarioRepository.findById(terreno.getDono().getUsuarioId()).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO)));
        terrenoCadastro.setEnderecoTerrenoID(responseEnderecoTerrenos);
        terrenoCadastro.setEnderecoID(responseEnderecoTerrenos.getId());
        Terreno terrenoRetorno = terrenoRepository.save(terrenoCadastro);

        return TerrenoMapper.terrenoParaResponseTerreno(
                terrenoRetorno, EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(responseEnderecoTerrenos));
    }


    public void deletarTerreno(int idTerreno) throws RegraDeNegocioException {
        Integer findUserId = sessaoUsuarioService.getIdLoggedUserId();
        Terreno terrenoRecuperado = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO));
        if (terrenoRecuperado.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO_EXIST);
        }
        if (!terrenoRecuperado.getDono().getUsuarioId().equals(findUserId)) {
            throw new RegraDeNegocioException("Usuário não é o proprietário do terreno");
        }
        terrenoRecuperado.setDisponivel("N");
        terrenoRepository.save(terrenoRecuperado);
    }

    public void alterarTerrenosUsuarioDeletado(Integer donoID) {
        List<Terreno> listaTerrenos = terrenoRepository.findAllByProprietarioID(donoID);
        if (listaTerrenos.isEmpty()){
            return;
        }
        for (Terreno terreno: listaTerrenos){
            terreno.setDisponivel("N");
            terrenoRepository.save(terreno);
        }
    }

    public ResponseContratoRelatorioDTO arrendarTerreno(Integer idTerreno, RequestContratoCreateDTO contrato) throws RegraDeNegocioException {
        contrato.setTerrenoID(idTerreno);
        Terreno terreno = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO_EXIST));
        Integer usuarioId = sessaoUsuarioService.getIdLoggedUserId();
        if (terreno.getDono() != null && terreno.getDono().getUsuarioId().equals(usuarioId)) {
            throw new RegraDeNegocioException("Usuário não pode alugar seu próprio terreno");
        }
        contrato.setTerreno(terreno);
        contrato.setLocatario(usuarioRepository.findById(usuarioId).orElseThrow(() -> new RegraDeNegocioException (NOT_FOUND_MESSAGE_USUARIO)));
        if (contrato.getLocatario().getAtivo().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_INATIVO);
        }
        if (terreno.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO_EXIST);
        }
        ResponseContratoDTO contratoResponse = contratoService.createContrato(contrato);
        RequestMensalidadeCreateDTO mensalidade = contrato.getMensalidade();
        mensalidade.setContratoID(contratoResponse.getId());
        mensalidadeService.criarMensalidade(contrato.getMensalidade());
        terreno.setDisponivel("N");
        terrenoRepository.save(terreno);
        return contratoService.resgatarContratoPorId(contratoResponse.getId());
    }

}
