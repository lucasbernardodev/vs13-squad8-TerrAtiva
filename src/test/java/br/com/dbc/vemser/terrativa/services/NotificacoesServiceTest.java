package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.requests.RequestNotificacoesDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseNotificacoesDTO;
import br.com.dbc.vemser.terrativa.entity.Notificacoes;
import br.com.dbc.vemser.terrativa.repository.NotificacoesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacoesServiceTest {

    @Mock
    private NotificacoesRepository notificacoesRepository;
    @Mock
    private ObjectMapper objectMapper;

    @Spy
    @InjectMocks
    private NotificacoesService notificacoesService;

    @BeforeEach
    public void setUp(){

    }

    @Test
    @DisplayName("Envia e retorna notificação salva no banco de dados")
    public void enviaERetoranaNotificacao(){

        Notificacoes notificacoesMock = Entidades.retornaNotificacoes();
        ResponseNotificacoesDTO responseMock = Entidades.retornaResponseNotificacoesDTO();
        RequestNotificacoesDTO requestMock = Entidades.retornaRequestNotificacoesDTO();

        when(notificacoesRepository.save(any())).thenReturn(notificacoesMock);
        when(objectMapper.convertValue(any(), eq(Notificacoes.class))).thenReturn(notificacoesMock);
        when(objectMapper.convertValue(any(), eq(ResponseNotificacoesDTO.class))).thenReturn(responseMock);

        ResponseNotificacoesDTO res = notificacoesService.enviarNotificacoes(requestMock);

        assertNotNull(res);
        assertEquals(res, responseMock);
    }


    @Test
    void buscarDatasAnterior() {

        when(notificacoesRepository.findAllByDataBefore(any())).thenReturn(Entidades.retornaListaNotificacoes());
        when(objectMapper.convertValue(any(), eq(ResponseNotificacoesDTO.class))).thenReturn(Entidades.retornaResponseNotificacoesDTO());

        notificacoesService.buscarDatasAnterior("2021-01-01");

        verify(notificacoesRepository, times(1)).findAllByDataBefore(any());
    }

    @Test
    void buscarDatasPosterior() {

        when(notificacoesRepository.findAllByDataAfter(any())).thenReturn(Entidades.retornaListaNotificacoes());
        when(objectMapper.convertValue(any(), eq(ResponseNotificacoesDTO.class))).thenReturn(Entidades.retornaResponseNotificacoesDTO());

        notificacoesService.buscarDatasPosterior("2021-01-01");

        verify(notificacoesRepository, times(1)).findAllByDataAfter(any());
    }

    @Test
    void buscarNotificacoesUsuarios() {

        when(notificacoesRepository.findAllByUsuariosContains(any())).thenReturn(Entidades.retornaListaNotificacoes());
        when(objectMapper.convertValue(any(), eq(ResponseNotificacoesDTO.class))).thenReturn(Entidades.retornaResponseNotificacoesDTO());

        notificacoesService.buscarNotificacoesUsuarios(1);

        verify(notificacoesRepository, times(1)).findAllByUsuariosContains(any());
    }
}