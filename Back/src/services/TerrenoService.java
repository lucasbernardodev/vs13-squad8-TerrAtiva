package services;

import database.BancoDeDados;
import infra.exceptions.DataNotFoundException;
import models.Locador;
import models.Locatario;
import models.Terreno;

import java.util.List;

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

    public Terreno buscarTerreno(int idTerreno) {
        Terreno buscandoTerreno = BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getId() == idTerreno)
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Terreno não Encontrado na base de dados"));

        return buscandoTerreno;
    }

    public List<Terreno> buscarTerreno(Locador locador) {
        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getLocador().getId() == locador.getId())
                .toList();
    }

    public List<Terreno> buscarTerreno(Locatario proprietario) {

        return BancoDeDados.terrenosDataBase
                .stream()
                .filter(terreno -> terreno.getProprietario() == proprietario)
                .toList();

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
