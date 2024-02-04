package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.entity.Endereco;

public class EnderecoMapper {

    public static ResponseEnderecoDTO EnderecoParaResponseEndereco(Endereco entity) {
        ResponseEnderecoDTO dto = new ResponseEnderecoDTO();
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setBairro(entity.getBairro());
        dto.setEstado(entity.getCodIBGE().getNomeEstado());
        dto.setCidade(entity.getCodIBGE().getNomeMunicipio());
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
        entity.setCodMunIBGE(dto.getCodigoMunicipioIBGE());
        entity.setCep(dto.getCep());

        return entity;
    }
}
