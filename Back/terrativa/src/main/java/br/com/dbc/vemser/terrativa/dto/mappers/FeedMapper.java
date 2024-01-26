package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.entity.Feed;

public class FeedMapper {

    public static ResponseFeedDTO FeedParaResponseFeed(Feed entity) {
        ResponseFeedDTO dto = new ResponseFeedDTO();
        dto.setTerrenoId(entity.getTerrenoId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        dto.setTamanho(entity.getTamanho());
        dto.setEstado(entity.getEstado());
        dto.setCidade(entity.getCidade());
        dto.setCod_estado(entity.getCod_estado());
        dto.setQuantidade(entity.getQuantidade());
        return dto;
    }

//    public static Feed RequestFeedParaFeed(RequestFeedCreateDTO dto) {
//        Feed entity = new Feed();
//        entity.setTerrenoId(dto.getTerrenoId());
//        entity.setTitulo(dto.getTitulo());
//        entity.setDescricao(dto.getDescricao());
//        entity.setPreco(dto.getPreco());
//        entity.setTamanho(dto.getTamanho());
//        entity.setEstado(dto.getEstado());
//        entity.setCidade(dto.getCidade());
//        entity.setCod_estado(dto.getCod_estado());
//        entity.setQuantidade(dto.getQuantidade());
//        return entity;
//    }
}
