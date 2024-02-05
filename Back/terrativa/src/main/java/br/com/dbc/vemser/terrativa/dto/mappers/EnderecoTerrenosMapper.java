package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoTerrenosCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;

public class EnderecoTerrenosMapper {

    public static ResponseEnderecoTerrenosDTO EnderecoTerrenosParaResponseEnderecoTerrenos(EnderecoTerrenos entity) {
        ResponseEnderecoTerrenosDTO dto = new ResponseEnderecoTerrenosDTO();
        dto.setId(entity.getId());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setBairro(entity.getBairro());
        dto.setCodigoMunicipioIBGE(entity.getCodMunicipioIBGE());
        dto.setCep(entity.getCep());
        dto.setLocalizacao(entity.getLocalizacao());
        return dto;
    }

    public static EnderecoTerrenos RequestEnderecoTerrenosParaEnderecoTerrenos(RequestEnderecoTerrenosCreateDTO dto) {
        EnderecoTerrenos entity = new EnderecoTerrenos();
        entity.setId(dto.getId());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setCodMunicipioIBGE(dto.getCodigoMunicipioIBGE());
        entity.setCep(dto.getCep());
        entity.setLocalizacao(dto.getLocalizacao());
        return entity;
    }

}
