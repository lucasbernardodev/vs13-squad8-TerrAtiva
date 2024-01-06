package database;

import models.Locador;
import models.Locatario;
import models.Terreno;
import services.TerrenoService;
import services.LocadorService;
import services.LocatarioService;
import controllers.LocadorController;
import controllers.LocatarioController;
import controllers.TerrenoController;

public class DadosMocados {
    public static void databaseOn() {

        LocatarioService locatarioService = new LocatarioService();
        TerrenoService terrenoService = new TerrenoService();
        LocadorController locadorController = new LocadorController();
        LocatarioController locatarioController = new LocatarioController();
        TerrenoController terrenoController = new TerrenoController();


        /**
         * DADOS MOCADOS LOCADORES
         */
        locadorController.cadastrar("davidk", "david@gmail.com",
                "123456", "David", "02/10/97");
        locadorController.cadastrar("diuli", "diuli@gmail.com",
                "qwe123", "Diuliano", "01/05/98");
        locadorController.cadastrar("Leonardo", "dasdsad",
                "12345", "lkfkj", "040158");
        locadorController.cadastrar("Mariana", "dasdsad",
                "123", "lkfkj", "040158");
        /**
         * DADOS MOCADOS LOCATARIOS
         */

        locatarioController.cadastrar("lucas", "lucas@gmail.com",
                "qwerty", "Lucas Silva", "11/22/97");
        locatarioController.cadastrar("maria", "maria@gmail.com",
                "abc123", "maria madalena", "01/05/91");
        locatarioController.cadastrar("Mariana", "dfdf", "12345", "dff", "fsdfdsf");

        /**
         * DADOS MOCADOS TERRENOS
         */
        Locatario locatario1 = new Locatario("lucas", "lucas@gmail.com",
                "qwerty", "Lucas Silva", "11/22/97");


        terrenoService.cadastrarTerreno("Campo de Futebol Abandonado", "Baixada Fluminense ", "Terra do nunca",
                "Gigantesco", 1000.23, locatario1);

        terrenoService.cadastrarTerreno("Campo 2", "Arco Iris", "Terra do nunca",
                "Gigantesco", 1000.23, locatario1);

        terrenoService.cadastrarTerreno("Campo 3", "Arco Iris", "Terra do nunca",
                "Gigantesco", 1000.23, locatario1);

        terrenoService.cadastrarTerreno("Campo 4", "Arco Iris", "Terra do nunca",
                "Gigantesco", 1000.23, locatario1);

        terrenoService.cadastrarTerreno("Campo 5", "Arco Iris", "Terra do nunca",
                "Gigantesco", 1000.23, locatario1);

        terrenoService.cadastrarTerreno("Campo 6", "Arco Iris", "Terra do nunca",
                "Gigantesco", 1000.23, locatario1);

        Terreno t1 = new Terreno("Campo minha nossa", "Arco Iris", "Terra do nunca",
                "Gigantesco", 1000.23, locatario1);

    }
}
