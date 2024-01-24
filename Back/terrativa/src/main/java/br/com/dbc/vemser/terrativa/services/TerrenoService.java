package br.com.dbc.vemser.terrativa.services;


import br.com.dbc.vemser.terrativa.dto.RequestTerreno;
import br.com.dbc.vemser.terrativa.dto.ResponseTerreno;
import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.entity.Aluguel;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import br.com.dbc.vemser.terrativa.config.validar.ValidarModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TerrenoService {
   private final TerrenoRepository terrenoRepository;

    public ResponseTerreno buscarTerreno(Integer idTerreno) {
        return TerrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.resgatarDadosPorId(idTerreno));
    }

    public ResponseTerreno cadastrarTerreno(RequestTerreno requestTerreno) {
        return TerrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.adicionar(
                        TerrenoMapper.requestTerrenoParaTerreno(requestTerreno)));
    }

    public ResponseTerreno alterarTerreno(RequestTerreno requestTerreno) {
        return TerrenoMapper.terrenoParaResponseTerreno(
                terrenoRepository.alterar(
                        TerrenoMapper.requestTerrenoParaTerreno(requestTerreno)));
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

    public void cancelarContratoTerreno(Integer usuarioID, Integer contratoID) {
        terrenoRepository.cancelarContratoTerreno(usuarioID, contratoID);
    }
}
