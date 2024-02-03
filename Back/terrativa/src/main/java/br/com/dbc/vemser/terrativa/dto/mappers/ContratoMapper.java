package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;

public class ContratoMapper {

    public static ResponseContratoDTO contratoParaResponseContrato(Contrato entity) {
        ResponseContratoDTO dto = new ResponseContratoDTO();
        dto.setId(entity.getId());
        dto.setAtivo(entity.getAtivo());
        dto.setDataAssinatura(entity.getDataAssinatura());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFinal(entity.getDataFinal());
        dto.setDataVencimentoAluguel(entity.getDataVencimentoAluguel());
        return dto;
    }

    public static Contrato requestContratoParaContrato(RequestContratoCreateDTO dto) {
        Contrato entity = new Contrato();
        entity.setId(dto.getId());
        entity.setLocatarioID(dto.getLocatarioID());
        entity.setLocatario(dto.getLocatario());
        entity.setTerrenoID(dto.getTerrenoID());
        entity.setTerreno(dto.getTerreno());
        entity.setAtivo("S");
        entity.setDataAssinatura(dto.getDataAssinatura());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFinal(dto.getDataFinal());
        entity.setDataVencimentoAluguel(dto.getDataVencimentoAluguel());
        return entity;
    }

    public static ResponseContratoRelatorioDTO responseContratoRelatorioDTO(Contrato entity){
        ResponseContratoRelatorioDTO dto = new ResponseContratoRelatorioDTO();
        dto.setIdContrato(entity.getId());
        dto.setAtivo(entity.getAtivo());
        dto.setDataAssinatura(entity.getDataAssinatura());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFinal(entity.getDataFinal());
        dto.setDataVencimentoAluguel(entity.getDataVencimentoAluguel());
        dto.setNomeLocatario(entity.getLocatario().getNome());
        dto.setSobrenomeLocatario(entity.getLocatario().getSobrenome());
        dto.setEmailLocatario(entity.getLocatario().getEmail());
        dto.setCpfLocatario(entity.getLocatario().getCpf());
        dto.setDataNascimentoLocatario(entity.getLocatario().getDataNascimento());
        dto.setSexoLocatario(entity.getLocatario().getSexo());
        dto.setCelularLocatario(entity.getLocatario().getCelular());
        dto.setTelefoneFixoLocatario(entity.getLocatario().getTelefoneFixo());
        dto.setIdTerreno(entity.getTerreno().getId());
        dto.setPreco(entity.getTerreno().getPreco());
        dto.setTamanho(entity.getTerreno().getTamanho());
        dto.setNomeDono(entity.getTerreno().getDono().getNome());
        dto.setSobrenomeDono(entity.getTerreno().getDono().getSobrenome());
        dto.setEmailDono(entity.getTerreno().getDono().getEmail());
        dto.setCpfDono(entity.getTerreno().getDono().getCpf());
        dto.setDataNascimentoDono(entity.getTerreno().getDono().getDataNascimento());
        dto.setSexoDono(entity.getTerreno().getDono().getSexo());
        dto.setCelularDono(entity.getTerreno().getDono().getCelular());
        dto.setTelefoneFixoDono(entity.getTerreno().getDono().getTelefoneFixo());
        dto.setLogradouro(entity.getTerreno().getEnderecoTerrenoID().getLogradouro());
        dto.setNumero(entity.getTerreno().getEnderecoTerrenoID().getNumero());
        dto.setComplemento(entity.getTerreno().getEnderecoTerrenoID().getComplemento());
        dto.setBairro(entity.getTerreno().getEnderecoTerrenoID().getBairro());
        dto.setCep(entity.getTerreno().getEnderecoTerrenoID().getCep());
        dto.setLocalizacao(entity.getTerreno().getEnderecoTerrenoID().getLocalizacao());
        dto.setNomeEstado(entity.getTerreno().getEnderecoTerrenoID().getCodIBGE().getNomeEstado());
        dto.setNomeMunicipio(entity.getTerreno().getEnderecoTerrenoID().getCodIBGE().getNomeMunicipio());
        return dto;
    }
}
