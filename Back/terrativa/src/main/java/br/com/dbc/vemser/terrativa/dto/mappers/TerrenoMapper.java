package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.RequestTerreno;
import br.com.dbc.vemser.terrativa.dto.ResponseTerreno;
import br.com.dbc.vemser.terrativa.entity.Terreno;

public class TerrenoMapper {

    public static Terreno RequestTerrenoParaTerreno(RequestTerreno requestTerreno){
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

    public static ResponseTerreno TerrenoParaResponseTerreno(Terreno terreno){
        ResponseTerreno responseTerreno = new ResponseTerreno();
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
