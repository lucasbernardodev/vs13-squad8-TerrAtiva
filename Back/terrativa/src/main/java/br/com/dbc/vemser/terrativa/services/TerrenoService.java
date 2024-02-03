package br.com.dbc.vemser.terrativa.services;


import br.com.dbc.vemser.terrativa.dto.mappers.ContratoMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoUpdateDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
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

    public ResponseTerrenoDTO buscarTerreno(Integer idTerreno) throws RegraDeNegocioException {
        Terreno terreno = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException("Terreno não encontrado"));

        return terrenoMapper.terrenoParaResponseTerreno(
                terreno, enderecoTerrenosService.resgatarPorId(terreno.getEnderecoID()));
    }

    public ResponseTerrenoDTO cadastrarTerreno(RequestTerrenoCreateDTO requestTerreno) throws Exception {
        requestTerreno.setId(null);
        requestTerreno.setDisponivel("S");
        EnderecoTerrenos enderecoTerrenos = enderecoTerrenosService.adicionarEnderecoTerrenos(requestTerreno.getEndereco());
        requestTerreno.setEnderecoID(enderecoTerrenos.getId());
        Terreno terrenoCadastro = terrenoMapper.requestTerrenoParaTerreno(requestTerreno);
        terrenoCadastro.setDono(usuarioRepository.findById(requestTerreno.getProprietarioID()).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado")));
        terrenoCadastro.setEnderecoTerrenoID(enderecoTerrenos);
        return terrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.save(terrenoCadastro), EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenos));
    }

    public ResponseTerrenoDTO alterarTerreno(Integer idTerreno, RequestTerrenoUpdateDTO requestTerreno) throws RegraDeNegocioException {
        requestTerreno.setId(idTerreno);
        requestTerreno.getEndereco().setId(idTerreno);
        EnderecoTerrenos responseEnderecoTerrenos = enderecoTerrenosService.alterar(requestTerreno.getEndereco());
        Terreno terrenoCadastro =  terrenoMapper.requestTerrenoParaTerreno(requestTerreno);
        terrenoCadastro.setDono(usuarioRepository.findById(requestTerreno.getProprietarioID()).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado")));
        terrenoCadastro.setEnderecoTerrenoID(responseEnderecoTerrenos);
        terrenoCadastro.setEnderecoID(responseEnderecoTerrenos.getId());
        Terreno terreno = terrenoRepository.save(terrenoCadastro);

        return terrenoMapper.terrenoParaResponseTerreno(
                terreno, EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(responseEnderecoTerrenos));
    }

    public void deletarTerreno(int idTerreno) throws RegraDeNegocioException {
        Terreno terrenoRecuperado = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException("Terreno não encontrado"));
        if (terrenoRecuperado.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException("Terreno não existe ou está alugado");
        }
        terrenoRecuperado.setDisponivel("N");
        terrenoRepository.save(terrenoRecuperado);
    }

    public void alterarTerrenosUsuarioDeletado(Integer donoID) throws RegraDeNegocioException {
            List<Terreno> listaTerrenos = terrenoRepository.findAllByProprietarioID(donoID);
            if (listaTerrenos.isEmpty()){
                throw new RegraDeNegocioException("Usuário não possui terrenos");
            }
            for (Terreno terreno: listaTerrenos){
                terreno.setDisponivel("N");
                terrenoRepository.save(terreno);
            }
    }

    public void arrendarTerreno(Integer idTerreno, RequestContratoCreateDTO contrato) throws Exception {
        contrato.setTerrenoID(idTerreno);
        Terreno terreno = terrenoRepository.findById(idTerreno).orElseThrow(() -> new RegraDeNegocioException("Terreno indisponível para aluguel"));
        contrato.setTerreno(terreno);
        contrato.setLocatario(usuarioRepository.findById(contrato.getLocatarioID()).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado")));
        if (terreno.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException("Terreno indisponível para aluguel");
        }
        ResponseContratoDTO contratoResponse = contratoService.createContrato(contrato);
        RequestMensalidadeCreateDTO mensalidade = contrato.getMensalidade();
        mensalidade.setContratoID(contratoResponse.getId());
        mensalidadeService.criarMensalidade(contrato.getMensalidade());
        terreno.setDisponivel("N");
        terrenoRepository.save(terreno);

    }

}
