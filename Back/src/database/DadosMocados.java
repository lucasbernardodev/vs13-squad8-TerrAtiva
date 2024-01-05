package database;

import models.Locador;
import models.Locatario;
import models.Terreno;
import services.TerrenoService;
import services.LocadorService;
import services.LocatarioService;

public class DadosMocados {
    public static void databaseOn() {

        LocadorService locadorService = new LocadorService();
        LocatarioService locatarioService = new LocatarioService();
        TerrenoService terrenoService = new TerrenoService();


        /**
         * DADOS MOCADOS LOCADORES
         */
        locadorService.cadastrar("davidk", "david@gmail.com",
                "123456", "David", "02/10/97");
        locadorService.cadastrar("diuli", "diuli@gmail.com",
                "qwe123", "Diuliano", "01/05/98");
        locadorService.atualizarPerfil(1, "davidtesteee", "david@gmail.com", "novaSenha",
                "David", "02/10/97");

        /**
         * DADOS MOCADOS LOCATARIOS
         */

        locatarioService.cadastrar("lucas", "lucas@gmail.com",
                "qwerty", "Lucas Silva", "11/22/97");
        locatarioService.cadastrar("maria", "maria@gmail.com",
                "abc123", "maria madalena", "01/05/91");

        locatarioService.atualizarPerfil(1, "cleito", "luquinhas@gmail.com", "novaSenha",
                "Lucas", "01/01/01");

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
