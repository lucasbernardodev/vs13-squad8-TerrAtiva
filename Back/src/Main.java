import app.Feed;
import database.DadosMocados;


public class Main {
    public static void main(String[] args) {
        DadosMocados.databaseOn();
        Feed app = new Feed();
        
        System.out.println(app.mostrarTerrenosPorLocalizacao("terra do nunca"));
    }
}