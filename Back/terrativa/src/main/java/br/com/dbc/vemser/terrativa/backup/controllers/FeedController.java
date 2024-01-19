package br.com.dbc.vemser.terrativa.backup.controllers;

import br.com.dbc.vemser.terrativa.models.Feed;
import br.com.dbc.vemser.terrativa.services.FeedService;

import java.util.ArrayList;

public class FeedController {
    private final FeedService feedService = new FeedService();

    public String mostrarTerrenosDisponveis() {
        ArrayList<Feed> response = feedService.mostrarTerrenosDisponiveis();
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;

    }
    public String mostrarTerrenosPorPreco(String valor) {
        ArrayList<Feed> response = feedService.mostrarTerrenosPorPreco(valor);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public String mostrarTerrenosPorCaracteristica(String caracteristica) {
        ArrayList<Feed> response = feedService.mostrarTerrenosPorCaracteristica(caracteristica);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public String mostrarTerrenosPorLocal(String local) {
        ArrayList<Feed> response = feedService.mostrarTerrenosPorLocal(local);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }
    public String buscarEstados() {
        ArrayList<Feed> response = feedService.buscarEstados();
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : Formatador.readerListEstadosFeed(response);
    }

    public String mostrarTerrenosPorTamanho(String tamanho) {
        ArrayList<Feed> response = feedService.mostrarTerrenosPorTamanho(tamanho);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public void limparFiltros() {
        feedService.limparFiltros();
    }

}
