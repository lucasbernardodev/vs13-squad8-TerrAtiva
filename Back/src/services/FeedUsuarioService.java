package services;

import models.Terreno;
import repository.FeedRepository;

import java.util.ArrayList;

public class FeedUsuarioService {
    private FeedRepository feedRepository = new FeedRepository();

    public ArrayList<Terreno> mostrarTerrenosDisponiveis(Integer usuarioID) {
        return feedRepository.mostrarTerrenosDisponiveis(usuarioID);
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
