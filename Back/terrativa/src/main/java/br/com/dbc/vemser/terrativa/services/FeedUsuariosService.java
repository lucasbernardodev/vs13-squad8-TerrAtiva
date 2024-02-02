package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class FeedUsuariosService {

    private final TerrenoRepository terrenoRepository;

    public List<Terreno> mostrarTerrenosDisponiveis(Integer id) throws Exception{
        return terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("S", id);
    }

    public List<Terreno> mostrarTerrenosDoUsuario(Integer id) throws Exception {

        return terrenoRepository.findAllByProprietarioID(id);
    }

    public List<Terreno> mostrarTerrenosAlugados(Integer id) throws Exception{

        //TODO: verificar na controler os contratos com meu id e buscar os terrenos com o id do contrato
        return terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("N", id);

    }

    public List<Terreno> mostrarTerrenosArrendados(Integer id) throws Exception{

        return terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("N", id);
    }
}
