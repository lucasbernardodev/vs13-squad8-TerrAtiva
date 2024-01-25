package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.RequestEnderecoTerrenosCreateDTO;
import br.com.dbc.vemser.terrativa.dto.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.repository.EnderecoTerrenosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoTerrenosService {
    private final EnderecoTerrenosRepository enderecoTerrenosRepository;
    private final EstadosMunicipioService estadosMunicipioService;


    public ResponseEnderecoTerrenosDTO adicionarEnderecoTerrenos(RequestEnderecoTerrenosCreateDTO requestEnderecoTerrenos) throws Exception{
        estadosMunicipioService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE());
        EnderecoTerrenos enderecoTerrenos = EnderecoTerrenosMapper.RequestEnderecoTerrenosParaEnderecoTerrenos(requestEnderecoTerrenos);
        ResponseEnderecoTerrenosDTO responseEnderecoTerrenos = EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.adicionar(enderecoTerrenos));
        return responseEnderecoTerrenos;
    }

    public ResponseEnderecoTerrenosDTO alterar(Integer id, RequestEnderecoTerrenosCreateDTO requestEnderecoTerrenos) throws Exception{
        estadosMunicipioService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE());
        EnderecoTerrenos enderecoTerrenos = EnderecoTerrenosMapper.RequestEnderecoTerrenosParaEnderecoTerrenos(requestEnderecoTerrenos);
        ResponseEnderecoTerrenosDTO responseEnderecoTerrenos = EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.alterar(id, enderecoTerrenos));
        return responseEnderecoTerrenos;
    }

    public void deletar(Integer id) throws Exception{
        enderecoTerrenosRepository.deletar(id);
    }

    public ResponseEnderecoTerrenosDTO resgatarPorId(Integer id) throws Exception{
        ResponseEnderecoTerrenosDTO responseEnderecoTerrenos = EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.resgatarDadosPorId(id));
        return responseEnderecoTerrenos;
    }
}
