package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.MensalidadeMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseMensalidadeDTO;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.MensalidadeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MensalidadeService - Test")
class MensalidadeServiceTest {

    @Mock
    private MensalidadeRepository mensalidadeRepository;
    @Mock
    private ContratoService contratoService;
    @Mock
    private SessaoUsuarioService sessaoUsuarioService;
    @Spy
    @InjectMocks
    private MensalidadeService mensalidadeService;

    @Test
    @DisplayName("Deve criar mensalidade quando criado um contrato, porem não retorna nada.")
    public void criaMensalidadeComSucesso() throws RegraDeNegocioException {
        ResponseContratoRelatorioDTO respMock = Entidades.retornaRelatorioContrato();
        RequestMensalidadeCreateDTO reqMock = retornaRequestMensalidade();
        Mensalidade menMock = Entidades.retornaMensalidade();


        when(contratoService.resgatarContratoPorId(anyInt())).thenReturn(respMock);
        when(mensalidadeRepository.save(anyObject())).thenReturn(menMock);

        mensalidadeService.criarMensalidade(reqMock);

        verify(mensalidadeRepository, times(1)).save(any(Mensalidade.class));
    }


    @Test
    @DisplayName("Altera mensalidade no banco de dados e responde um response")
    public void alteraMensalidadeComSucesso() throws RegraDeNegocioException {
        Integer idMock = 2;
        RequestMensalidadeCreateDTO createDTO = retornaRequestMensalidade();
        Mensalidade mensalidadeMock = Entidades.retornaMensalidade();
        ResponseContratoRelatorioDTO relatorioDTOMock = Entidades.retornaRelatorioContrato();
        ResponseMensalidadeDTO responseMensalidadeDTOMock = retornaResponseMensalidade();

        doReturn(null).when(mensalidadeService).verificaUsuario(idMock);
        when(mensalidadeRepository.findById(anyInt())).thenReturn(Optional.of(mensalidadeMock));
        when(contratoService.resgatarContratoPorId(anyInt())).thenReturn(relatorioDTOMock);
        when(mensalidadeRepository.save(anyObject())).thenReturn(mensalidadeMock);

        ResponseMensalidadeDTO responseMensalidadeDTO = mensalidadeService.alterarMensalidade(idMock, createDTO);


        assertNotNull(responseMensalidadeDTO);
        assertEquals(responseMensalidadeDTO, responseMensalidadeDTOMock);
    }

    @Test
    @DisplayName("Retorna mensalidade por ID com um Response mensalidade")
    public void restornaMensalidadeComSucesso() throws RegraDeNegocioException {
        Integer idMock = 2;
        Mensalidade mensalidademock = Entidades.retornaMensalidade();
        ResponseMensalidadeDTO responseMock = retornaResponseMensalidade();

        doReturn(null).when(mensalidadeService).verificaUsuario(idMock);
        when(mensalidadeRepository.findById(anyInt())).thenReturn(Optional.of(mensalidademock));

        ResponseMensalidadeDTO responseMensalidadeDTO = mensalidadeService.resgatarMensalidadePorId(idMock);

        assertNotNull(responseMensalidadeDTO);
        assertEquals(responseMock, responseMensalidadeDTO);
    }

    @Test
    @DisplayName("Retorna mensalidade por ID lança uma Exception")
    public void restornaMensalidadeException() throws RegraDeNegocioException {
        Integer idMock = 2;
        Mensalidade mensalidademock = Entidades.retornaMensalidade();

        doReturn(null).when(mensalidadeService).verificaUsuario(idMock);

        assertThrows(RegraDeNegocioException.class, () -> mensalidadeService.resgatarMensalidadePorId(idMock));
    }


    @Test
    @DisplayName("Verifica se o usuario pode fazer as alterações que deseja no banco de dados")
    public void verificaUsuarioComSucesso() throws RegraDeNegocioException {
        Integer idLogger = 2;
        Mensalidade mensalidadeMock = Entidades.retornaMensalidade();
        Contrato contratoMock = Entidades.retornaContratoEntity();

        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idLogger);
        when(mensalidadeRepository.findById(anyInt())).thenReturn(Optional.of(mensalidadeMock));
        when(contratoService.findByID(anyInt())).thenReturn(contratoMock);

        assertNull(mensalidadeService.verificaUsuario(idLogger));
    }

    @Test
    @DisplayName("Verifica se o usuario pode fazer e lança uma Exception de erro")
    public void verificaUsuarioException() throws RegraDeNegocioException {
        Integer idLogger = 3;
        Mensalidade mensalidadeMock = Entidades.retornaMensalidade();
        Contrato contratoMock = Entidades.retornaContratoEntity();

        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(idLogger);
        when(mensalidadeRepository.findById(anyInt())).thenReturn(Optional.of(mensalidadeMock));
        when(contratoService.findByID(anyInt())).thenReturn(contratoMock);

        assertThrows(RegraDeNegocioException.class, () -> mensalidadeService.verificaUsuario(idLogger));
    }



    public RequestMensalidadeCreateDTO retornaRequestMensalidade(){
        RequestMensalidadeCreateDTO men = new RequestMensalidadeCreateDTO();
        men.setMensalidadeID(1);
        men.setContratoID(1);
        men.setValorMensal(2000);
        men.setAnoExercicio(2024);

        return men;
    }

    public ResponseMensalidadeDTO retornaResponseMensalidade(){
        ResponseMensalidadeDTO res = new ResponseMensalidadeDTO();
        res.setMensalidadeID(1);
        res.setContratoID(1);
        res.setValorMensal(2000);
        res.setAnoExercicio(2024);

        return res;
    }



}