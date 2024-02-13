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
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerrenoService {
   private final TerrenoRepository terrenoRepository;
   private final ContratoService contratoService;
   private final TerrenoMapper terrenoMapper;
   private final UsuarioRepository usuarioRepository;
   private final EnderecoTerrenosService enderecoTerrenosService;
   private final MensalidadeService mensalidadeService;

    private final String NOT_FOUND_MESSAGE_TERRENO = "Terreno não encontrado";
    private final String NOT_FOUND_MESSAGE_TERRENO_EXIST = "Terreno não existe ou está alugado";
    private final String NOT_FOUND_MESSAGE_USUARIO = "Usuário não encontrado";
    private final String NOT_FOUND_MESSAGE_INATIVO = "Usuário inativo";


    public ResponseTerrenoDTO buscarTerreno(Integer idTerreno) throws RegraDeNegocioException {
        Terreno terreno = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO));
        if (terreno.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO_EXIST );
        }
        return terrenoMapper.terrenoParaResponseTerreno(
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
        Terreno terrenoCadastro = terrenoMapper.requestTerrenoParaTerreno(requestTerreno);
        terrenoCadastro.setDono(usuarioRepository.findById(requestTerreno.getProprietarioID()).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO)));
        terrenoCadastro.setEnderecoTerrenoID(enderecoTerrenos);
        return terrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.save(terrenoCadastro), EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenos));
    }

    public ResponseTerrenoDTO alterarTerreno(Integer idTerreno, RequestTerrenoUpdateDTO requestTerreno) throws RegraDeNegocioException {
        Terreno terreno = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO));
        requestTerreno.setId(terreno.getId());
        requestTerreno.setProprietarioID(terreno.getDono().getUsuarioId());
        if (terreno.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO_EXIST);
        }
        requestTerreno.getEndereco().setId(idTerreno);
        EnderecoTerrenos responseEnderecoTerrenos = enderecoTerrenosService.alterar(requestTerreno.getEndereco());
        Terreno terrenoCadastro =  terrenoMapper.requestTerrenoParaTerreno(requestTerreno);
        terrenoCadastro.setDono(usuarioRepository.findById(terreno.getDono().getUsuarioId()).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO)));
        terrenoCadastro.setEnderecoTerrenoID(responseEnderecoTerrenos);
        terrenoCadastro.setEnderecoID(responseEnderecoTerrenos.getId());
        Terreno terrenoRetorno = terrenoRepository.save(terrenoCadastro);

        return terrenoMapper.terrenoParaResponseTerreno(
                terrenoRetorno, EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(responseEnderecoTerrenos));
    }

    public void deletarTerreno(int idTerreno) throws RegraDeNegocioException {
        Integer findUserId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
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

    public void alterarTerrenosUsuarioDeletado(Integer donoID) throws RegraDeNegocioException {
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
        contrato.setTerreno(terreno);
        contrato.setLocatario(usuarioRepository.findById(contrato.getLocatarioID()) .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO)));
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
