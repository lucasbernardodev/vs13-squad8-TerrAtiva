package controllers;

import models.Terreno;
import services.FeedService;
import util.Formatador;

import java.util.ArrayList;

public class FeedController {
    private final FeedService feedService = new FeedService();

    public String mostrarTerrenosDisponveis() {
        ArrayList<Terreno> response = feedService.mostrarTerrenosDisponiveis();
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : Formatador.readerListTerrenos(response);

    }

    public String mostrarTerrenosPorPrecoMenor(double valor) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosPorPreco(valor);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : Formatador.readerListTerrenos(response);
    }

    public String mostrarTerrenosPorTitulo(String titulo) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosPorTitulo(titulo);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : Formatador.readerListTerrenos(response);
    }

    public String mostrarTerrenosPorLocatario(String nomeLocatario) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosPorLocatario(nomeLocatario);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : Formatador.readerListTerrenos(response);
    }

    public String mostrarTerrenosPorTamanho(String tamanho) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosPorTamanho(tamanho);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : Formatador.readerListTerrenos(response);
    }
}
