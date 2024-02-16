package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoTerrenosCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.entity.EstadosMunicipios;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.EnderecoTerrenosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoTerrenosServiceTest {

    @Mock
    private EnderecoTerrenosRepository enderecoTerrenosRepository;

    @Mock
    private EstadoMunicipiosService estadoMunicipiosService;

    @InjectMocks
    private EnderecoTerrenosService enderecoTerrenosService;

    @Test
    @DisplayName("Adicionar endereco terrenos com sucesso")
    public void adicionarEnderecoTerreno() throws RegraDeNegocioException {
        //GIVEN
        RequestEnderecoTerrenosCreateDTO requestEnderecoTerrenosCreateDTOMock = retornaRequestEnderecoTerrenosCreateDTO();
        EnderecoTerrenos enderecoTerrenosMock = retornaEnderecoTerrenos();

        when(estadoMunicipiosService.buscarCodIBGE(any())).thenReturn(new EstadosMunicipios());
        when(enderecoTerrenosRepository.save(any())).thenReturn(enderecoTerrenosMock);

        //WHEN
        EnderecoTerrenos enderecoTerrenos = enderecoTerrenosService.adicionarEnderecoTerrenos(requestEnderecoTerrenosCreateDTOMock);

        //THEN
        assertNotNull(enderecoTerrenos);
        assertEquals(enderecoTerrenosMock, enderecoTerrenos);
    }

    @Test
    @DisplayName("Alterar endereco terrenos com sucesso")
    public void alterarEnderecoTerreno() throws RegraDeNegocioException {
        //GIVEN
        RequestEnderecoTerrenosCreateDTO requestEnderecoTerrenosCreateDTOMock = retornaRequestEnderecoTerrenosCreateDTO();
        EnderecoTerrenos enderecoTerrenosMock = retornaEnderecoTerrenos();

        when(estadoMunicipiosService.buscarCodIBGE(any())).thenReturn(new EstadosMunicipios());
        when(enderecoTerrenosRepository.save(any())).thenReturn(enderecoTerrenosMock);

        //WHEN
        EnderecoTerrenos enderecoTerrenos = enderecoTerrenosService.alterar(requestEnderecoTerrenosCreateDTOMock);

        //THEN
        assertNotNull(enderecoTerrenos);
        assertEquals(enderecoTerrenosMock, enderecoTerrenos);
    }

    @Test
    @DisplayName("Resgatar endereco terrenos por id com sucesso")
    public void resgatarEnderecoTerrenoPorId() {
        //GIVEN
        EnderecoTerrenos enderecoTerrenosMock = retornaEnderecoTerrenos();
        ResponseEnderecoTerrenosDTO responseEnderecoTerrenosDTOMock = retornaResponseEnderecoTerrenosDTO();

        when(enderecoTerrenosRepository.findById(any())).thenReturn(Optional.of(enderecoTerrenosMock));

        //WHEN
        ResponseEnderecoTerrenosDTO responseEnderecoTerrenosDTO = enderecoTerrenosService.resgatarPorId(1);

        //THEN
        assertNotNull(responseEnderecoTerrenosDTO);
        assertEquals(responseEnderecoTerrenosDTOMock, responseEnderecoTerrenosDTO);

    }

    private RequestEnderecoTerrenosCreateDTO retornaRequestEnderecoTerrenosCreateDTO() {
        RequestEnderecoTerrenosCreateDTO request = new RequestEnderecoTerrenosCreateDTO();
        request.setCodigoMunicipioIBGE(1112222);
        request.setCep(19180000);
        request.setComplemento("Casa");
        request.setLogradouro("Rua Teste");
        request.setNumero(123);

        return request;
    }

    private EnderecoTerrenos retornaEnderecoTerrenos() {
        EnderecoTerrenos enderecoTerrenos = new EnderecoTerrenos();
        enderecoTerrenos.setCep(19180000);
        enderecoTerrenos.setComplemento("Casa");
        enderecoTerrenos.setLogradouro("Rua Teste");
        enderecoTerrenos.setNumero(123);

        return enderecoTerrenos;
    }

    private ResponseEnderecoTerrenosDTO retornaResponseEnderecoTerrenosDTO() {
        ResponseEnderecoTerrenosDTO response = new ResponseEnderecoTerrenosDTO();
        response.setCep(19180000);
        response.setComplemento("Casa");
        response.setLogradouro("Rua Teste");
        response.setNumero(123);

        return response;
    }

}