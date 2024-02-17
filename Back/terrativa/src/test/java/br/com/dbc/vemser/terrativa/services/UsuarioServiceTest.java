package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.UsuarioMapper;
import br.com.dbc.vemser.terrativa.dto.requests.*;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UsuarioService - Test")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Spy
    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Buscar usuário por id")
    void buscarUsuarioPorId() throws RegraDeNegocioException {
        // Given
        Integer id = anyInt();
        Usuario usuarioMock = Entidades.retornaUsuario();
        ResponseUsuarioDTO responseUsuarioDTOMock = Entidades.retornaResponseUsuarioDTO();

        // When
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioMock));
        ResponseUsuarioDTO result = usuarioService.buscarUsuarioPorId(id);

        // Then
        assertNotNull(result);
        assertEquals(responseUsuarioDTOMock, result);
    }

    @Test
    @DisplayName("Confere se senhas são iguais")
    void conferirSenha() {

        String senha = "senha123";
        String senhaConf = "senha321";

        assertThrows(RegraDeNegocioException.class, () -> usuarioService.conferirSenha(senha, senhaConf));
    }

    @Test
    @DisplayName("Cadastrar usuário")
    void cadastrarUsuario() {
    // Given
//    RequestUsuarioCreateDTO requestUsuarioCreateDTO = Entidades.r;
    // TODO: Set the properties of requestUsuarioCreateDTO

    // When
    // TODO: Mock the necessary methods

    // Then
    // TODO: Perform the necessary assertions
}

    @Test
    @DisplayName("Criar admin")
    void criarAdmin() {
    // Given
//    RequestAdminDTO requestAdminDTO = new RequestAdminDTO();
    // TODO: Set the properties of requestAdminDTO

    // When
    // TODO: Mock the necessary methods

    // Then
    // TODO: Perform the necessary assertions
}

    @Test
    @DisplayName("Alterar senha")
    void alterarSenha() {
    // Given
//    RequestSenhaDTO requestSenhaDTO = new RequestSenhaDTO();
    // TODO: Set the properties of requestSenhaDTO

    // When
    // TODO: Mock the necessary methods

    // Then
    // TODO: Perform the necessary assertions
}

    @Test
    @DisplayName("Deletar usuário")
    void deletarUsuario() {
    // Given
//    DeletarContaDTO deletarContaDTO = new DeletarContaDTO();
    // TODO: Set the properties of deletarContaDTO

    // When
    // TODO: Mock the necessary methods

    // Then
    // TODO: Perform the necessary assertions
}



    @Test
    @DisplayName("Alterar usuário com token")
    void alterarUsuarioComToken() throws RegraDeNegocioException {
        // Given
        RequestUsuarioUpdateDTO requestUsuarioUpdateDTO = Entidades.retornaRequestUsuarioUpdateDTO();
        Usuario usuario = Entidades.retornaUsuario();

        // When
        doReturn(usuario).when(usuarioService).getLoggedUser();
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        ResponseUsuarioDTO result = usuarioService.alterarUsuarioComToken(requestUsuarioUpdateDTO);

        // Then
        assertEquals(Entidades.retornaResponseUsuarioDTO(), result);


}

}