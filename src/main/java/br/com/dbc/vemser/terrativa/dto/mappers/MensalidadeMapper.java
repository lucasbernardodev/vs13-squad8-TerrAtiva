package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseMensalidadeDTO;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;

public class MensalidadeMapper {

    public static ResponseMensalidadeDTO MensalidadeParaResponseMensalidade(Mensalidade entity) {
        ResponseMensalidadeDTO dto = new ResponseMensalidadeDTO();
        dto.setMensalidadeID(entity.getMensalidadeID());
        dto.setContratoID(entity.getContratoID());
        dto.setValorMensal(entity.getValorMensal());
        dto.setAnoExercicio(entity.getAnoExercicio());
        return dto;
    }

    public static Mensalidade RequestMensalidadeParaMensalidade(RequestMensalidadeCreateDTO dto) {
        Mensalidade entity = new Mensalidade();
        entity.setMensalidadeID(dto.getMensalidadeID());
        entity.setContratoID(dto.getContratoID());
        entity.setValorMensal(dto.getValorMensal());
        entity.setAnoExercicio(dto.getAnoExercicio());
        return entity;
    }
}
