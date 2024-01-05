package app;

import database.BancoDeDados;
import models.Terreno;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Terreno> mostrarTerrenosPorLocalizacao(String localizacao){
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getLocalizacao().equals(localizacao))
                .collect(Collectors.toList());
    }

    public List<Terreno> mostrarTerrenosPorPrecoMenor(double valor){
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getPreco() <= valor)
                .collect(Collectors.toList());
    }
    public Terreno mostrarTerrenosPorTitulo(String titulo){
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getTitulo().toLowerCase().equals(titulo.toLowerCase()))
                .findFirst()
                .get();
    }

    public List<Terreno> mostrarTerrenosPorLocatario(String nomeLocatario) {
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getProprietario().getNome().equals(nomeLocatario))
                .collect(Collectors.toList());
    }

    public List<Terreno> mostrarTerrenosPorTamanho(String tamanhoTerreno) {
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getTamanho().equals(tamanhoTerreno))
                .collect(Collectors.toList());
    }
}
