package br.com.dbc.vemser.terrativa.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoTerrenosCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoUpdateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.dbc.vemser.terrativa.services.Entidades.retornaEnderecoTerreno;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TerrenoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoTerrenosService enderecoTerrenosService;

    @Mock
    private TerrenoRepository terrenoRepository;

    @Mock
    private TerrenoMapper terrenoMapper;

    @Mock
    private EnderecoTerrenosMapper enderecoTerrenosMapper;

    @Mock
    private SessaoUsuarioService sessaoUsuarioService;

    @InjectMocks
    private TerrenoService terrenoService;

    private static final Integer PROPRIETARIO_ID = 1;
    private static final String NOT_FOUND_MESSAGE_USUARIO = "Usuário não encontrado";
    private static final String NOT_FOUND_MESSAGE_INATIVO = "Usuário inativo";

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }


    //Buscar
    @Test
    @DisplayName("Teste buscar terreno")
    public void testBuscarTerrenoComSucesso() throws RegraDeNegocioException {
        Optional<Terreno> terrenoMock = Optional.of(Entidades.retornaTerreno());
        ResponseEnderecoTerrenosDTO enderecoTerrenosMock = Entidades.responseEnderecoTerrenosDTO();
        ResponseTerrenoDTO responseTerrenoMock = Entidades.retronaResponseTerrenoDTO();

        when(terrenoRepository.findById(anyInt())).thenReturn(terrenoMock);
        when(enderecoTerrenosService.resgatarPorId(anyInt())).thenReturn(enderecoTerrenosMock);

        ResponseTerrenoDTO responseTerreno = terrenoService.buscarTerreno(new Random().nextInt());

//        assertTrue(new ReflectionEquals(responseTerreno).matches(Entidades.retronaResponseTerrenoDTO()));
        assertEquals(responseTerreno, responseTerrenoMock);
    }

    @Test
    @DisplayName("Não consegue buscar o terreno e joga uma exception")
    public void retornaExceptionSemTerreno(){
        Integer idMock = null;

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.buscarTerreno(idMock));
    }

    @Test
    @DisplayName("Não consegue buscar o terreno pois não esta disponivel e joga uma exception")
    public void retornaExceptionTerrenoIndisponivel(){
        Optional<Terreno> terrenoMock = Optional.of(Entidades.retornaTerreno());
        terrenoMock.get().setDisponivel("N");

        when(terrenoRepository.findById(anyInt())).thenReturn(terrenoMock);

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.buscarTerreno(new Random().nextInt()));
    }

    //Deletar
    @Test
    @DisplayName("Teste deletar terreno com sucesso")
    public void testeDeletarTerrenoSucesso() throws RegraDeNegocioException {
        Integer idMock = 2;
        Terreno terreno = Entidades.retornaTerreno();

        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idMock);
        when(terrenoRepository.findById(anyInt())).thenReturn(Optional.of(terreno));

        terrenoService.deletarTerreno(new Random().nextInt());

        verify(terrenoRepository, times(1)).save(terreno);
    }

    @Test
    @DisplayName("Não consegue buscar o terreno pois não esta disponivel e joga uma exception")
    public void retornaExceptionTerrenoIndisponivelParaDeletar(){
        Optional<Terreno> terrenoMock = Optional.of(Entidades.retornaTerreno());
        terrenoMock.get().setDisponivel("N");
        Integer idMock = 2;

        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idMock);
        when(terrenoRepository.findById(anyInt())).thenReturn(terrenoMock);

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.deletarTerreno(new Random().nextInt()));
    }

    @Test
    @DisplayName("Usuario que esta tentando deletar não tem permissão")
    public void retornaExceptionUsuarioSemPermissaoParaDeletar(){
        Optional<Terreno> terrenoMock = Optional.of(Entidades.retornaTerreno());
        Integer idMock = 3;

        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idMock);
        when(terrenoRepository.findById(anyInt())).thenReturn(terrenoMock);

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.deletarTerreno(new Random().nextInt()));
    }

    @Test
    @DisplayName("Altera os terrenos de um determinado usuario para indiponíveis quando o usuario é deletado.")
    public void deletaTerrenosQuandoUsuarioDeletado() throws RegraDeNegocioException {
        List<Terreno> listaTerrenosMock = List.of(Entidades.retornaTerreno(), Entidades.retornaTerreno(), Entidades.retornaTerreno(), Entidades.retornaTerreno());

        when(terrenoRepository.findAllByProprietarioID(anyInt())).thenReturn(listaTerrenosMock);
        when(terrenoRepository.save(anyObject())).thenReturn(Entidades.retornaTerreno());

        terrenoService.alterarTerrenosUsuarioDeletado(new Random().nextInt());

        verify(terrenoRepository, times(listaTerrenosMock.size())).save(any(Terreno.class));
    }


}







