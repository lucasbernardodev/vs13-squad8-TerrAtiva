import app.Feed;
import database.DadosMocados;
import app.Menus;


public class Main {
    public static void main(String[] args) {

        System.out.println("Bem vindo a TerrAtiva!" +
                "\nA platforma que facilita o arrendamento de terras, conectando pessoas!");

        DadosMocados.databaseOn();
        Menus.menuTemp();
        Feed app = new Feed();


    }
}