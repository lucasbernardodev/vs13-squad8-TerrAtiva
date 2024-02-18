package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.*;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ContratoService - Test")
class ContratoServiceTest {

    @Mock
    private ContratoRepository contratoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private SessaoUsuarioService sessaoUsuarioService;
    @Spy
    @InjectMocks
    private ContratoService contratoService;

    @Test
    @DisplayName("Retorna relatório do contrato com informações de endereço")
    public void retornaRelatorioDoContrato() throws RegraDeNegocioException {
        //GIVEN
        ResponseContratoRelatorioDTO relMock = Entidades.retornaRelatorioContrato();
        Contrato contratoMock = Entidades.retornaContratoEntity();
        Integer idUsuarioMock = 2;
        Usuario user = Entidades.retornaUsuario();

        //WHEN
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idUsuarioMock);
        when(contratoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(contratoMock));
        when(contratoRepository.retornaContratoPorID(anyInt())).thenReturn(contratoMock);
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user));

        ResponseContratoRelatorioDTO rel = contratoService.resgatarContratoPorId(new Random().nextInt());

        //THEN
        assertNotNull(rel);
        assertEquals(relMock, rel);

    }

    @Test
    @DisplayName("Criar contrato com sucesso")
    public void criarContratoComSucesso(){
        //GIVEN
        RequestContratoCreateDTO requestContratoCreateDTOMock = retornaContratCreate();
        Contrato contratoMock = Entidades.retornaContratoEntity();
        ResponseContratoDTO responseContratoDTOMock = retornaResponseContratoDTO();

        //WHEN
        when(contratoRepository.save(anyObject())).thenReturn(contratoMock);

        ResponseContratoDTO responseContratoDTO = contratoService.createContrato(requestContratoCreateDTOMock);

        //THEN
        assertNotNull(responseContratoDTO);
        assertTrue(new ReflectionEquals(responseContratoDTO).matches(responseContratoDTOMock));

    }

    @Test
    @DisplayName("Deve deletar um contrato com sucesso")
    public void deletarContratoComSucesso() throws RegraDeNegocioException {
        //GIVEN
        Contrato contratoMock = Entidades.retornaContratoEntity();
        Integer idUsuarioMock = 2;

        //WHEN
        doReturn(null).when(contratoService).verificaUsuario(idUsuarioMock);
        doReturn(contratoMock).when(contratoService).findByID(idUsuarioMock);
        when(contratoRepository.save(anyObject())).thenReturn(contratoMock);

        contratoService.deletar(2);

        //THEN
        verify(contratoRepository, times(1)).save(contratoMock);
    }


    @Test
    @DisplayName("Deve retornar um contrato com sucesso através do ID do locatario")
    public void buscarContratoPorLocatario() {
        //Given
        List<Contrato> contratoListaMock = List.of(Entidades.retornaContratoEntity(), Entidades.retornaContratoEntity(), Entidades.retornaContratoEntity());

        //WHEN
        when(contratoRepository.findAllByLocatarioID(anyInt())).thenReturn(contratoListaMock);

        List<Contrato> contratoLista = contratoService.buscarContratoPorLocatario(new Random().nextInt());

        //THEN
        assertNotNull(contratoLista);
        assertEquals(contratoListaMock.size(), contratoLista.size());
    }

    @Test
    @DisplayName("Retorna contrato po ID solicitado")
    public void retornacontratoID() throws RegraDeNegocioException {
        //Given
        Optional<Contrato> contratoMock = Optional.of(Entidades.retornaContratoEntity());

        //WHEN
        when(contratoRepository.findById(anyInt())).thenReturn(contratoMock);

        Contrato contrato = contratoService.findByID(new Random().nextInt());

        //THEN
        assertNotNull(contrato);
        assertEquals(contrato, contratoMock.get());
    }

    @Test
    @DisplayName("Deve verificar o usuário para autorizar mudanças em apenas sem contratos.")
    public void retornaNullCasoSucesso() throws RegraDeNegocioException {
        //Given
        Integer idUsuarioMock = 2;
        Optional<Contrato> contratoMock = Optional.of(Entidades.retornaContratoEntity());

        //WHEN
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idUsuarioMock);
        when(contratoRepository.findById(anyInt())).thenReturn(contratoMock);

        //THEN
        assertNull(contratoService.verificaUsuario(new Random().nextInt()));
    }

    @Test
    @DisplayName("Deve retornar uma RegraDeNegocioException quando não foi o usuario correto para alteração.")
    public void retornaExceptionVerificaUsuario(){
        //Given
        Integer idDonoErradoMock = new Random().nextInt();
        Optional<Contrato> contratoMock = Optional.of(Entidades.retornaContratoEntity());

        //WHEN
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idDonoErradoMock);
        when(contratoRepository.findById(anyInt())).thenReturn(contratoMock);

        //THEN
        assertThrows(RegraDeNegocioException.class, () -> contratoService.verificaUsuario(idDonoErradoMock));
    }


    public RequestContratoCreateDTO retornaContratCreate(){
        RequestContratoCreateDTO cont = new RequestContratoCreateDTO();
        cont.setDataAssinatura(LocalDate.of(2024,02,15));
        cont.setDataFinal(LocalDate.of(2024,02,15));
        cont.setDataInicio(LocalDate.of(2025,02,15));
        cont.setDataVencimentoAluguel(new Random().nextInt());
        cont.setMensalidade(retornaCreateMensalidade());

        return cont;
    }

    public RequestMensalidadeCreateDTO retornaCreateMensalidade(){
        RequestMensalidadeCreateDTO men = new RequestMensalidadeCreateDTO();
        men.setAnoExercicio(2024);
        men.setValorMensal(2000);

        return men;
    }

    public ResponseContratoDTO retornaResponseContratoDTO(){
        ResponseContratoDTO cont = new ResponseContratoDTO();
        cont.setId(1);
        cont.setAtivo("S");
        cont.setDataAssinatura(LocalDate.of(2024,02,15));
        cont.setDataFinal(LocalDate.of(2024,02,15));
        cont.setDataInicio(LocalDate.of(2025,02,15));
        cont.setDataVencimentoAluguel(5);

        return cont;
    }



}