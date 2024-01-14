package services;


import models.Terreno;
import repository.TerrenoRepository;

import util.validar.ValidarModel;


public class TerrenoService {
   private TerrenoRepository terrenoRepository = new TerrenoRepository();
    public void cadastrarTerreno( String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {
        ValidarModel.TERRENO(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
        terrenoRepository.adicionar(new Terreno(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel));
    }

    public void alterarTerreno(Integer id, String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {
        ValidarModel.TERRENO(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
        terrenoRepository.alterar(id, new Terreno(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel));
    }

    public Terreno buscarTerreno(int idTerreno) {
        return terrenoRepository.resgatarDadosPorId(idTerreno);
    }

    public void deletarTerreno(int idTerreno) {
        terrenoRepository.deletar(idTerreno);
    }
}
