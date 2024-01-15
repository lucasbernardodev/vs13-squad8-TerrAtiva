package util.formatter;

import models.Feed;
import models.Terreno;

import java.util.List;

public class Formatador {
    /**
     * Formatar dados de uma lista
     *
     * @author Pedro Henrique
     *
     * @param options Lista de opções do menu
     */
    public static String readerListTerrenos(List<Terreno> options) {
        String response = "";

        for (Terreno value: options) {
            response += value.toString() + System.lineSeparator();
        }
        return response;
    }

    public static String readerListTerrenosFeed(List<Feed> options) {
        String response = "";
        for (Feed value: options) {
            response += value.toString() + System.lineSeparator();
        }
        return response;
    }

    public static String readerListEstadosFeed(List<Feed> options) {
        String response = "ID | QUANTIDADE DE ANÚNCIOS | ESTADO";
        response += System.lineSeparator();
        for (Feed value: options) {
            response += value.listaEstados() + System.lineSeparator();
        }
        return response;
    }
}
