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

    public void limparFiltros() {
        feedRepository.limparFiltros();
    }

//        feedRepository.setEstado("26");
//        feedRepository.filtrarPorCaracteristicas("campo");
//         feedRepository.limparFiltros();
//        System.out.println("\n\n\n#################### NOVA CONSULTA ##########################\n\n\n");
//        feedRepository.filtrarPorValor("aproximadamente 2000");
//        feedRepository.filtrarPorTamanho("10 hectares");
//    response = feedRepository.buscarTerrenos();

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
        return resultado.toString();
    }

}
