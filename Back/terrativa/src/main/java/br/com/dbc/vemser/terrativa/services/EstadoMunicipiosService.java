package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.EstadosMunicipios;
import br.com.dbc.vemser.terrativa.repository.EstadoMunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoMunicipiosService {

    private final EstadoMunicipioRepository estadoMunicipioRepository;

    public EstadosMunicipios buscarCodIBGE(Integer id){

        return estadoMunicipioRepository.findById(id).orElseThrow(()-> new RuntimeException("Estado não encontrado"));
    }
}
