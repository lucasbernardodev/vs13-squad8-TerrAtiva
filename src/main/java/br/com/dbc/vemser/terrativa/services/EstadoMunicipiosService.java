package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.EstadosMunicipios;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.EstadoMunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoMunicipiosService {

    private final EstadoMunicipioRepository estadoMunicipioRepository;

    private final String NOT_FOUND_MESSAGE = "Estado não encontrado";

    public EstadosMunicipios buscarCodIBGE(Integer id) throws RegraDeNegocioException{

        return estadoMunicipioRepository.findById(id).orElseThrow(()-> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
    }
}
