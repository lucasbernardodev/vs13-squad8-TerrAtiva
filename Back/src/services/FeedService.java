package services;

import models.Terreno;
import repository.FeedRepository;

import java.util.ArrayList;

public class FeedService {
    private FeedRepository feedRepository = new FeedRepository();

    public ArrayList<Terreno> mostrarTerrenosDisponiveis() {
        return feedRepository.mostrarTerrenosDisponiveis();
    }

    public ArrayList<Terreno> mostrarTerrenosPorPreco(double value){
        return feedRepository.mostrarTerrenosPorPreco(value);
    }

    public ArrayList<Terreno> mostrarTerrenosPorTitulo(String titulo) {
        return feedRepository.mostrarTerrenosPorTitulo(titulo);
    }

    public ArrayList<Terreno> mostrarTerrenosPorLocatario(String name) {
        return feedRepository.mostrarTerrenosPorLocatario(name);
    }

    public ArrayList<Terreno> mostrarTerrenosPorTamanho(String tamanho) {
        return feedRepository.mostrarTerrenosPorTamanho(tamanho);
    }
}
