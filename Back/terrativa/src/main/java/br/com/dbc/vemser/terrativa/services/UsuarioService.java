package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.UsuarioMapper;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioLoginDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioUpdateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final ContratoService contratoService;
    private final TerrenoService terrenoService;
    private final EnderecoService enderecoService;
    private final PasswordEncoder passwordEncoder;


    private final String NOT_FOUND_MESSAGE_USUARIO = "Usuário não encontrado";
    private final String NOT_FOUND_MESSAGE_CONTRATOS = "Você possui contratos ativos, antes de deletar seu cadastro, finalize todos seus contratos!";


    public ResponseUsuarioDTO buscarUsuarioPorId(Integer id) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findByUsuarioIdAndAtivoEquals(id, "S");
        if (usuario == null || usuario.getAtivo().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO);
        }
        return UsuarioMapper.usuarioParaResponseUsuario(usuario);
    }

    public ResponseUsuarioDTO cadastrarUsuario(RequestUsuarioCreateDTO usuario) throws RegraDeNegocioException {
        usuario.setAtivo("S");
        usuario.setUsuarioId(null);
        RequestEnderecoCreateDTO endereco = usuario.getEndereco();
        Usuario usuarioSalvo = usuarioRepository.save(UsuarioMapper.requestUsuarioParaUsuario(usuario));
        endereco.setUsuarioID(usuarioSalvo.getUsuarioId());
        ResponseEnderecoDTO enderecoDTO = enderecoService.adicionarEndereco(endereco);
         ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioSalvo);
         responseUsuario.setEndereco(enderecoDTO);
        //emailService.sendEmailUsuario(responseUsuario, 1);
        return responseUsuario;
    }

    public ResponseUsuarioDTO alterarUsuario(Integer idUsuario, RequestUsuarioUpdateDTO usuario) throws RegraDeNegocioException {
        buscarUsuarioPorId(idUsuario);
        usuario.setUsuarioId(idUsuario);
        Usuario usuarioAtualizado = UsuarioMapper.requestUsuarioParaUsuario(usuario);
        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioRepository.save(usuarioAtualizado));
//        emailService.sendEmailUsuario(responseUsuario, 2);
        return responseUsuario;
    }

    public void deletarUsuario(int id) throws RegraDeNegocioException {
        Usuario usuarioRecuperado = usuarioRepository.findByUsuarioIdAndAtivoEquals(id, "S");
        if (usuarioRecuperado == null) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO);
        }
        List<Contrato> listaContratos = contratoService.buscarContratoPorLocatario(usuarioRecuperado.getUsuarioId());
        for (Contrato contrato: listaContratos){
            if(contrato.getAtivo().equals("S")){
                throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_CONTRATOS);
            }
        }
        terrenoService.alterarTerrenosUsuarioDeletado(id);
        usuarioRecuperado.setAtivo("N");
        usuarioRepository.save(usuarioRecuperado);
//        emailService.sendEmailUsuario(responseUsuarioDTO, 3);
    }

//    public ResponseUsuarioDTO loginUsuario(RequestUsuarioLoginDTO usuario) throws RegraDeNegocioException {
//        Usuario usuarioLogin = usuarioRepository.findByEmailAndSenhaAndAtivoEquals(usuario.getEmail(), usuario.getSenha(), "S");
//        if (usuarioLogin == null) {
//            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO);
//        }
//        return UsuarioMapper.usuarioParaResponseUsuario(usuarioLogin);
//    }


    public Optional<Usuario> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Optional<Usuario> findByLogin(String username) {
        return usuarioRepository.findByLogin(username);
    }

    public Optional<Usuario> getLoggedUser() throws RegraDeNegocioException {
        return findById(getIdLoggedUser());
    }

    public Integer getIdLoggedUser() {
        Integer findUserId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return findUserId;
    }




    public ResponseEnderecoDTO resgatarPorId(Integer id) throws RegraDeNegocioException {
        buscarUsuarioPorId(id);
        return enderecoService.resgatarPorId(id);
    }


    public ResponseEnderecoDTO alterarEndereco(Integer id, RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        buscarUsuarioPorId(id);
        return enderecoService.alterar(id, endereco);
    }
}