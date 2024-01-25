package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.ResponseFeed;
import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.repository.FeedRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;

    private final ObjectMapper objectMapper;


    public ArrayList<ResponseFeed> mostrarTerrenosDisponiveis() throws Exception{
        ArrayList<Feed> feed = feedRepository.buscarTerrenos();
        ArrayList<ResponseFeed> responseFeed = objectMapper.convertValue(feed, new TypeReference<ArrayList<ResponseFeed>>() {});
        return responseFeed;
    }

    public ArrayList<ResponseFeed> mostrarTerrenosPorPreco(String preco) throws Exception {
        feedRepository.filtrarPorValor(preco);
        ArrayList<Feed> feed = feedRepository.buscarTerrenos();
        ArrayList<ResponseFeed> responseFeed = objectMapper.convertValue(feed, new TypeReference<ArrayList<ResponseFeed>>() {});
        return responseFeed;
    }

    public ArrayList<ResponseFeed> mostrarTerrenosPorCaracteristica(String caracteristica) throws Exception {
        feedRepository.filtrarPorCaracteristicas(caracteristica);
        ArrayList<Feed> feed = feedRepository.buscarTerrenos();
        ArrayList<ResponseFeed> responseFeeds = objectMapper.convertValue(feed, new TypeReference<ArrayList<ResponseFeed>>() {});
        return responseFeeds;
    }

    public ArrayList<ResponseFeed> mostrarTerrenosPorLocal(String estado) throws Exception {
        feedRepository.filtrarPorEstado(estado);
        ArrayList<Feed> feed = feedRepository.buscarTerrenos();
        ArrayList<ResponseFeed> responseFeed = objectMapper.convertValue(feed, new TypeReference<ArrayList<ResponseFeed>>() {});
        return responseFeed;
    }

    public ArrayList<ResponseFeed> mostrarTerrenosPorTamanho(String tamanho) {
        feedRepository.filtrarPorTamanho(tamanho);
        ArrayList<Feed> feed = feedRepository.buscarTerrenos();
        ArrayList<ResponseFeed> responseFeed = objectMapper.convertValue(feed, new TypeReference<ArrayList<ResponseFeed>>() {});
        return responseFeed;
    }


    public ArrayList<Feed> buscarEstados() {
        return feedRepository.buscarEstados();
    }



}
