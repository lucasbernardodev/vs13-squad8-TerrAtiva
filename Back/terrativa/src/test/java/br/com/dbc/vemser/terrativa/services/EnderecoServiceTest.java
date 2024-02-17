package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.entity.Endereco;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.EnderecoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EnderecoService - Test")
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private EstadoMunicipiosService estadoMunicipioService;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    void resgatarPorId() throws RegraDeNegocioException {
        // Given
        Integer id = 1;
        Optional<Endereco> enderecoMock = Optional.of(Entidades.retornaEnderecoEntityMock());

        // When
        when(enderecoRepository.findEnderecoByUsuarioID(id)).thenReturn(enderecoMock.get());
        ResponseEnderecoDTO result = enderecoService.resgatarPorId(id);

        // Then
        assertEquals(EnderecoMapper.EnderecoParaResponseEndereco(enderecoMock.get()), result);
    }

    @Test
    void adicionarEndereco() throws RegraDeNegocioException {
        // Given
        Optional<Endereco> enderecoMock = Optional.of(Entidades.retornaEnderecoEntityMock());

        // When
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoMock.get());
        ResponseEnderecoDTO result = enderecoService.adicionarEndereco(Entidades.retornaRequestEnderecoCreateDTO());

        // Then
        assertEquals(EnderecoMapper.EnderecoParaResponseEndereco(enderecoMock.get()), result);
    }

    @Test
    void alterar() throws RegraDeNegocioException {
        // Given
        Usuario usuarioMock = Entidades.retornaUsuario();
        RequestEnderecoCreateDTO request = Entidades.retornaRequestEnderecoCreateDTO();
        Endereco enderecoMock = Entidades.retornaEnderecoEntityMock();
        ResponseEnderecoDTO responseEnderecoDTO = EnderecoMapper.EnderecoParaResponseEndereco(enderecoMock);
//        enderecoMock.setCodIBGE(Entidades.retornaEstadosMunicipios());

        // When
        when(estadoMunicipioService.buscarCodIBGE(
                request.getCodigoMunicipioIBGE())).thenReturn(Entidades.retornaEstadosMunicipios());
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoMock);

        ResponseEnderecoDTO result = enderecoService.alterar(usuarioMock, request);

        // Then
        assertEquals(responseEnderecoDTO, result);
    }


}