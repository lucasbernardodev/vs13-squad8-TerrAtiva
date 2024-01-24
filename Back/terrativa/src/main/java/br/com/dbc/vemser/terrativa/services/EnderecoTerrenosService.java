package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.RequestEnderecoTerrenos;
import br.com.dbc.vemser.terrativa.dto.ResponseEnderecoTerrenos;
import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.repository.EnderecoTerrenosRepository;
import br.com.dbc.vemser.terrativa.util.validar.ValidarModel;
import org.springframework.stereotype.Service;

@Service
public class EnderecoTerrenosService {
    private final EnderecoTerrenosRepository enderecoTerrenosRepository;
    private final EstadosMunicipioService estadosMunicipioService;

    public EnderecoTerrenosService(EnderecoTerrenosRepository enderecoTerrenosRepository, EstadosMunicipioService estadosMunicipioService) {
        this.enderecoTerrenosRepository = enderecoTerrenosRepository;
        this.estadosMunicipioService = estadosMunicipioService;
    }

    public ResponseEnderecoTerrenos adicionarEnderecoTerrenos(RequestEnderecoTerrenos requestEnderecoTerrenos) throws Exception{
        estadosMunicipioService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE());
        EnderecoTerrenos enderecoTerrenos = EnderecoTerrenosMapper.RequestEnderecoTerrenosParaEnderecoTerrenos(requestEnderecoTerrenos);
        ResponseEnderecoTerrenos responseEnderecoTerrenos = EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.adicionar(enderecoTerrenos));
        return responseEnderecoTerrenos;
    }

    public ResponseEnderecoTerrenos alterar(Integer id, RequestEnderecoTerrenos requestEnderecoTerrenos) throws Exception{
        estadosMunicipioService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE());
        EnderecoTerrenos enderecoTerrenos = EnderecoTerrenosMapper.RequestEnderecoTerrenosParaEnderecoTerrenos(requestEnderecoTerrenos);
        ResponseEnderecoTerrenos responseEnderecoTerrenos = EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.alterar(id, enderecoTerrenos));
        return responseEnderecoTerrenos;
    }

    public void deletar(Integer id) throws Exception{
        enderecoTerrenosRepository.deletar(id);
    }

    public ResponseEnderecoTerrenos resgatarPorId(Integer id) throws Exception{
        ResponseEnderecoTerrenos responseEnderecoTerrenos = EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.resgatarDadosPorId(id));
        return responseEnderecoTerrenos;
    }
}
