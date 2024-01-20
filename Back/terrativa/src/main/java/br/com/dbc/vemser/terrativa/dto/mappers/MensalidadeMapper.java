package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.RequestMensalidade;
import br.com.dbc.vemser.terrativa.dto.ResponseMensalidade;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;

public class MensalidadeMapper {

    public static ResponseMensalidade MensalidadeParaResponseMensalidade(Mensalidade entity) {
        ResponseMensalidade dto = new ResponseMensalidade();
        dto.setMensalidadeID(entity.getMensalidadeID());
        dto.setContratoID(entity.getContratoID());
        dto.setValorMensal(entity.getValorMensal());
        dto.setAnoExercicio(entity.getAnoExercicio());
        dto.setDataReajuste(entity.getDataReajuste());
        return dto;
    }

    public static Mensalidade RequestMensalidadeParaMensalidade(RequestMensalidade dto) {
        Mensalidade entity = new Mensalidade();
        entity.setMensalidadeID(dto.getMensalidadeID());
        entity.setContratoID(dto.getContratoID());
        entity.setValorMensal(dto.getValorMensal());
        entity.setAnoExercicio(dto.getAnoExercicio());
        entity.setDataReajuste(dto.getDataReajuste());
        return entity;
    }
}
