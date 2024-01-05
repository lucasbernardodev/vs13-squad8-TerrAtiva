package services;

import database.BancoDeDados;
import models.Locador;
import models.Locatario;
import models.Terreno;

public class TerrenoService {

    public void cadastrarTerreno(String titulo, String descricao, String localizacao,
                                 String tamanho, double preco, Locatario proprietario) {

        // TODO: VALIDAR PARAMETROS;

        BancoDeDados.terrenosDataBase.add(new Terreno(titulo, descricao, localizacao,
                tamanho, preco, proprietario));

    }

    public boolean atualizarDadosTerreno(int idTerreno, Terreno terrenoAtualizado) {
        // TODO: VALIDAR PARAMETROS;
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getId() == idTerreno)
                .findFirst()
                .map(terreno -> {
                    terreno.setTitulo(terrenoAtualizado.getTitulo());
                    terreno.setDescricao(terrenoAtualizado.getDescricao());
                    terreno.setLocalizacao(terrenoAtualizado.getLocalizacao());
                    terreno.setPreco(terrenoAtualizado.getPreco());
                    terreno.setLocador(terrenoAtualizado.getLocador());
                    terreno.setDisponivel(terrenoAtualizado.isDisponivel());
                    return true;
                }).orElse(false);

    }

    public boolean deletarTerreno(int idTerreno) {
        // TODO: VALIDAR PARAMETROS;

        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getId() == idTerreno)
                .findFirst()
                .map(terreno -> {
                    BancoDeDados.terrenosDataBase.remove(terreno);
                    return true;
                }).orElse(false);
    }
}