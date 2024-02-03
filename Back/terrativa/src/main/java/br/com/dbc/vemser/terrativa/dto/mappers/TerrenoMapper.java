package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoUpdateDTO;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import org.springframework.stereotype.Component;

@Component
public class TerrenoMapper {

    public final Terreno requestTerrenoParaTerreno(RequestTerrenoCreateDTO requestTerreno){
        Terreno terreno = new Terreno();
        terreno.setId(requestTerreno.getId());
        terreno.setTitulo(requestTerreno.getTitulo());
        terreno.setDescricao(requestTerreno.getDescricao());
        terreno.setProprietarioID(requestTerreno.getProprietarioID());
        terreno.setEnderecoID(requestTerreno.getEnderecoID());
        terreno.setPreco(requestTerreno.getPreco());
        terreno.setTamanho(requestTerreno.getTamanho());
        terreno.setDisponivel(requestTerreno.getDisponivel());
        return terreno;
    }
    public final Terreno requestTerrenoParaTerreno(RequestTerrenoUpdateDTO requestTerreno){
        Terreno terreno = new Terreno();
        terreno.setId(requestTerreno.getId());
        terreno.setTitulo(requestTerreno.getTitulo());
        terreno.setDescricao(requestTerreno.getDescricao());
        terreno.setProprietarioID(requestTerreno.getProprietarioID());
        terreno.setEnderecoID(requestTerreno.getEnderecoID());
        terreno.setPreco(requestTerreno.getPreco());
        terreno.setTamanho(requestTerreno.getTamanho());
        terreno.setDisponivel(requestTerreno.getDisponivel());
        return terreno;
    }

    public final ResponseTerrenoDTO terrenoParaResponseTerreno(Terreno terreno){
        ResponseTerrenoDTO responseTerreno = new ResponseTerrenoDTO();
        responseTerreno.setId(terreno.getId());
        responseTerreno.setTitulo(terreno.getTitulo());
        responseTerreno.setDescricao(terreno.getDescricao());
        responseTerreno.setProprietarioID(terreno.getProprietarioID());
        responseTerreno.setEnderecoID(terreno.getEnderecoID());
        responseTerreno.setPreco(terreno.getPreco());
        responseTerreno.setTamanho(terreno.getTamanho());
        responseTerreno.setDisponivel(terreno.getDisponivel());
        return responseTerreno;
    }

    public final ResponseFeedDTO terrenoToFeedDTO(Terreno terreno) {
        ResponseFeedDTO responseTerreno = new ResponseFeedDTO();
        responseTerreno.setTerrenoId(terreno.getId());
        responseTerreno.setTitulo(terreno.getTitulo());
        responseTerreno.setDescricao(terreno.getDescricao());
        responseTerreno.setPropietario(terreno.getDono().getNome() + " " + terreno.getDono().getSobrenome());
        responseTerreno.setPreco(terreno.getPreco());
        responseTerreno.setTamanho(terreno.getTamanho());
        if (terreno.getCriado() != null) {
            responseTerreno.setCriado(terreno.getCriado().toString());
        }
        responseTerreno.setLogradouro(terreno.getEnderecoTerrenoID().getLogradouro());
        responseTerreno.setNumero(terreno.getEnderecoTerrenoID().getNumero());
        responseTerreno.setComplemento(terreno.getEnderecoTerrenoID().getComplemento());
        responseTerreno.setBairro(terreno.getEnderecoTerrenoID().getBairro());
        responseTerreno.setCep(terreno.getEnderecoTerrenoID().getCep());
        responseTerreno.setLocalizacao(terreno.getEnderecoTerrenoID().getLocalizacao());
        responseTerreno.setCidade(terreno.getEnderecoTerrenoID().getCodIBGE().getNomeMunicipio());
        responseTerreno.setEstado(terreno.getEnderecoTerrenoID().getCodIBGE().getNomeEstado());

        return responseTerreno;
    }
}
