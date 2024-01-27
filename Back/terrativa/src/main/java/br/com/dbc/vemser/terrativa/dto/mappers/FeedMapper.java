package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedDTO;
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

}
