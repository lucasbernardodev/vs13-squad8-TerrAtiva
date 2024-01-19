package br.com.dbc.vemser.terrativa.backup.controllers;

import br.com.dbc.vemser.terrativa.models.Terreno;
import br.com.dbc.vemser.terrativa.services.FeedUsuarioService;

import java.util.ArrayList;

public class FeedUsuarioController {
    private final FeedUsuarioService feedService = new FeedUsuarioService();

    public String mostrarTerrenosDisponveis(Integer usuarioID) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosDisponiveis(usuarioID);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public String mostrarTerrenosAlugados(Integer usuarioID) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosAlugados(usuarioID);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public String mostrarTerrenosDoUsuario(Integer usuarioID) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosDoUsuario(usuarioID);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public String mostrarTodosTerrenos(Integer usuarioID) {
        ArrayList<Terreno> response = feedService.mostrarTodosTerrenos(usuarioID);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public String mostrarMeusTerrenosArrendados(Integer usuarioID) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosArrendados(usuarioID);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public String mostrarTerrenosPorPrecoMenor(double valor) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosPorPreco(valor);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }

    public String mostrarTerrenosPorTitulo(String titulo, Integer usuarioID) {
        ArrayList<Terreno> response = feedService.mostrarTerrenosPorTitulo(titulo, usuarioID);
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response;
    }


}
