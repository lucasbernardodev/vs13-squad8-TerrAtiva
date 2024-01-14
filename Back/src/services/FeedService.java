package services;

import models.Feed;
import models.Terreno;
import repository.FeedRepository;

import java.util.ArrayList;

public class FeedService {
    private FeedRepository feedRepository = new FeedRepository();

    public ArrayList<Feed> mostrarTerrenosDisponiveis() {
        return feedRepository.buscarTerrenos();
    }

    public String preparaPesquisa(String pesquisa) {

        if (pesquisa == "") {
            return "(+)";
        }

        String[] pesquisaFatiada = pesquisa.split(" ");
        StringBuilder resultado = new StringBuilder();

        resultado.append("(");
        for (String p : pesquisaFatiada) {
            resultado.append(p);
            resultado.append("(+)|");
        }
        resultado.deleteCharAt(resultado.length() -1);
        resultado.append(")");
        System.out.println(resultado.toString());
        return resultado.toString();
    }


//    public ArrayList<Terreno> mostrarTerrenosPorPreco(double value){
//        return feedRepository.mostrarTerrenosPorPreco(value);
//    }
//
//    public ArrayList<Terreno> mostrarTerrenosPorTitulo(String titulo) {
//        return feedRepository.mostrarTerrenosPorTitulo(titulo);
//    }

//    public ArrayList<Terreno> mostrarTerrenosPorLocatario(String name) {
//        return feedRepository.mostrarTerrenosPorLocatario(name);
//    }
//
//    public ArrayList<Terreno> mostrarTerrenosPorTamanho(String tamanho) {
//        return feedRepository.mostrarTerrenosPorTamanho(tamanho);
//    }
}
