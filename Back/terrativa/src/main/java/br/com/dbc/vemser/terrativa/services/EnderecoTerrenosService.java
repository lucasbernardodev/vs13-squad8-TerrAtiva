package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoTerrenosCreateDTO;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.repository.EnderecoTerrenosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoTerrenosService {
    private final EnderecoTerrenosRepository enderecoTerrenosRepository;
    private final EstadoMunicipiosService estadoMunicipiosService;


    public ResponseEnderecoTerrenosDTO adicionarEnderecoTerrenos(RequestEnderecoTerrenosCreateDTO requestEnderecoTerrenos) {
        estadoMunicipiosService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE());
        EnderecoTerrenos enderecoTerrenos = EnderecoTerrenosMapper.RequestEnderecoTerrenosParaEnderecoTerrenos(requestEnderecoTerrenos);
        return EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.save(enderecoTerrenos));
    }

    //TODO: verificar o que o buscarCodIBGE está fazendo
    public ResponseEnderecoTerrenosDTO alterar(Integer id, RequestEnderecoTerrenosCreateDTO requestEnderecoTerrenos) throws Exception {
        estadoMunicipiosService.buscarCodIBGE(requestEnderecoTerrenos.getCodigoMunicipioIBGE());

        EnderecoTerrenos enderecoTerrenos = EnderecoTerrenosMapper.RequestEnderecoTerrenosParaEnderecoTerrenos(requestEnderecoTerrenos);

        enderecoTerrenos.setId(id);
        enderecoTerrenos = enderecoTerrenosRepository.save(enderecoTerrenos);

        return EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenos);
    }

    public void deletar(Integer id) {
        enderecoTerrenosRepository.deleteById(id);
    }

    public ResponseEnderecoTerrenosDTO resgatarPorId(Integer id) {
        return EnderecoTerrenosMapper.EnderecoTerrenosParaResponseEnderecoTerrenos(enderecoTerrenosRepository.getById(id));
    }
}
