package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.RequestEndereco;
import br.com.dbc.vemser.terrativa.dto.ResponseEndereco;
import br.com.dbc.vemser.terrativa.entity.Endereco;

public class EnderecoMapper {

    public static ResponseEndereco EnderecoParaResponseEndereco(Endereco entity) {
        ResponseEndereco dto = new ResponseEndereco();
        dto.setId(entity.getId());
        dto.setUsuarioID(entity.getUsuarioID());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setBairro(entity.getBairro());
        dto.setCep(entity.getCep());
        return dto;
    }

    public static Endereco RequestEnderecoParaEndereco(RequestEndereco dto) {
        Endereco entity = new Endereco();
        entity.setId(dto.getId());
        entity.setUsuarioID(dto.getUsuarioID());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setCep(dto.getCep());
        return entity;
    }
}
