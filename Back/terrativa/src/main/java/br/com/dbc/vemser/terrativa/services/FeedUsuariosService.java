package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedUsuariosService {

    private final TerrenoRepository terrenoRepository;
    private final ContratoRepository contratoRepository;
    private final TerrenoMapper terrenoMapper;
    private final EnderecoTerrenosService enderecoTerrenosService;

    private final String NOT_FOUND_MESSAGE = "Ocorreu um erro interno, tente novamente mais tarde.";


    List<ResponseEnderecoTerrenosDTO> enderecos = new ArrayList<>();

    public List<ResponseTerrenoDTO> mostrarTerrenosDisponiveis(Integer id) {
        List<Terreno> terrenos = terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("S", id);
        for (Terreno t : terrenos) {
            enderecos.add(enderecoTerrenosService.resgatarPorId(t.getEnderecoID()));
        }
        List<ResponseTerrenoDTO> responseTerreno = new ArrayList<>();
        for (int i = 0; i < terrenos.size(); i++) {
            responseTerreno.add(terrenoMapper.terrenoParaResponseTerreno(terrenos.get(i), enderecos.get(i)));
        }
        return  responseTerreno;
    }

    public List<ResponseTerrenoDTO> mostrarTerrenosDoUsuario(Integer id) {
        List<Terreno> terrenos = terrenoRepository.findAllByProprietarioID(id);
        for (Terreno t : terrenos) {
            enderecos.add(enderecoTerrenosService.resgatarPorId(t.getEnderecoID()));
        }
        List<ResponseTerrenoDTO> responseTerreno = new ArrayList<>();
        for (int i = 0; i < terrenos.size(); i++) {
            responseTerreno.add(terrenoMapper.terrenoParaResponseTerreno(terrenos.get(i), enderecos.get(i)));
        }
        return responseTerreno;
    }

    public List<ResponseTerrenoDTO> mostrarTerrenosAlugados(Integer id) throws RegraDeNegocioException{

        List<Contrato> contrato = contratoRepository.findAllByLocatarioID(id);
        List<Terreno> terrenos = new ArrayList<>();
        for (Contrato c : contrato) {
            Terreno terreno = terrenoRepository.findById(
                    c.getTerrenoID()).orElseThrow(() -> new RegraDeNegocioException(
                    NOT_FOUND_MESSAGE));
            terrenos.add(terreno);
            enderecos.add(enderecoTerrenosService.resgatarPorId(terreno.getEnderecoID()));
        }
        List<ResponseTerrenoDTO> responseTerreno = new ArrayList<>();
        for (int i = 0; i < terrenos.size(); i++) {
            responseTerreno.add(terrenoMapper.terrenoParaResponseTerreno(terrenos.get(i), enderecos.get(i)));
        }

        return responseTerreno;
    }

    public List<ResponseTerrenoDTO> mostrarTerrenosArrendados(Integer id) {
        List<Terreno> terrenos = terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("N", id);
        for (Terreno t : terrenos) {
            enderecos.add(enderecoTerrenosService.resgatarPorId(t.getEnderecoID()));
        }
        List<ResponseTerrenoDTO> responseTerreno = new ArrayList<>();
        for (int i = 0; i < terrenos.size(); i++) {
            responseTerreno.add(terrenoMapper.terrenoParaResponseTerreno(terrenos.get(i), enderecos.get(i)));
        }
        return responseTerreno;
    }
}
