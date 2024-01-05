package models;

import database.BancoDeDados;
import java.util.ArrayList;
import java.util.List;

public class Feed {

    public List<Terreno> mostrarTerrenosDisponveis(){
        ArrayList<Terreno> terrenosDisponiveis= new ArrayList<Terreno>();

        for (Terreno terreno : BancoDeDados.terrenosDataBase) {
            if (terreno.isDisponivel()) {
                terrenosDisponiveis.add(terreno);
            }
        }
        return terrenosDisponiveis;
    }
    
    
}
