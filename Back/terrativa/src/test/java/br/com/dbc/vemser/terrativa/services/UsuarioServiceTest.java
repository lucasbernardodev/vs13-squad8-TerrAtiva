package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.EnderecoMapper;
import br.com.dbc.vemser.terrativa.dto.mappers.UsuarioMapper;
import br.com.dbc.vemser.terrativa.dto.requests.*;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseAdminDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.CargoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UsuarioService - Test")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private SessaoUsuarioService sessaoUsuarioService;
    @Mock
    private ContratoService contratoService;
    @Mock
    private TerrenoService terrenoService;
    @Mock
    private EnderecoService enderecoService;
    @Mock
    private CargoRepository cargoRepository;

    @Spy
    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deveria buscar usuário por ID")
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
    @DisplayName("Deveria lançar uma exceção ao buscar usuário inativo")
    void buscarUsuarioInativo() {
        // Given
        Integer id = anyInt();
        Usuario usuarioMock = Entidades.retornaUsuario();
        usuarioMock.setAtivo("N");

        // When
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioMock));

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.buscarUsuarioPorId(id));
    }

    @Test
    @DisplayName("Deveria lançar uma exceção ao conferir senhas diferentes")
    void conferirSenha() {

        String senha = "senha123";
        String senhaConf = "senha321";

        assertThrows(RegraDeNegocioException.class, () -> usuarioService.conferirSenha(senha, senhaConf));
    }

    @Test
    @DisplayName("Deveria cadastrar usuário com sucesso")
    void cadastrarUsuario() throws RegraDeNegocioException {
    // Given
        RequestUsuarioCreateDTO requestUsuarioCreateDTO = Entidades.retornaRequestUsuarioCreateDTO();
        Usuario usuario = Entidades.retornaUsuario();
        ResponseUsuarioDTO responseUsuarioDTO = Entidades.retornaResponseUsuarioDTO();
        ResponseEnderecoDTO responseEnderecoDTO = EnderecoMapper.EnderecoParaResponseEndereco(Entidades.retornaEnderecoEntityMock());
        responseUsuarioDTO.setEndereco(responseEnderecoDTO);

    // When
        doNothing().when(usuarioService).conferirSenha(any(), any());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(passwordEncoder.encode(any())).thenReturn("senha321");
        when(cargoRepository.findCargosByIdCargo(anyInt())).thenReturn(Entidades.retornaCargos());
        when(enderecoService.adicionarEndereco(any(RequestEnderecoCreateDTO.class))).thenReturn(responseEnderecoDTO);

        ResponseUsuarioDTO result = usuarioService.cadastrarUsuario(requestUsuarioCreateDTO);
    // Then
        verify(usuarioService, times(1)).cadastrarUsuario(requestUsuarioCreateDTO);
        assertNotNull(result);
        assertEquals(responseUsuarioDTO, result);
}

    @Test
    @DisplayName("Deveria criar um admin com sucesso")
    void criarAdmin() throws RegraDeNegocioException {
    // Given
        RequestAdminDTO requestAdminDTO = Entidades.retornaRequestAdminDTO();
        ResponseAdminDTO responseAdminDTO = Entidades.retornaResponseAdminDTO();
        Usuario usuario = Entidades.retornaUsuario();

    // When
        doNothing().when(usuarioService).conferirSenha(any(), any());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(passwordEncoder.encode(any())).thenReturn("senha123");
        when(cargoRepository.findCargosByIdCargo(anyInt())).thenReturn(Entidades.retornaCargos());

        ResponseAdminDTO result = usuarioService.criarAdmin(requestAdminDTO);

    // Then
        assertTrue(new ReflectionEquals(responseAdminDTO).matches(result));
}

    @Test
    @DisplayName("Deveria alterar senha com sucesso")
    void alterarSenha() throws RegraDeNegocioException {
    // Given
        RequestSenhaDTO requestSenhaDTO = new RequestSenhaDTO();
        Usuario usuario = Entidades.retornaUsuario();

    // When
        doReturn(usuario).when(usuarioService).getLoggedUser();
        doNothing().when(usuarioService).conferirSenha(any(), any());
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(passwordEncoder.encode(any())).thenReturn("senha321");

        usuarioService.alterarSenha(requestSenhaDTO);

    // Then
        verify(usuarioService, times(1)).alterarSenha(requestSenhaDTO);
    }

    @Test
    @DisplayName("Deveria lançar exceção ao alterar senha com senha atual incorreta")
    void alterarSenhaComSenhaAtualIncorreta() throws RegraDeNegocioException {
        // Given
        RequestSenhaDTO requestSenhaDTO = new RequestSenhaDTO();
        Usuario usuario = Entidades.retornaUsuario();

        // When
        doReturn(usuario).when(usuarioService).getLoggedUser();
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        assertThrows(RegraDeNegocioException.class, () -> usuarioService.alterarSenha(requestSenhaDTO));

    }

    @Test
    @DisplayName("Deveria deletar usuário com sucesso")
    void deletarUsuario() throws RegraDeNegocioException {
    // Given
        DeletarContaDTO deletarContaDTO = new DeletarContaDTO();
        deletarContaDTO.setConfirmacao("DELETAR MINHA CONTA");
        Usuario usuario = Entidades.retornaUsuario();
        List<Contrato> listaContratos = new ArrayList<>();
        Contrato contrato = Entidades.retornaContratoEntity();
        contrato.setAtivo("N");
        listaContratos.add(contrato);

    // When
        when(contratoService.buscarContratoPorLocatario(anyInt())).thenReturn(listaContratos);
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(1);
        when(usuarioRepository.findByUsuarioIdAndAtivoEquals(anyInt(), any())).thenReturn(usuario);
        doNothing().when(terrenoService).alterarTerrenosUsuarioDeletado(anyInt());

        usuarioService.deletarUsuario(deletarContaDTO);
    // Then
        verify(usuarioService, times(1)).deletarUsuario(deletarContaDTO);
}

    @Test
    @DisplayName("Deveria verificar se não tem contratos ativos antes de deletar usuário")
    void deletarUsuarioComContratosAtivos() {
        // Given
        DeletarContaDTO deletarContaDTO = new DeletarContaDTO();
        deletarContaDTO.setConfirmacao("DELETAR MINHA CONTA");
        Usuario usuario = Entidades.retornaUsuario();
        List<Contrato> listaContratos = new ArrayList<>();
        listaContratos.add(Entidades.retornaContratoEntity());

        // When
        when(contratoService.buscarContratoPorLocatario(anyInt())).thenReturn(listaContratos);
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(1);
        when(usuarioRepository.findByUsuarioIdAndAtivoEquals(anyInt(), any())).thenReturn(usuario);

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.deletarUsuario(deletarContaDTO));
    }
    @Test
    @DisplayName("Deveria jogar erro quando confirmação para deletar conta não está correta")
    void deletarUsuarioComConfirmacaoIncorreta() {
        // Given
        DeletarContaDTO deletarContaDTO = new DeletarContaDTO();
        deletarContaDTO.setConfirmacao("DELETAR MINHA CONTA (ERRADO)");

        // When
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.deletarUsuario(deletarContaDTO));
    }

    @Test
    @DisplayName("Deveria jogar erro quando usuário não é encontrado")
    void deletarUsuarioComUsuarioNaoEncontrado() {
        // Given
        DeletarContaDTO deletarContaDTO = new DeletarContaDTO();
        deletarContaDTO.setConfirmacao("DELETAR MINHA CONTA");

        // When
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(1);
        when(usuarioRepository.findByUsuarioIdAndAtivoEquals(anyInt(), any())).thenReturn(null);

        assertThrows(RegraDeNegocioException.class, () -> usuarioService.deletarUsuario(deletarContaDTO));
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

    @Test
    @DisplayName("Retorna usuário de acordo com o email")
    void retornaUsuarioPorEmail() {
        // Given
        String email = anyString();
        // When
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(Entidades.retornaUsuario()));

        Optional<Usuario> result = usuarioService.findByEmail(email);
        // Then
        assertTrue(result.isPresent());

    }

    @Test
    @DisplayName("Deveria retornar usuário logado com sucesso")
    void getLoggedUser() throws RegraDeNegocioException {
        // Given
        Usuario usuario = Entidades.retornaUsuario();
        // When
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(1);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.getLoggedUser();
        // Then
        assertEquals(usuario, result);
    }

    @Test
    @DisplayName("Deveria lançar exceção ao não encontrar usuário logado")
    void getLoggedUserNotFound() {
        // Given
        // When
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(1);
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.getLoggedUser());
    }

    @Test
    @DisplayName("Deveria retornar DTO do usuário logado com sucesso")
    void getUserDTO() throws RegraDeNegocioException {
        // Given
        Usuario usuario = Entidades.retornaUsuario();
        ResponseUsuarioDTO responseUsuarioDTO = Entidades.retornaResponseUsuarioDTO();
        // When
        when(sessaoUsuarioService.getIdLoggedUserId()).thenReturn(1);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        ResponseUsuarioDTO result = usuarioService.getUserDTO();
        // Then
        assertEquals(responseUsuarioDTO, result);
    }

    @Test
    @DisplayName("Deveria retornar endereço do usuário logado com sucesso")
    void resgatarEnderecoUsuario() throws RegraDeNegocioException {
        // Given
        ResponseEnderecoDTO responseEnderecoDTO = EnderecoMapper.EnderecoParaResponseEndereco(Entidades.retornaEnderecoEntityMock());
        // When
        when(enderecoService.resgatarPorId(anyInt())).thenReturn(responseEnderecoDTO);

        ResponseEnderecoDTO result = usuarioService.resgatarEnderecoUsuario();
        // Then
        assertEquals(responseEnderecoDTO, result);
    }

    @Test
    @DisplayName("Deveria alterar endereço do usuário com sucesso")
    void alterarEnderecoUsuario() throws RegraDeNegocioException {
        // Given
        RequestEnderecoCreateDTO requestEnderecoUpdateDTO = Entidades.retornaRequestEnderecoCreateDTO();
        ResponseEnderecoDTO responseEnderecoDTO = EnderecoMapper.EnderecoParaResponseEndereco(Entidades.retornaEnderecoEntityMock());
        Usuario usuario = Entidades.retornaUsuario();

        // When
        when(enderecoService.alterar(usuario, requestEnderecoUpdateDTO)).thenReturn(responseEnderecoDTO);
        doReturn(usuario).when(usuarioService).getLoggedUser();

        ResponseEnderecoDTO result = usuarioService.alterarEndereco(requestEnderecoUpdateDTO);
        // Then
        assertEquals(responseEnderecoDTO, result);
    }
}