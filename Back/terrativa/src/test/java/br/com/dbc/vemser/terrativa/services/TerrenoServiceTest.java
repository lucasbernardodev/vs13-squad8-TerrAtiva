package br.com.dbc.vemser.terrativa.services;


import br.com.dbc.vemser.terrativa.dto.requests.*;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static br.com.dbc.vemser.terrativa.services.Entidades.*;
import static org.mockito.Mockito.*;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoTerrenosMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
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
    private ContratoService contratoService;

    @Mock
    private TerrenoRepository terrenoRepository;

    @Mock
    private TerrenoMapper terrenoMapper;

    @Mock
    private MensalidadeService mensalidadeService;

    @Mock
    private EnderecoTerrenosMapper enderecoTerrenosMapper;

    @Mock
    private SessaoUsuarioService sessaoUsuarioService;

    @InjectMocks
    private TerrenoService terrenoService;

    private static final Integer PROPRIETARIO_ID = 1;
    private static final String NOT_FOUND_MESSAGE_USUARIO = "Usuário não encontrado";
    private static final String NOT_FOUND_MESSAGE_INATIVO = "Usuário inativo";
    private static final String NOT_FOUND_MESSAGE_TERRENO_EXIST = "Terreno não existe ou está alugado";



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

        Terreno terrenoSalvo = retornaTerreno();
        terrenoSalvo.setId(1);

        ResponseTerrenoDTO responseTerrenoDTO = Entidades.retronaResponseTerrenoDTO();
        responseTerrenoDTO.setId(1);

        when(usuarioRepository.findById(requestTerrenoCreateDTO.getProprietarioID())).thenReturn(Optional.of(proprietario));
        when(enderecoTerrenosService.adicionarEnderecoTerrenos(enderecoTerrenoCreateDTO)).thenReturn(Entidades.retornaEnderecoTerreno());
        when(terrenoRepository.save(any(Terreno.class))).thenReturn(terrenoSalvo);

        ResponseTerrenoDTO responseTerreno = terrenoService.cadastrarTerreno(requestTerrenoCreateDTO);

        assertEquals(responseTerrenoDTO, responseTerreno);
    }

    @Test
    @DisplayName("Testar se a exceção é lançada quando o usuário está inativo")
    public void testUsuarioInativo() throws RegraDeNegocioException {
        RequestTerrenoCreateDTO requestTerreno = new RequestTerrenoCreateDTO();
        requestTerreno.setProprietarioID(1);

        Usuario usuario = new Usuario();
        usuario.setUsuarioId(1);
        usuario.setAtivo("N");

        when(usuarioRepository.findById(requestTerreno.getProprietarioID())).thenReturn(Optional.of(usuario));

        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
            terrenoService.cadastrarTerreno(requestTerreno);
        });

        String expectedMessage = NOT_FOUND_MESSAGE_INATIVO;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    //Buscar
    @Test
    @DisplayName("Teste buscar terreno")
    public void testBuscarTerrenoComSucesso() throws RegraDeNegocioException {
        Optional<Terreno> terrenoMock = Optional.of(retornaTerreno());
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
        Optional<Terreno> terrenoMock = Optional.of(retornaTerreno());
        terrenoMock.get().setDisponivel("N");

        when(terrenoRepository.findById(anyInt())).thenReturn(terrenoMock);

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.buscarTerreno(new Random().nextInt()));
    }

    //Deletar
    @Test
    @DisplayName("Teste deletar terreno com sucesso")
    public void testeDeletarTerrenoSucesso() throws RegraDeNegocioException {
        Integer idMock = 2;
        Terreno terreno = retornaTerreno();

        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idMock);
        when(terrenoRepository.findById(anyInt())).thenReturn(Optional.of(terreno));

        terrenoService.deletarTerreno(new Random().nextInt());

        verify(terrenoRepository, times(1)).save(terreno);
    }

    @Test
    @DisplayName("Não consegue buscar o terreno pois não esta disponivel e joga uma exception")
    public void retornaExceptionTerrenoIndisponivelParaDeletar(){
        Optional<Terreno> terrenoMock = Optional.of(retornaTerreno());
        terrenoMock.get().setDisponivel("N");
        Integer idMock = 2;

        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idMock);
        when(terrenoRepository.findById(anyInt())).thenReturn(terrenoMock);

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.deletarTerreno(new Random().nextInt()));
    }

    @Test
    @DisplayName("Usuario que esta tentando deletar não tem permissão")
    public void retornaExceptionUsuarioSemPermissaoParaDeletar(){
        Optional<Terreno> terrenoMock = Optional.of(retornaTerreno());
        Integer idMock = 3;

        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idMock);
        when(terrenoRepository.findById(anyInt())).thenReturn(terrenoMock);

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.deletarTerreno(new Random().nextInt()));
    }

    @Test
    @DisplayName("Altera os terrenos de um determinado usuario para indiponíveis quando o usuario é deletado.")
    public void deletaTerrenosQuandoUsuarioDeletado() throws RegraDeNegocioException {
        List<Terreno> listaTerrenosMock = List.of(retornaTerreno(), retornaTerreno(), retornaTerreno(), retornaTerreno());

        when(terrenoRepository.findAllByProprietarioID(anyInt())).thenReturn(listaTerrenosMock);
        when(terrenoRepository.save(anyObject())).thenReturn(retornaTerreno());

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
    @DisplayName("Testar a alteração de um terreno")
    public void testAlterarTerreno() throws RegraDeNegocioException {
        Integer idTerreno = 1;
        RequestTerrenoUpdateDTO requestTerreno = new RequestTerrenoUpdateDTO();
        requestTerreno.setTitulo("Novo título");
        requestTerreno.setDescricao("Nova descrição");

        RequestEnderecoTerrenosCreateDTO endereco = new RequestEnderecoTerrenosCreateDTO();
        requestTerreno.setEndereco(endereco);

        Usuario dono = new Usuario();
        dono.setUsuarioId(1);

        Terreno terrenoExistente = new Terreno();
        terrenoExistente.setId(idTerreno);
        terrenoExistente.setDono(dono);
        terrenoExistente.setDisponivel("S");

        EnderecoTerrenos enderecoTerrenos = new EnderecoTerrenos();
        enderecoTerrenos.setId(idTerreno);

        Terreno terrenoAtualizado = new Terreno();
        terrenoAtualizado.setId(idTerreno);
        terrenoAtualizado.setTitulo(requestTerreno.getTitulo());
        terrenoAtualizado.setDescricao(requestTerreno.getDescricao());
        terrenoAtualizado.setEnderecoTerrenoID(enderecoTerrenos);

        when(terrenoRepository.findById(idTerreno)).thenReturn(Optional.of(terrenoExistente));
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(dono.getUsuarioId());
        when(enderecoTerrenosService.alterar(any(RequestEnderecoTerrenosCreateDTO.class))).thenReturn(enderecoTerrenos);
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(dono));
        when(terrenoRepository.save(any(Terreno.class))).thenReturn(terrenoAtualizado);

        ResponseTerrenoDTO resultado = terrenoService.alterarTerreno(idTerreno, requestTerreno);

        assertEquals(requestTerreno.getTitulo(), resultado.getTitulo());
        assertEquals(requestTerreno.getDescricao(), resultado.getDescricao());
        assertEquals(enderecoTerrenos.getId(), resultado.getEndereco().getId());
    }

    @Test
    @DisplayName("Não consegue alterar o terreno pois não está disponível e joga uma exception")
    public void retornaExceptionTerrenoIndisponivelAoAlterar() {
        Integer idTerreno = 1;
        RequestTerrenoUpdateDTO requestTerrenoUpdateDTO = new RequestTerrenoUpdateDTO();

        Terreno terreno = retornaTerreno();
        terreno.setId(idTerreno);
        terreno.setDisponivel("N");

        when(terrenoRepository.findById(idTerreno)).thenReturn(Optional.of(terreno));

        assertThrows(RegraDeNegocioException.class, () -> terrenoService.alterarTerreno(idTerreno, requestTerrenoUpdateDTO));
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

    @Test
    @DisplayName("Teste arrendar terreno com sucesso")
    void testArrendarTerreno() {
        Terreno terrenoMock = mock(Terreno.class);
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        Contrato contrato = new Contrato();
        Integer usuarioId = 123;

        when(usuarioRepositoryMock.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        when(terrenoMock.getDisponivel()).thenReturn("S");
        Usuario usuarioAtivo = new Usuario();
        usuarioAtivo.setAtivo("S");
        when(usuarioRepositoryMock.findById(eq(usuarioId))).thenReturn(Optional.of(usuarioAtivo));

        contrato.setTerreno(terrenoMock);
        contrato.setLocatario(usuarioRepositoryMock.findById(usuarioId).orElseThrow(() -> new NullPointerException(NOT_FOUND_MESSAGE_USUARIO)));

        try {
            if (contrato.getLocatario().getAtivo().equals("N")) {
                throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_INATIVO);
            }
            if (terrenoMock.getDisponivel().equals("N")) {
                throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_TERRENO_EXIST);
            }
        } catch (RegraDeNegocioException e) {
            assertEquals("Usuário inativo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Testar a criação de contrato, mensalidade e alteração de disponibilidade do terreno")
    public void testarCriacaoContratoMensalidadeEAlteracaoDisponibilidadeTerreno() throws RegraDeNegocioException {
        Integer idTerreno = 1;
        Terreno terreno = new Terreno();
        terreno.setDisponivel("S");

        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();
        contrato.setTerrenoID(idTerreno);

        RequestMensalidadeCreateDTO mensalidade = new RequestMensalidadeCreateDTO();
        contrato.setMensalidade(mensalidade);

        ResponseContratoDTO contratoResponse = new ResponseContratoDTO();
        contratoResponse.setId(1);

        Integer usuarioId = sessaoUsuarioService.getIdLoggedUserId();
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);
        usuario.setAtivo("S");
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        when(terrenoRepository.findById(idTerreno)).thenReturn(Optional.of(terreno));
        when(contratoService.createContrato(contrato)).thenReturn(contratoResponse);
        doNothing().when(mensalidadeService).criarMensalidade(mensalidade);
        when(terrenoRepository.save(terreno)).thenReturn(terreno);
        when(contratoService.resgatarContratoPorId(contratoResponse.getId())).thenReturn(new ResponseContratoRelatorioDTO());

        ResponseContratoRelatorioDTO resultado = terrenoService.arrendarTerreno(idTerreno, contrato);

        verify(contratoService, times(1)).createContrato(contrato);
        verify(mensalidadeService, times(1)).criarMensalidade(mensalidade);
        verify(terrenoRepository, times(1)).save(terreno);
        verify(contratoService, times(1)).resgatarContratoPorId(contratoResponse.getId());

        assertEquals("N", terreno.getDisponivel());

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Testar se a exceção é lançada quando o usuário tenta alugar seu próprio terreno")
    public void testarUsuarioAlugandoProprioTerreno() {
        Integer idTerreno = 1;
        Integer usuarioId = 1;

        Terreno terreno = new Terreno();
        terreno.setDisponivel("S");
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);
        terreno.setDono(usuario);

        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();
        contrato.setTerrenoID(idTerreno);

        when(terrenoRepository.findById(idTerreno)).thenReturn(Optional.of(terreno));
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(usuarioId);

        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
            terrenoService.arrendarTerreno(idTerreno, contrato);
        });

        String expectedMessage = "Usuário não pode alugar seu próprio terreno";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Testar se a exceção é lançada quando o locatário está inativo")
    public void testLocatarioInativo() throws RegraDeNegocioException {
        Integer idTerreno = 1;
        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();
        contrato.setTerrenoID(idTerreno);

        Terreno terreno = new Terreno();
        terreno.setId(idTerreno);
        terreno.setDisponivel("S");

        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);
        usuario.setAtivo("N");

        when(terrenoRepository.findById(idTerreno)).thenReturn(Optional.of(terreno));
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(usuarioId);
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
            terrenoService.arrendarTerreno(idTerreno, contrato);
        });

        String expectedMessage = NOT_FOUND_MESSAGE_INATIVO;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Testar se a exceção é lançada quando o terreno não está disponível para alteração")
    public void testTerrenoNaoDisponivelAlteracao() throws RegraDeNegocioException {
        // Preparar os dados de teste
        Integer idTerreno = 1;
        RequestTerrenoUpdateDTO requestTerreno = new RequestTerrenoUpdateDTO();

        Terreno terreno = new Terreno();
        terreno.setId(idTerreno);
        terreno.setDisponivel("N");

        Usuario dono = new Usuario();
        dono.setUsuarioId(1);
        terreno.setDono(dono);

        when(terrenoRepository.findById(idTerreno)).thenReturn(Optional.of(terreno));
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(dono.getUsuarioId());

        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
            terrenoService.alterarTerreno(idTerreno, requestTerreno);
        });

        String expectedMessage = NOT_FOUND_MESSAGE_TERRENO_EXIST;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Testar se a exceção é lançada quando o terreno não está disponível para arrendamento")
    public void testTerrenoNaoDisponivelArrendamento() throws RegraDeNegocioException {
        Integer idTerreno = 1;
        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();

        Terreno terreno = new Terreno();
        terreno.setId(idTerreno);
        terreno.setDisponivel("N");

        Usuario dono = new Usuario();
        dono.setUsuarioId(2);
        terreno.setDono(dono);

        Usuario locatario = new Usuario();
        locatario.setUsuarioId(1);
        locatario.setAtivo("S");

        when(terrenoRepository.findById(idTerreno)).thenReturn(Optional.of(terreno));
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(locatario.getUsuarioId());
        when(usuarioRepository.findById(locatario.getUsuarioId())).thenReturn(Optional.of(locatario));

        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
            terrenoService.arrendarTerreno(idTerreno, contrato);
        });

        String expectedMessage = NOT_FOUND_MESSAGE_TERRENO_EXIST;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private RequestContratoCreateDTO criarContratoComLocatarioInativo() {
        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();
        return contrato;
    }

    private RequestContratoCreateDTO criarContratoComMesmoProprietario() {
        RequestContratoCreateDTO contrato = new RequestContratoCreateDTO();
        return contrato;
    }

}







