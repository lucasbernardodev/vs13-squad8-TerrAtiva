package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.repository.FeedRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FeedUsuarioService {
    private final FeedRepository feedRepository;

    public FeedUsuarioService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public ArrayList<Terreno> mostrarTerrenosDisponiveis(Integer usuarioID) {
        return feedRepository.mostrarTerrenosDisponiveis(usuarioID);
    }

    public ArrayList<Terreno> mostrarTerrenosAlugados(Integer usuarioID) {
        return feedRepository.mostrarTerrenosAlugados(usuarioID);
    }

    public ArrayList<Terreno> mostrarTerrenosDoUsuario(Integer usuarioID) {
        return feedRepository.mostrarTerrenosDoUsuario(usuarioID);
    }

    public ArrayList<Terreno> mostrarTodosTerrenos(Integer usuarioID) {
        return feedRepository.mostrarTodosOsTerrenos(usuarioID);
    }

    public ArrayList<Terreno> mostrarTerrenosArrendados(Integer usuarioID) {
        return feedRepository.mostrarTerrenosArrendados(usuarioID);
    }

    public ArrayList<Terreno> mostrarTerrenosPorPreco(double value){
        return feedRepository.mostrarTerrenosPorPreco(value);
    }

    public ArrayList<Terreno> mostrarTerrenosPorTitulo(String titulo, Integer usuarioID) {
        return feedRepository.mostrarTerrenosPorTitulo(titulo, usuarioID);
    }
}