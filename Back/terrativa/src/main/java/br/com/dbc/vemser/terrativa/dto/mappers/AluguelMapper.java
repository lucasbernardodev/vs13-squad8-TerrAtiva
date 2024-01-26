package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.RequestAluguelCreateDTO;
import br.com.dbc.vemser.terrativa.dto.ResponseAluguelDTO;
import br.com.dbc.vemser.terrativa.entity.Aluguel;
public class AluguelMapper {

    public static ResponseAluguelDTO AluguelParaResponseAluguel(Aluguel entity) {
        ResponseAluguelDTO dto = new ResponseAluguelDTO();
        dto.setPagamentoID(entity.getPagamentoID());
        dto.setMensalidadeID(entity.getMensalidadeID());
        dto.setMesReferencia(entity.getMesReferencia());
        dto.setDataEmissao(entity.getDataEmissao());
        dto.setDataVencimento(entity.getDataVencimento());
        dto.setTaxas(entity.getTaxas());
        dto.setCodigoBarras(entity.getCodigoBarras());
        dto.setDataPagamento(entity.getDataPagamento());
        dto.setPago(entity.getPago());
        return dto;
    }

    public static Aluguel RequestAluguelParaAluguel(RequestAluguelCreateDTO dto) {
        Aluguel entity = new Aluguel();
        entity.setPagamentoID(dto.getPagamentoID());
        entity.setMensalidadeID(dto.getMensalidadeID());
        entity.setMesReferencia(dto.getMesReferencia());
        entity.setDataEmissao(dto.getDataEmissao());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setTaxas(dto.getTaxas());
        entity.setCodigoBarras(dto.getCodigoBarras());
        entity.setDataPagamento(dto.getDataPagamento());
        entity.setPago(dto.getPago());
        return entity;
    }
}
