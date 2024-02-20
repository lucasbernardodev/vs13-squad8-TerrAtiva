package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.EstadosMunicipios;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.EstadoMunicipioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DisplayName("EstadoMunicipiosService - Test")
class EstadoMunicipiosServiceTest {

    @Mock
    private EstadoMunicipioRepository estadoMunicipioRepository;

    @InjectMocks
    private EstadoMunicipiosService estadoMunicipiosService;

    @Test
    @DisplayName("Deve retornar Estados e Municipios atráves do ID informado")
    public void retornaCodIbge() throws RegraDeNegocioException {
        Optional<EstadosMunicipios> estadosMunicipiosMock = Optional.of(Entidades.retornaEstadosMunicipios());

        when(estadoMunicipioRepository.findById(anyInt())).thenReturn(estadosMunicipiosMock);

        EstadosMunicipios estadosMunicipios = estadoMunicipiosService.buscarCodIBGE(new Random().nextInt());

        assertEquals(estadosMunicipios, estadosMunicipiosMock.get());
    }

    @Test
    @DisplayName("Deve retornar uma RegraDeNegocioException caso o estado não seja encontrado")
    public void retornaExceptionEstadoNaoEncontrado(){

        Integer idMock = new Random().nextInt();

        assertThrows(RegraDeNegocioException.class, () -> estadoMunicipiosService.buscarCodIBGE(idMock));
    }

}