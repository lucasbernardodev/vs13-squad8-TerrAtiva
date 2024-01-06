import app.Feed;
import database.DadosMocados;
import services.LoginService;

public class MainTest {
    public static void main(String[] args) {
        DadosMocados.databaseOn();
        LoginService login = new LoginService();
        login.menu();
        Feed app = new Feed();

        System.out.println(app.mostrarTerrenosPorLocalizacao("terra do nunca"));
    }
}
