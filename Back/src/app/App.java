package app;

import app.menus.SessaoMenu;

public class App {
    public static void iniciaAplicacao() {
        DadosMocados.databaseOn();
        SessaoMenu.menuInicial();
    }
}
