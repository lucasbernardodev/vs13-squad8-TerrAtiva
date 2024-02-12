package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.UsuarioMapper;
import br.com.dbc.vemser.terrativa.dto.requests.*;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import br.com.dbc.vemser.terrativa.security.TokenService;
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
    private final TokenService tokenService;
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
        if (!usuario.getSenha().equals(usuario.getSenhaConf())) {
            throw new RegraDeNegocioException("Senhas não conferem!");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        RequestEnderecoCreateDTO endereco = usuario.getEndereco();
        Usuario usuarioSalvo = usuarioRepository.save(UsuarioMapper.requestUsuarioParaUsuario(usuario));
        endereco.setUsuarioID(usuarioSalvo.getUsuarioId());
        ResponseEnderecoDTO enderecoDTO = enderecoService.adicionarEndereco(endereco);
        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioSalvo);
        responseUsuario.setEndereco(enderecoDTO);
        //emailService.sendEmailUsuario(responseUsuario, 1);
        return responseUsuario;
    }

//    public ResponseUsuarioDTO alterarUsuario(Integer idUsuario, RequestUsuarioUpdateDTO usuario) throws RegraDeNegocioException {
//        buscarUsuarioPorId(idUsuario);
//        usuario.setUsuarioId(idUsuario);
//        Usuario usuarioAtualizado = UsuarioMapper.requestUsuarioParaUsuario(usuario);
//        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioRepository.save(usuarioAtualizado));
////        emailService.sendEmailUsuario(responseUsuario, 2);
//        return responseUsuario;
//    }

    public String alterarSenha(RequestSenhaDTO senha) throws Exception{
        Usuario usuarioRecuperado = findById(getIdLoggedUser()).get();
        if (passwordEncoder.matches(usuarioRecuperado.getSenha(), senha.getSenhaAtual())){
            if(senha.getSenhaNova().equals(senha.getSenhaNovaConf())){
                String senhaCripto = passwordEncoder.encode(senha.getSenhaNova());
                usuarioRecuperado.setSenha(senhaCripto);
                usuarioRepository.save(usuarioRecuperado);
            } else {
                new RegraDeNegocioException("Senhas não conferem!");
            }
        }else{
            new RegraDeNegocioException("Senha atual apresentada não confere com a atual");
        }
        return "Senha alterada com sucesso!";
    }

    public void deletarUsuario(DeletarContaDTO confirmacao) throws RegraDeNegocioException {
        if (!confirmacao.getConfirmacao().equals("DELETAR MINHA CONTA")) {
            throw new RegraDeNegocioException("Operação cancelada!");
        }
        Usuario usuarioRecuperado = usuarioRepository.findByUsuarioIdAndAtivoEquals(getIdLoggedUser(), "S");
        if (usuarioRecuperado == null) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO);
        }
        List<Contrato> listaContratos = contratoService.buscarContratoPorLocatario(usuarioRecuperado.getUsuarioId());
        for (Contrato contrato: listaContratos){
            if(contrato.getAtivo().equals("S")){
                throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_CONTRATOS);
            }
        }
        terrenoService.alterarTerrenosUsuarioDeletado(getIdLoggedUser());
        usuarioRecuperado.setAtivo("N");
        usuarioRepository.save(usuarioRecuperado);
    }

    public Optional<Usuario> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Optional<Usuario> findByEmail(String username) {
        return usuarioRepository.findByEmail(username);
    }

    public Usuario getLoggedUser() throws RegraDeNegocioException {
        return findById(getIdLoggedUser()).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO));
    }
    public ResponseUsuarioDTO getUserDTO() throws RegraDeNegocioException {
        return UsuarioMapper.usuarioParaResponseUsuario(getLoggedUser());
    }


    public Optional<Usuario> findByEmailAndSenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public ResponseEnderecoDTO resgatarPorId() throws RegraDeNegocioException {
        buscarUsuarioPorId(getIdLoggedUser());
        return enderecoService.resgatarPorId(getIdLoggedUser());
    }


    public ResponseEnderecoDTO alterarEndereco(RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        buscarUsuarioPorId(getIdLoggedUser());
        return enderecoService.alterar(getIdLoggedUser(), endereco);
    }




    public ResponseUsuarioDTO alterarUsuarioComToken(RequestUsuarioUpdateDTO usuario) throws RegraDeNegocioException {

        Usuario usuarioExistente = findById(getIdLoggedUser()).get();

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setSobrenome(usuario.getSobrenome());
        usuarioExistente.setEmail(usuario.getEmail());

        usuarioRepository.save(usuarioExistente);

        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioExistente);

        return responseUsuario;
    }

}