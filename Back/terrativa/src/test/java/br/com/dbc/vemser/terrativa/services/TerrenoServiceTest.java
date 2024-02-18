package br.com.dbc.vemser.terrativa.services;


import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.Endereco;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.Mockito.*;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoTerrenosCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoUpdateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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


    @Test
    @DisplayName("Testar cadastrar um terreno")
    public void cadastrarTerreno() throws RegraDeNegocioException {
        RequestTerrenoCreateDTO requestTerrenoCreateDTO = new RequestTerrenoCreateDTO();
        requestTerrenoCreateDTO.setProprietarioID(1);
        requestTerrenoCreateDTO.setTitulo("Terreno para Compra");
        requestTerrenoCreateDTO.setDescricao("Descrição do Terreno");

        RequestEnderecoTerrenosCreateDTO enderecoTerrenoCreateDTO = new RequestEnderecoTerrenosCreateDTO();
        enderecoTerrenoCreateDTO.setLogradouro("Avenida Paulista");
        enderecoTerrenoCreateDTO.setNumero(123);
        enderecoTerrenoCreateDTO.setBairro("Jardins");
        enderecoTerrenoCreateDTO.setCodigoMunicipioIBGE(12345);

        requestTerrenoCreateDTO.setEndereco(enderecoTerrenoCreateDTO);

        Usuario proprietario = Entidades.retornaUsuario();
        proprietario.setUsuarioId(1);

        Terreno terrenoSalvo = Entidades.retornaTerreno();
        terrenoSalvo.setId(1);

        ResponseTerrenoDTO responseTerrenoDTO = Entidades.retronaResponseTerrenoDTO();
        responseTerrenoDTO.setId(1);

        when(usuarioRepository.findById(requestTerrenoCreateDTO.getProprietarioID())).thenReturn(Optional.of(proprietario));
        when(enderecoTerrenosService.adicionarEnderecoTerrenos(enderecoTerrenoCreateDTO)).thenReturn(Entidades.retornaEnderecoTerreno());
        when(terrenoRepository.save(any(Terreno.class))).thenReturn(terrenoSalvo);

        ResponseTerrenoDTO responseTerreno = terrenoService.cadastrarTerreno(requestTerrenoCreateDTO);

        assertEquals(responseTerrenoDTO, responseTerreno);
    }


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


    @Test
    @DisplayName("Não consegue alterar o terreno e joga uma exception")
    public void retornaExceptionAoAlterarTerreno() {
        Integer idTerreno = null;
        RequestTerrenoUpdateDTO requestTerrenoUpdateDTO = new RequestTerrenoUpdateDTO();

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.alterarTerreno(idTerreno, requestTerrenoUpdateDTO));
    }

    @Test
    @DisplayName("Não consegue alterar o terreno pois não está disponível e joga uma exception")
    public void retornaExceptionTerrenoIndisponivelAoAlterar() {
        Integer idTerreno = 1;
        RequestTerrenoUpdateDTO requestTerrenoUpdateDTO = new RequestTerrenoUpdateDTO();

        Terreno terreno = Entidades.retornaTerreno();
        terreno.setId(idTerreno);
        terreno.setDisponivel("N");

        when(terrenoRepository.findById(idTerreno)).thenReturn(Optional.of(terreno));

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.alterarTerreno(idTerreno, requestTerrenoUpdateDTO));
    }




    @Test
    @DisplayName("Deve lançar uma exceção ao tentar alterar um terreno indisponível")
    public void testAlterarTerreno() throws RegraDeNegocioException {
        // Configuração do mock
        Terreno terreno = new Terreno();
        terreno.setId(1);
        Usuario donoTerreno = new Usuario();
        donoTerreno.setUsuarioId(1); // ID de usuário fictício
        terreno.setDono(donoTerreno);
        terreno.setDisponivel("S");

        RequestTerrenoUpdateDTO requestTerreno = new RequestTerrenoUpdateDTO();
        requestTerreno.setId(1);

        // Configuração de comportamento do mock
        when(terrenoRepository.findById(1)).thenReturn(java.util.Optional.of(terreno));
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(1); // ID de usuário fictício
        when(usuarioRepository.findById(1)).thenReturn(java.util.Optional.of(donoTerreno));

        // Execução do método a ser testado
        ResponseTerrenoDTO response = terrenoService.alterarTerreno(1, requestTerreno);

        // Verificação do resultado
        Assertions.assertNotNull(response);

        verify(terrenoRepository, times(1)).save(terreno);



    }

















    @Test
    @DisplayName("Não consegue arrendar o terreno pois é o proprietário e joga uma exception")
    public void retornaExceptionAoArrendarTerrenoProprio() {
        Integer idTerreno = 1;
        RequestContratoCreateDTO contrato = criarContratoComMesmoProprietario();

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.arrendarTerreno(idTerreno, contrato));
    }

    @Test
    @DisplayName("Não consegue arrendar o terreno pois o locatário está inativo e joga uma exception")
    public void retornaExceptionLocatarioInativoAoArrendar() {
        Integer idTerreno = 1;
        RequestContratoCreateDTO contrato = criarContratoComLocatarioInativo();

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.arrendarTerreno(idTerreno, contrato));
    }


    private RequestContratoCreateDTO criarContratoComLocatarioInativo() {
        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();
        return contrato;
    }

    private RequestContratoCreateDTO criarContratoValido() {
        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();
        return contrato;
    }

    private RequestContratoCreateDTO criarContratoComMesmoProprietario() {
        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();
        return contrato;
    }

}







