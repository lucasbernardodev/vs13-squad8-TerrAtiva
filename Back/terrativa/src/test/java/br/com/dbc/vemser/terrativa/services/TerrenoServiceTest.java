package br.com.dbc.vemser.terrativa.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.dbc.vemser.terrativa.services.Entidades.retornaEnderecoTerreno;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TerrenoServiceTest {
    @InjectMocks
    private TerrenoService terrenoService;

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
    SessaoUsuarioService sessaoUsuarioService;

    private static final Integer PROPRIETARIO_ID = 1;
    private static final String NOT_FOUND_MESSAGE_USUARIO = "Usuário não encontrado";
    private static final String NOT_FOUND_MESSAGE_INATIVO = "Usuário inativo";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste buscar terreno")
    public void testBuscarTerreno() throws RegraDeNegocioException {
        Terreno terreno = new Terreno();
        terreno.setId(1);
        terreno.setDisponivel("S");
        terreno.setEnderecoID(1);

        when(terrenoRepository.findById(1)).thenReturn(Optional.of(terreno));
        when(enderecoTerrenosService.resgatarPorId(1)).thenReturn(null);

        ResponseTerrenoDTO responseTerreno = terrenoService.buscarTerreno(1);

        assertEquals(terreno.getId(), responseTerreno.getId());
    }



    @Test
    @DisplayName("Teste deletar terreno com sucesso")
    public void testDeletarTerrenoSucesso() throws RegraDeNegocioException {
        Terreno terreno = new Terreno();
        terreno.setId(1);
        terreno.setDisponivel("S");
        terreno.setEnderecoID(1);

        when(terrenoRepository.findById(1)).thenReturn(Optional.of(terreno));
        doNothing().when(terrenoRepository).delete(terreno);

        terrenoService.deletarTerreno(1);

        verify(terrenoRepository).delete(terreno);

    }


}







