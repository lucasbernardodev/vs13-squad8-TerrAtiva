package controllers;

import app.Feed;
import util.Formatador;

public class FeedController {
    static Feed app = new Feed();
    public static String mostrarTerrenosDisponveis() {
        return Formatador.readerListTerrenos(app.mostrarTerrenosDisponveis());
    }

    public static String mostrarTerrenosPorLocalizacao(String localizacao) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorLocalizacao(localizacao));
    }

    public static String mostrarTerrenosPorPrecoMenor(double valor) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorPrecoMenor(valor));
    }

    public static String mostrarTerrenosPorTitulo(String titulo) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorTitulo(titulo));
    }

    public static String mostrarTerrenosPorLocatario(String nomeLocatario) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorLocatario(nomeLocatario));
    }

    public static String mostrarTerrenosPorTamanho(String tamanho) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorTamanho(tamanho));
    }
}
