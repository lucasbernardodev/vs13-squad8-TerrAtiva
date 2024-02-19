package br.com.dbc.vemser.terrativa.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SessaoUsuarioService - Test")

class SessaoUsuarioServiceTest {

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Spy
    @InjectMocks
    private SessaoUsuarioService sessaoUsuarioService;
    @Test
    void getIdLoggedUserId() {

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(1);

        SecurityContextHolder.setContext(securityContext);

        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).thenReturn(null);
        when(authentication.getPrincipal()).thenReturn(1);

        sessaoUsuarioService.getIdLoggedUserId();

        verify(sessaoUsuarioService, times(1)).getIdLoggedUserId();
    }
}