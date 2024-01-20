package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.RequestContrato;
import br.com.dbc.vemser.terrativa.dto.ResponseContrato;
import br.com.dbc.vemser.terrativa.entity.Contrato;

public class ContratoMapper {

    public static ResponseContrato ContratoParaResponseContrato(Contrato entity) {
        ResponseContrato dto = new ResponseContrato();
        dto.setId(entity.getId());
        dto.setProprietarioID(entity.getProprietarioID());
        dto.setTerrenoID(entity.getTerrenoID());
        dto.setAtivo(entity.getAtivo());
        dto.setDataAssinatura(entity.getDataAssinatura());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFinal(entity.getDataFinal());
        dto.setDataVencimentoAluguel(entity.getDataVencimentoAluguel());
        return dto;
    }

    public static Contrato RequestContratoParaContrato(RequestContrato dto) {
        Contrato entity = new Contrato();
        entity.setId(dto.getId());
        entity.setProprietarioID(dto.getProprietarioID());
        entity.setTerrenoID(dto.getTerrenoID());
        entity.setAtivo(dto.getAtivo());
        entity.setDataAssinatura(dto.getDataAssinatura());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFinal(dto.getDataFinal());
        entity.setDataVencimentoAluguel(dto.getDataVencimentoAluguel());
        return entity;
    }
}
