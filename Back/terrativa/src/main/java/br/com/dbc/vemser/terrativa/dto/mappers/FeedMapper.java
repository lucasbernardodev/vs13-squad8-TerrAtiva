package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.RequestFeed;
import br.com.dbc.vemser.terrativa.dto.ResponseFeed;
import br.com.dbc.vemser.terrativa.entity.Feed;

public class FeedMapper {

    public static ResponseFeed FeedParaResponseFeed(Feed entity) {
        ResponseFeed dto = new ResponseFeed();
        dto.setTerrenoId(entity.getTerrenoId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        dto.setTamanho(entity.getTamanho());
        dto.setEstado(entity.getEstado());
        dto.setCidade(entity.getCidade());
        dto.setCod_estado(entity.getCod_estado());
        return dto;
    }

    public static Feed RequestFeedParaFeed(RequestFeed dto) {
        Feed entity = new Feed();
        entity.setTerrenoId(dto.getTerrenoId());
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setTamanho(dto.getTamanho());
        entity.setEstado(dto.getEstado());
        entity.setCidade(dto.getCidade());
        entity.setCod_estado(dto.getCod_estado());
        return entity;
    }
}
