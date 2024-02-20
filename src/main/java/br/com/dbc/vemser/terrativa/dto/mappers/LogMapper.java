package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.requests.LogCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.LogDTO;
import br.com.dbc.vemser.terrativa.entity.Log;
import br.com.dbc.vemser.terrativa.entity.TipoLog;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class LogMapper {

    @Enumerated(EnumType.STRING)
    private TipoLog tipoLog;
    private String descricao;
    private String data;

    public static Log CreateDTOToEntity(LogCreateDTO dto) {
        Log entity = new Log();
        entity.setTipoLog(dto.getTipoLog());
        entity.setDescricao(dto.getDescricao());
        entity.setData(dto.getData());
        return entity;
    }

    public static LogDTO EntityToDTO(Log entity) {
        LogDTO dto = new LogDTO();
        dto.setId(entity.getId());
        dto.setTipoLog(entity.getTipoLog());
        dto.setDescricao(entity.getDescricao());
        dto.setData(entity.getData());
        return dto;
    }


}
