package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.entity.Endereco;

public class EnderecoMapper {

    public static ResponseEnderecoDTO EnderecoParaResponseEndereco(Endereco entity) {
        ResponseEnderecoDTO dto = new ResponseEnderecoDTO();
        dto.setId(entity.getId());
        dto.setUsuarioID(entity.getUsuarioID());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setBairro(entity.getBairro());
        dto.setCodigoMunicipioIBGE(entity.getCodigoMunicipioIBGE());
        dto.setCep(entity.getCep());
        return dto;
    }

    public static Endereco RequestEnderecoParaEndereco(RequestEnderecoCreateDTO dto) {
        Endereco entity = new Endereco();
        entity.setId(dto.getId());
        entity.setUsuarioID(dto.getUsuarioID());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setCodigoMunicipioIBGE(dto.getCodigoMunicipioIBGE());
        entity.setCep(dto.getCep());

        return entity;
    }
}
