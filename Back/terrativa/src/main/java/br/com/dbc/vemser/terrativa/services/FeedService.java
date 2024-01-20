package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.repository.FeedRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FeedService {
    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public ArrayList<Feed> mostrarTerrenosDisponiveis() {

        return feedRepository.buscarTerrenos();
    }

    public ArrayList<Feed> mostrarTerrenosPorCaracteristica(String caracteristica) {
        feedRepository.filtrarPorCaracteristicas(caracteristica);
        return feedRepository.buscarTerrenos();
    }
    public ArrayList<Feed> mostrarTerrenosPorPreco(String preco) {
        feedRepository.filtrarPorValor(preco);
        return feedRepository.buscarTerrenos();
    }
    public ArrayList<Feed> mostrarTerrenosPorTamanho(String tamanho) {
        feedRepository.filtrarPorTamanho(tamanho);
        return feedRepository.buscarTerrenos();
    }
    public ArrayList<Feed> mostrarTerrenosPorLocal(String estado) {
        feedRepository.filtrarPorEstado(estado);
        return feedRepository.buscarTerrenos();
    }

    public ArrayList<Feed> buscarEstados() {
        return feedRepository.buscarEstados();
    }



}
