package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.FeedUsuarioMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.FeedUsuariosAlugadosMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedUsuarioAlugadosDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedUsuarioDTO;
import br.com.dbc.vemser.terrativa.repository.FeedUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final FeedUsuarioRepository feedUsuarioRepository;
    private final ObjectMapper objectMapper;

    public List<ResponseFeedUsuarioDTO> mostrarTerrenosDisponiveis(Integer id) throws Exception{
        return feedUsuarioRepository.mostrarTerrenosDisponiveis(id).stream().map(FeedUsuarioMapper::feedParaResponseFeed).toList();
    }

    public List<ResponseFeedUsuarioDTO> mostrarTerrenosDoUsuario(Integer id) throws Exception {

        return feedUsuarioRepository.mostrarTerrenosDoUsuario(id).stream().map(FeedUsuarioMapper::feedParaResponseFeed).toList();
    }

    public List<ResponseFeedUsuarioAlugadosDTO> mostrarTerrenosAlugados(Integer id) throws Exception{

        return feedUsuarioRepository.mostrarTerrenosAlugados(id).stream().map(FeedUsuariosAlugadosMapper :: feedUsuariosParaAlugados).toList();

    }

    public List<ResponseFeedUsuarioAlugadosDTO> mostrarTerrenosArrendados(Integer id) throws Exception{

        return feedUsuarioRepository.mostrarTerrenosArrendados(id).stream().map(FeedUsuariosAlugadosMapper :: feedUsuariosParaAlugados).toList();
    }
}
