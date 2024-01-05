package services;

import database.BancoDeDados;
import models.Locador;
import models.Locatario;
import models.Terreno;

public class TerrenoService {

    public void cadastrarTerreno(String titulo, String descricao, String localizacao,
                                 String tamanho, double preco, Locatario proprietario,
                                 Locador locador, boolean disponivel) {

        // TODO: VALIDAR PARAMETROS;

        BancoDeDados.terrenosDataBase.add(new Terreno(titulo, descricao, localizacao,
                tamanho, preco, proprietario,
                locador, disponivel));

    }

    public boolean atualizarDadosTerreno(int idTerreno, Terreno terrenoAtualizado) {
        // TODO: VALIDAR PARAMETROS;
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getId() == idTerreno)
                .findFirst()
                .map(terreno -> {
                    terreno.setTitulo(terreno.getTitulo());
                    terreno.setDescricao(terreno.getDescricao());
                    terreno.setLocalizacao(terreno.getLocalizacao());
                    terreno.setPreco(terreno.getPreco());
                    terreno.setLocador(terreno.getLocador());
                    terreno.setDisponivel(terreno.isDisponivel());
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
