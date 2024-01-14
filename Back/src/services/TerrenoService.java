package services;


import models.Aluguel;
import models.Contrato;
import models.Mensalidade;
import models.Terreno;
import repository.TerrenoRepository;

import util.validar.ValidarModel;

import java.time.LocalDate;

public class TerrenoService {
   private TerrenoRepository terrenoRepository = new TerrenoRepository();
    public void cadastrarTerreno( String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {
        ValidarModel.TERRENOS(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
        terrenoRepository.adicionar(new Terreno(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel));
    }

    public void alterarTerreno(Integer id, String titulo, String descricao, Integer proprietarioID, Integer enderecoID, double preco, String tamanho, String disponivel) {
        ValidarModel.TERRENOS(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel);
        terrenoRepository.alterar(id, new Terreno(titulo,descricao,proprietarioID,enderecoID,preco,tamanho,disponivel));
    }

    public Terreno buscarTerreno(int idTerreno) {
        return terrenoRepository.resgatarDadosPorId(idTerreno);
    }

    public void deletarTerreno(int idTerreno) {
        terrenoRepository.deletar(idTerreno);
    }

    public void arrendarTerreno(Integer proprietarioID, Integer terrenoID,
                                LocalDate dataAssinatura, LocalDate dataInicio, LocalDate dataFinal,
                                Integer dataVencimentoAluguel, // CONTRATO
                                double valorMensal, Integer anoExercicio, // MENSALIDADE
                                Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento,
                                double taxas, String codigoBarras, LocalDate dataPagamento) { // ALUGUEL

        ValidarModel.CONTRATOS(proprietarioID, terrenoID, dataAssinatura, dataInicio, dataFinal, dataVencimentoAluguel);
        ValidarModel.MENSALIDADES(valorMensal, anoExercicio);
        ValidarModel.ALUGUEL_PAGAMENTOS(mesReferencia, dataEmissao, dataVencimento, taxas, codigoBarras, dataPagamento);

        terrenoRepository.arrendarTerreno(
                new Contrato(proprietarioID, terrenoID, dataAssinatura, dataInicio, dataFinal, dataVencimentoAluguel),
                new Mensalidade(valorMensal, anoExercicio),
                new Aluguel(mesReferencia, dataEmissao, dataVencimento, taxas, codigoBarras, dataPagamento));
    }
}
