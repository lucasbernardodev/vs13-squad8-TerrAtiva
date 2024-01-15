package app;

import app.menus.LoginMenu;
import database.BancoDeDados;

import java.sql.Connection;

public class App {
    LoginMenu loginMenu = new LoginMenu();
    public void iniciaAplicacao() {
        loginMenu.inicio();
    }
}
