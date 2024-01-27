package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedUsuarioAlugadosDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedUsuarioDTO;
import br.com.dbc.vemser.terrativa.entity.FeedUsuario;
import br.com.dbc.vemser.terrativa.entity.FeedUsuariosAlugados;
import br.com.dbc.vemser.terrativa.repository.FeedUsuarioRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class FeedUsuariosService {

    private final FeedUsuarioRepository feedUsuarioRepository;
    private final ObjectMapper objectMapper;

    public ArrayList<ResponseFeedUsuarioDTO> mostrarTerrenosDisponiveis(Integer id) throws Exception{
        ArrayList<FeedUsuario> feedUsuario = feedUsuarioRepository.mostrarTerrenosDisponiveis(id);
        ArrayList<ResponseFeedUsuarioDTO> responseFeedUsuarioDTO = objectMapper.convertValue(feedUsuario, new TypeReference<ArrayList<ResponseFeedUsuarioDTO>>() {});
        return responseFeedUsuarioDTO;
    }


    public ArrayList<ResponseFeedUsuarioDTO> mostrarTerrenosDoUsuario(Integer id) throws Exception {
        ArrayList<FeedUsuario> feedUsuario = feedUsuarioRepository.mostrarTerrenosDoUsuario(id);
        ArrayList<ResponseFeedUsuarioDTO> responseFeedUsuarioDTOS = objectMapper.convertValue(feedUsuario, new TypeReference<ArrayList<ResponseFeedUsuarioDTO>>() {});
        return responseFeedUsuarioDTOS;
    }


    public ArrayList<ResponseFeedUsuarioAlugadosDTO> mostrarTerrenosAlugados(Integer id) throws Exception{
        ArrayList<FeedUsuariosAlugados> feedUsuariosAlugados = feedUsuarioRepository.mostrarTerrenosAlugados(id);
        ArrayList<ResponseFeedUsuarioAlugadosDTO> responseFeedUsuarioAlugadosDTOS = objectMapper.convertValue(feedUsuariosAlugados, new TypeReference<ArrayList<ResponseFeedUsuarioAlugadosDTO>>() {});
        return responseFeedUsuarioAlugadosDTOS;
    }

    public ArrayList<ResponseFeedUsuarioAlugadosDTO> mostrarTerrenosArrendados(Integer id) throws Exception{
        ArrayList<FeedUsuariosAlugados> feedUsuariosAlugados = feedUsuarioRepository.mostrarTerrenosArrendados(id);
        ArrayList<ResponseFeedUsuarioAlugadosDTO> responseFeedUsuarioAlugadosDTOS = objectMapper.convertValue(feedUsuariosAlugados, new TypeReference<ArrayList<ResponseFeedUsuarioAlugadosDTO>>() {});
        return responseFeedUsuarioAlugadosDTOS;
    }
}
