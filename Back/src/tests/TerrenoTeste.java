package tests;

import controllers.TerrenoController;

public class TerrenoTeste {
    public static void main(String[] args) {
        TerrenoController tc = new TerrenoController();
        System.out.println(tc.resgatarTerrenoPorID(1));

        System.out.println("\\\\\\\\\\\\\\\\\\\\");

        tc.atualizarTerreno(1,
                "your_titulo_value",
                "your_descricao_value",
                2,
                3,
                696969,
                "your_tamanho_value",
                "S");
        System.out.println(tc.resgatarTerrenoPorID(1));

        System.out.println("\\\\\\\\\\\\\\\\\\\\");

        tc.deletarDados(1);
        System.out.println(tc.resgatarTerrenoPorID(1));

        System.out.println("\\\\\\\\\\\\\\\\\\\\");

        tc.cadastrarTerreno(
                "your_titulo_value",
                "your_descricao_value",
                2,
                3,
                696969,
                "your_tamanho_value",
                "S");
    }
}
