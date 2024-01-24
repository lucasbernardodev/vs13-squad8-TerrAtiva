package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.RequestEnderecoTerrenos;
import br.com.dbc.vemser.terrativa.dto.ResponseEnderecoTerrenos;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;

public class EnderecoTerrenosMapper {

    public static ResponseEnderecoTerrenos EnderecoTerrenosParaResponseEnderecoTerrenos(EnderecoTerrenos entity) {
        ResponseEnderecoTerrenos dto = new ResponseEnderecoTerrenos();
        dto.setId(entity.getId());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setBairro(entity.getBairro());
        dto.setCodigoMunicipioIBGE(entity.getCodigoMunicipioIBGE());
        dto.setCep(entity.getCep());
        dto.setLocalizacao(entity.getLocalizacao());
        return dto;
    }

    public static EnderecoTerrenos RequestEnderecoTerrenosParaEnderecoTerrenos(RequestEnderecoTerrenos dto) {
        EnderecoTerrenos entity = new EnderecoTerrenos();
        entity.setId(dto.getId());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setCodigoMunicipioIBGE(dto.getCodigoMunicipioIBGE());
        entity.setCep(dto.getCep());
        entity.setLocalizacao(dto.getLocalizacao());
        return entity;
    }

}
