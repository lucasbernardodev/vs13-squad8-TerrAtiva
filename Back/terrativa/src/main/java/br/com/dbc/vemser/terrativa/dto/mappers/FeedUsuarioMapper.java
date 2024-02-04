package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedUsuarioDTO;
import br.com.dbc.vemser.terrativa.entity.FeedUsuario;

public class FeedUsuarioMapper {

    public static ResponseFeedUsuarioDTO feedParaResponseFeed(FeedUsuario entity) {
        ResponseFeedUsuarioDTO dto = new ResponseFeedUsuarioDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setProprietarioID(entity.getProprietarioID());
        dto.setEnderecoID(entity.getEnderecoID());
        dto.setPreco(entity.getPreco());
        dto.setTamanho(entity.getTamanho());
        dto.setDisponivel(entity.getDisponivel());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setBairro(entity.getBairro());
        dto.setCodigoMunicipioIBGE(entity.getCodigoMunicipioIBGE());
        dto.setCep(entity.getCep());
        dto.setLocalizacao(entity.getLocalizacao());

        return dto;
    }

//    public static FeedUsuario requestFeedParaFeed(RequestFeedCreateDTO dto) {
//        FeedUsuario entity = new FeedUsuario();
//        entity.setId(dto.getId());
//        entity.setTitulo(dto.getTitulo());
//        entity.setDescricao(dto.getDescricao());
//        entity.setProprietarioID(dto.getProprietarioID());
//        entity.setEnderecoID(dto.getEnderecoID());
//        entity.setPreco(dto.getPreco());
//        entity.setTamanho(dto.getTamanho());
//        entity.setDisponivel(dto.getDisponivel());
//        entity.setLogradouro(dto.getLogradouro());
//        entity.setNumero(dto.getNumero());
//        entity.setComplemento(dto.getComplemento());
//        entity.setBairro(dto.getBairro());
//        entity.setCodigoMunicipioIBGE(dto.getCodigoMunicipioIBGE());
//        entity.setCep(dto.getCep());
//        entity.setLocalizacao(dto.getLocalizacao());
//        return entity;
//    }

}
