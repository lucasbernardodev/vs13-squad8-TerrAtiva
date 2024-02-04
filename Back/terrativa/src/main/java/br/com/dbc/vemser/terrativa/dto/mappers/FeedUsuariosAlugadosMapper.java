package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedUsuarioAlugadosDTO;
import br.com.dbc.vemser.terrativa.entity.FeedUsuariosAlugados;

public class FeedUsuariosAlugadosMapper {

    public static ResponseFeedUsuarioAlugadosDTO feedUsuariosParaAlugados(FeedUsuariosAlugados entity) {
        ResponseFeedUsuarioAlugadosDTO dto = new ResponseFeedUsuarioAlugadosDTO();
        dto.setContratoID(entity.getContratoID());
        dto.setLocatarioID(entity.getLocatarioID());
        dto.setTerrenoID(entity.getTerrenoID());
        dto.setAtivo(entity.getAtivo());
        dto.setDataAssinatura(entity.getDataAssinatura());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFinal(entity.getDataFinal());
        dto.setDataVencimentoAluguel(entity.getDataVencimentoAluguel());
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
}
