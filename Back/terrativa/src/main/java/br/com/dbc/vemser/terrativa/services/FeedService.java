package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.repository.FeedRepository;

import java.util.ArrayList;

public class FeedService {
    private final FeedRepository feedRepository = new FeedRepository();

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

    public String preparaPesquisa(String pesquisa) {

        if (pesquisa == "") {
            return "(+)";
        }

        String[] pesquisaFatiada = pesquisa.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String p : pesquisaFatiada) {
            resultado.append("[");
            resultado.append(p);
            resultado.append("(+)");
            resultado.append("]");
        }
        System.out.println(resultado.toString());
        return resultado.toString();
    }

}
