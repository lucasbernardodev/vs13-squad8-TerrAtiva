package util;

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
}