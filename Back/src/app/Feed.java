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
                .filter(terreno -> terreno.getLocalizacao().toLowerCase().contains(localizacao.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Terreno> mostrarTerrenosPorPrecoMenor(double valor){
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getPreco() <= valor)
                .collect(Collectors.toList());
    }
    
    public List<Terreno> mostrarTerrenosPorTitulo(String titulo){
        List<Terreno> tituloExato = 
                BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());

        if (!tituloExato.isEmpty())
        {
            return tituloExato;
        }

        return BancoDeDados.terrenosDataBase
                    .stream()
                    .filter(terreno -> terreno.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                    .collect(Collectors.toList());
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
                .filter(terreno -> terreno.getTamanho().contains(tamanhoTerreno))
                .collect(Collectors.toList());
    }
}
