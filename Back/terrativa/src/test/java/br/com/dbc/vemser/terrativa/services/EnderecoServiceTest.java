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
//        Endereco enderecoMock = Entidades.retornaEndereco();
//        enderecoMock.setCodIBGE(Entidades.retornaEstadosMunicipios());
//
//        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoMock);
//
//        // When
//        ResponseEnderecoDTO result = enderecoService.adicionarEndereco(request);
//
//        // Then
//        assertEquals(EnderecoMapper.EnderecoParaResponseEndereco(enderecoMock), result);
    }

    @Test
    void alterar() throws RegraDeNegocioException {
        // Given
        Usuario usuario = new Usuario();
        RequestEnderecoCreateDTO request = retornaRequestEnderecoCreateDTOMock();
        Endereco enderecoMock = EnderecoMapper.RequestEnderecoParaEndereco(request);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoMock);

        // When
        ResponseEnderecoDTO result = enderecoService.alterar(usuario, request);

        // Then
        assertEquals(EnderecoMapper.EnderecoParaResponseEndereco(enderecoMock), result);
    }

    public RequestEnderecoCreateDTO retornaRequestEnderecoCreateDTOMock() {
        return new RequestEnderecoCreateDTO(1, 1, "Avenida Paulista", 1500,
                "Apto 208", "Centro", 1100015, 90900000);
    }

}