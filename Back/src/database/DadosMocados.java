package database;

import models.Locador;
import models.Locatario;
import services.LocatarioService;
import controllers.LocadorController;
import controllers.LocatarioController;
import controllers.TerrenoController;
import services.TerrenoService;

public class DadosMocados {
    public static void databaseOn() {

        TerrenoController terrenoController = new TerrenoController();
        LocadorController locadorController = new LocadorController();
        LocatarioController locatarioController = new LocatarioController();


        /**
         * DADOS MOCADOS LOCADORES
         */
        locadorController.cadastrar("davidk", "david@gmail.com", "123456", "David", "02/10/97");
        locadorController.cadastrar("diuli", "diuli@gmail.com", "qwe123", "Diuliano", "01/05/98");
        locadorController.cadastrar("Leonardo", "dasdsad", "12345", "lkfkj", "040158");
        locadorController.cadastrar("Mariana", "dasdsad", "123", "lkfkj", "040158");
        locadorController.cadastrar("bersch", "pedrobersch@hotmail.com", "123456", "Pedro Bersch", "16/04/2003");
        locadorController.cadastrar("shai", "shaienne@gmail.com", "123456", "Shaianne", "01/01/2000");
        locadorController.cadastrar("italo", "italo@gmail.com", "123456", "Italo Lacerda", "01/01/2000");

        /**
         * DADOS MOCADOS LOCATARIOS
         */
        locatarioController.cadastrar("lucas", "lucas@gmail.com",
                "qwerty", "Lucas Silva", "11/22/97");
        locatarioController.cadastrar("maria", "maria@gmail.com",
                "abc123", "maria madalena", "01/05/91");
        locatarioController.cadastrar("Mariana", "dfdf", "12345", "dff", "fsdfdsf");
        locatarioController.cadastrar("roger", "roger@gmail.com", "123456", "Roger", "01/01/2000");
        locatarioController.cadastrar("jessica", "jessica@gmail.com", "123456", "Jessica", "01/01/2000");
        locatarioController.cadastrar("pedroH", "pedrohenrique@gmail.com", "123456", "Pedro Henrique", "01/01/2000");

        /**
         * DADOS MOCADOS TERRENOS
         */
        Locatario locatarioUm = new Locatario("lucas", "lucas@gmail.com",
                "qwerty", "Lucas Silva", "11/22/97");
        //BancoDeDados.locatariosDataBase.add(locatarioUm);
        Locatario locatarioDois = new Locatario("roger", "roger@gmail.com", "123456", "Roger", "01/01/2000");
        Locatario locatarioTres = new Locatario("jessica", "jessica@gmail.com", "123456", "Jessica", "01/01/2000");
        Locatario locatarioQuatro = new Locatario("pedroH", "pedrohenrique@gmail.com", "123456", "Pedro Henrique", "01/01/2000");

        terrenoController.cadastrarTerreno("Campo de Futebol Abandonado", "Baixada Fluminense", "Terra do nunca",
                "Gigantesco", 285000.0, locatarioUm);

        terrenoController.cadastrarTerreno("Terreno Baldio", "Proximo: Posto de Saúde, Farmácia", "Eldorado do Sul",
                "Grande", 180000.0, locatarioDois);

        terrenoController.cadastrarTerreno("Sitio", "Fazenda", "Triunfo",
                "Grande", 690000.0, locatarioDois);

        terrenoController.cadastrarTerreno("Chácara", "Completo frutífera, poço artesiano", "Palmares do Sul",
                "Médio", 290.0000, locatarioTres);

        terrenoController.cadastrarTerreno("Fazenda Rural", "30 Hectares", "Charquedas",
                "Gigantesco", 1850000.0, locatarioQuatro);

        terrenoController.cadastrarTerreno("Terreno Aterrado", "Terreno limpo, aterrado", "Cachoeirinha",
                "Pequeno", 78000.0, locatarioUm);
        terrenoController.cadastrarTerreno("Fazenda", "Terras fértil", "Bagé",
                "Gigantesco", 3200000.0, locatarioDois);
        terrenoController.cadastrarTerreno("Terreno com Lago", "Linda vista para lago", "Eldorado do Sul",
                "Pequeno", 40000.0, locatarioTres);
        terrenoController.cadastrarTerreno("Mata Nativa", "Terreno com vista para vale", "Triunfo",
                "Gigantesco", 60000.0, locatarioQuatro);

        Locador locadorteste = new Locador("davidk", "david@gmail.com", "123456", "David", "02/10/97");
        locadorController.arrendarTerreno(1, locadorteste);

    }
}
