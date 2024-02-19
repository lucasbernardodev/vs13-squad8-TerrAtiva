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
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacoesServiceTest {

    @Mock
    private NotificacoesRepository notificacoesRepository;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private NotificacoesService notificacoesService;

    @BeforeEach
    public void setUp(){

    }

    // Esta quebrado - Dando erro
    @Test
    @DisplayName("Envia e retorna notificação salva no banco de dados")
    public void enviaERetoranaNotificacao(){

        Notificacoes notificacoesMock = Entidades.retornaNotificacoes();
        ResponseNotificacoesDTO responseMock = Entidades.retornaResponseNotificacoesDTO();
        RequestNotificacoesDTO requestMock = Entidades.retornaRequestNotificacoesDTO();

        when(notificacoesRepository.save(anyObject())).thenReturn(notificacoesMock);

        ResponseNotificacoesDTO res = notificacoesService.enviarNotificacoes(requestMock);

        assertNotNull(res);
        assertEquals(res, responseMock);
    }

}