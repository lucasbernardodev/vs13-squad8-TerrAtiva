package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.services.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    public String mostrarTerrenosDisponveis() {
        ArrayList<Feed> response = feedService.mostrarTerrenosDisponiveis();
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response.toString();

    }
    public String mostrarTerrenosPorPreco(String valor) {
        ArrayList<Feed> response = feedService.mostrarTerrenosPorPreco(valor);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response.toString();
    }

    public String mostrarTerrenosPorCaracteristica(String caracteristica) {
        ArrayList<Feed> response = feedService.mostrarTerrenosPorCaracteristica(caracteristica);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response.toString();
    }

    public String mostrarTerrenosPorLocal(String local) {
        ArrayList<Feed> response = feedService.mostrarTerrenosPorLocal(local);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response.toString();
    }
    public String buscarEstados() {
        ArrayList<Feed> response = feedService.buscarEstados();
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response.toString();
    }

    public String mostrarTerrenosPorTamanho(String tamanho) {
        ArrayList<Feed> response = feedService.mostrarTerrenosPorTamanho(tamanho);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response.toString();
    }


}
