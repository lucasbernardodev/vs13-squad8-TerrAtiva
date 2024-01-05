package database;

import models.Locador;
import models.Locatario;
import models.Terreno;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    public static List<Terreno> terrenosDataBase = new ArrayList<>();
    public static List<Locador> locadoresDataBase = new ArrayList<>();
    public static List<Locatario> locatariosDataBase = new ArrayList<>();

    private static int quantidadeTerrenosCadastrados = 1;
    private static int quantidadeLocadoresCadastrados = 1;
    private static int quantidadeLocatariosCadastrados = 1;

    public static int novoLocadorID(){
        return quantidadeLocadoresCadastrados++;
    }
    public static int novoLocatarioID(){
        return quantidadeLocatariosCadastrados++;
    }
    public static int novoTerrenoID(){
        return quantidadeTerrenosCadastrados++;
    }


}
