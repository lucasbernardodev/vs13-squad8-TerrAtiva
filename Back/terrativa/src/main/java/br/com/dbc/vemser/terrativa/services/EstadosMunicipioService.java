package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.EstadosMunicipios;
import br.com.dbc.vemser.terrativa.repository.EstadoMunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadosMunicipioService {

    private final EstadoMunicipioRepository estadoMunicipioRepository;

    public EstadosMunicipios buscarCodIBGE(Integer id){
        return estadoMunicipioRepository.buscarCodIBGE(id);
    }
}
