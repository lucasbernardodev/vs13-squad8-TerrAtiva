package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoTerrenosCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.entity.EstadosMunicipios;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.EnderecoTerrenosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoTerrenosService {

    private final EnderecoTerrenosRepository enderecoTerrenosRepository;
    private final EstadoMunicipiosService estadoMunicipiosService;


    public EnderecoTerrenos adicionarEnderecoTerrenos(RequestEnderecoTerrenosCreateDTO requestEnderecoTerrenos) throws RegraDeNegocioException {
        estadoMunicipiosService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE());
        EnderecoTerrenos enderecoTerrenos = EnderecoTerrenosMapper.RequestEnderecoTerrenosParaEnderecoTerrenos(requestEnderecoTerrenos);
        enderecoTerrenos.setCodIBGE(estadoMunicipiosService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE()));
        return enderecoTerrenosRepository.save(enderecoTerrenos);
    }

    public EnderecoTerrenos alterar(RequestEnderecoTerrenosCreateDTO requestEnderecoTerrenos) throws RegraDeNegocioException {
        EstadosMunicipios estadosMunicipios = estadoMunicipiosService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE());
        EnderecoTerrenos enderecoTerrenos = EnderecoTerrenosMapper.RequestEnderecoTerrenosParaEnderecoTerrenos(requestEnderecoTerrenos);
        enderecoTerrenos.setCodIBGE(estadosMunicipios);
        return enderecoTerrenosRepository.save(enderecoTerrenos);
    }

//    public ResponseEnderecoTerrenosDTO resgatarPorId(Integer id){
//        return EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.getById(id));
//    }

    public ResponseEnderecoTerrenosDTO resgatarPorId(Integer id) throws EntityNotFoundException {
        Optional<EnderecoTerrenos> enderecoTerrenosOptional = enderecoTerrenosRepository.findById(id);
        EnderecoTerrenos enderecoTerrenos = enderecoTerrenosOptional.orElseThrow(() -> new EntityNotFoundException("EnderecoTerrenos não encontrado com o ID: " + id));
        return EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenos);
    }


}
