package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedUsuariosServiceTest {

    @Mock
    private TerrenoRepository terrenoRepository;

    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private EnderecoTerrenosService enderecoTerrenosService;

    @InjectMocks
    private FeedUsuariosService feedUsuariosService;

    @Test
    @DisplayName("Teste mostrar terrenos disponiveis")
    public void mostrarTerrenosDisponiveis() {

        when(terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("S", 1)).thenReturn(criarListaDeTerrenos());
        when(enderecoTerrenosService.resgatarPorId(null)).thenReturn(criarEnderecoDTO());

        List<ResponseTerrenoDTO> responseTerrenos = feedUsuariosService.mostrarTerrenosDisponiveis(1);

        assertEquals(1, responseTerrenos.size());
    }

    @Test
    @DisplayName("Teste mostrar terrenos do usuario")
    public void mostrarTerrenosDoUsuario() {

        List<Terreno> listaDeTerrenos = criarListaDeTerrenos();

        when(terrenoRepository.findAllByProprietarioID(anyInt())).thenReturn(listaDeTerrenos);
        when(enderecoTerrenosService.resgatarPorId(null)).thenReturn(criarEnderecoDTO());

        List<ResponseTerrenoDTO> responseTerrenos = feedUsuariosService.mostrarTerrenosDoUsuario(1);

        assertNotNull(responseTerrenos);
        assertEquals(listaDeTerrenos.size(), responseTerrenos.size());
    }

    @Test
    @DisplayName("Teste para mostrar terrenos alugados")
    public void testMostrarTerrenosAlugados() throws RegraDeNegocioException {
        List<Terreno> terrenos = criarListaDeTerrenos();
        List<Contrato> contratos = criarListaDeContratos();

        when(contratoRepository.findAllByLocatarioID(anyInt())).thenReturn(contratos);
        when(terrenoRepository.findById(any())).thenReturn(Optional.of(terrenos.get(0)));
        when(enderecoTerrenosService.resgatarPorId(any())).thenReturn(criarEnderecoDTO());

        List<ResponseTerrenoDTO> responseTerrenos = feedUsuariosService.mostrarTerrenosAlugados(1);

        assertEquals(1, responseTerrenos.size());
    }

    @Test
    @DisplayName("Teste mostrar terrenos arrendados")
    public void mostrarTerrenosArrendados() {

        when(terrenoRepository.findAllByDisponivelEqualsAndProprietarioID("N", 1)).thenReturn(criarListaDeTerrenos());
        when(enderecoTerrenosService.resgatarPorId(any())).thenReturn(criarEnderecoDTO());

        FeedUsuariosService feedUsuariosService = new FeedUsuariosService(terrenoRepository, contratoRepository, enderecoTerrenosService);

        List<ResponseTerrenoDTO> responseTerrenos = feedUsuariosService.mostrarTerrenosArrendados(1);

        assertEquals(1, responseTerrenos.size());
    }


    private List<Terreno> criarListaDeTerrenos() {
        List<Terreno> terrenos = new ArrayList<>();
        Terreno terreno = new Terreno();
        terrenos.add(terreno);
        return terrenos;
    }

    private ResponseEnderecoTerrenosDTO criarEnderecoDTO() {
        return new ResponseEnderecoTerrenosDTO();
    }

    private Contrato criarContratoEntity() {
        Contrato contrato = new Contrato();
        contrato.setId(1);
        contrato.setLocatarioID(1);
        contrato.setAtivo("S");

        return contrato;
    }

    private List<Contrato> criarListaDeContratos() {
        List<Contrato> contratos = new ArrayList<>();
        Contrato contrato = criarContratoEntity();
        contratos.add(contrato);
        return contratos;
    }


}
