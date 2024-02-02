package br.com.dbc.vemser.terrativa.dto.mappers;

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
}
