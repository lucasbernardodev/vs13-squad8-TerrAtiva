package controllers;

import app.Feed;
import util.Formatador;

public class FeedController {
    Feed app = new Feed();
    public String mostrarTerrenosDisponveis() {
        return Formatador.readerListTerrenos(app.mostrarTerrenosDisponveis());
    }

    public String mostrarTerrenosPorLocalizacao(String localizacao) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorLocalizacao(localizacao));
    }

    public String mostrarTerrenosPorPrecoMenor(double valor) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorPrecoMenor(valor));
    }

    public String mostrarTerrenosPorTitulo(String titulo) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorTitulo(titulo));
    }

    public String mostrarTerrenosPorLocatario(String nomeLocatario) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorLocatario(nomeLocatario));
    }

    public String mostrarTerrenosPorTamanho(String tamanho) {
        return Formatador.readerListTerrenos(app.mostrarTerrenosPorTamanho(tamanho));
    }
}
