package app;

import app.menus.SessaoMenu;
import database.DadosMocados;

public class App {
    public static void iniciaAplicacao() {
        DadosMocados.databaseOn();
        SessaoMenu.menuInicial();
    }
}
