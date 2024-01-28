package br.com.dbc.vemser.terrativa.services;


import br.com.dbc.vemser.terrativa.dto.mappers.ContratoMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TerrenoService {
   private final TerrenoRepository terrenoRepository;
   private final ContratoService contratoService;

    public ResponseTerrenoDTO buscarTerreno(Integer idTerreno) {
        return TerrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.resgatarDadosPorId(idTerreno));
    }

    public ResponseTerrenoDTO cadastrarTerreno(RequestTerrenoCreateDTO requestTerreno) {
        return TerrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.adicionar(
                        TerrenoMapper.requestTerrenoParaTerreno(requestTerreno)));
    }

    public ResponseTerrenoDTO alterarTerreno(RequestTerrenoCreateDTO requestTerreno) {
        return TerrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.alterar(
                        TerrenoMapper.requestTerrenoParaTerreno(requestTerreno)));
    }


    public void deletarTerreno(int idTerreno) {
        terrenoRepository.deletar(idTerreno);
    }

    public void arrendarTerreno(Integer idTerreno, RequestContratoCreateDTO contrato) throws Exception{

        contrato.setTerrenoID(idTerreno);
        Terreno terreno = terrenoRepository.resgatarDadosPorId(idTerreno);
        if (terreno.getDisponivel().equals("N")) {
            throw new RegraDeNegocioException("Terreno já está alugado");
        }
        contrato.setId(contratoService.getNextId());

        terrenoRepository.arrendarTerreno(ContratoMapper.requestContratoParaContrato(contrato), terreno);
    }

    public void cancelarContratoTerreno(Integer usuarioID, Integer contratoID) {
        terrenoRepository.cancelarContratoTerreno(usuarioID, contratoID);
    }
}
