package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
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
@Data
@Slf4j
public class FeedUsuariosService {

    private final TerrenoRepository terrenoRepository;
    private final ContratoRepository contratoRepository;
    private final TerrenoMapper terrenoMapper;

    public List<Terreno> mostrarTerrenosDisponiveis(Integer id) throws Exception{
        return terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("S", id);
    }

    public List<Terreno> mostrarTerrenosDoUsuario(Integer id) throws Exception {

        return terrenoRepository.findAllByProprietarioID(id);
    }

    public List<Terreno> mostrarTerrenosAlugados(Integer id) throws Exception{

        List<Contrato> contrato = contratoRepository.findAllByLocatarioID(id);
        List<Terreno> terrenos = new ArrayList<>();
        for (Contrato c : contrato) {
            terrenos.add(terrenoRepository.findById(
                    c.getTerrenoID()).orElseThrow(() -> new RegraDeNegocioException(
                            "Ocorreu um erro interno, tente novamente mais tarde.")));
        }
        return terrenos.stream().filter(terreno -> terreno.getDisponivel().equals("N")).toList();

    }

    public List<Terreno> mostrarTerrenosArrendados(Integer id) throws Exception{

        return terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("N", id);
    }
}
