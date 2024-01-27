package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;

public class ContratoMapper {

    public static ResponseContratoDTO contratoParaResponseContrato(Contrato entity) {
        ResponseContratoDTO dto = new ResponseContratoDTO();
        dto.setId(entity.getId());
        dto.setLocatarioID(entity.getLocatarioID());
        dto.setTerrenoID(entity.getTerrenoID());
        dto.setAtivo(entity.getAtivo());
        dto.setDataAssinatura(entity.getDataAssinatura());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFinal(entity.getDataFinal());
        dto.setDataVencimentoAluguel(entity.getDataVencimentoAluguel());
        return dto;
    }

    public static Contrato requestContratoParaContrato(RequestContratoCreateDTO dto) {
        Contrato entity = new Contrato();
        entity.setId(dto.getId());
        entity.setLocatarioID(dto.getLocatarioID());
        entity.setTerrenoID(dto.getTerrenoID());
        entity.setAtivo(dto.getAtivo());
        entity.setDataAssinatura(dto.getDataAssinatura());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFinal(dto.getDataFinal());
        entity.setDataVencimentoAluguel(dto.getDataVencimentoAluguel());
        return entity;
    }
}
