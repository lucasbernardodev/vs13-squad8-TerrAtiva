package br.com.dbc.vemser.terrativa.services;

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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final ContratoService contratoService;
    private final TerrenoService terrenoService;
    private final EnderecoService enderecoService;
    private final PasswordEncoder passwordEncoder;
    private final CargoRepository cargoRepository;
    private final SessaoUsuarioService sessaoUsuarioService;


    private final String NOT_FOUND_MESSAGE_USUARIO = "Usuário não encontrado";
    private final String OPERATION_CANCELED = "Operação cancelada!";
    private final String PASSWORD_NOT_CHECK = "Senha atual apresentada não confere com a atual";
    private final String NOT_FOUND_MESSAGE_CONTRATOS = "Você possui contratos ativos, antes de deletar seu cadastro, finalize todos seus contratos!";


    public ResponseUsuarioDTO buscarUsuarioPorId(Integer id) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO));
        if (usuario.getAtivo().equals("N")) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO);
        }
        return UsuarioMapper.usuarioParaResponseUsuario(usuario);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario getLoggedUser() throws RegraDeNegocioException {
        return usuarioRepository.findById(sessaoUsuarioService.getIdLoggedUserId()).orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO));
    }

    public ResponseUsuarioDTO getUserDTO() throws RegraDeNegocioException {
        return UsuarioMapper.usuarioParaResponseUsuario(getLoggedUser());
    }

    public ResponseEnderecoDTO resgatarEnderecoUsuario() {
        return enderecoService.resgatarPorId(sessaoUsuarioService.getIdLoggedUserId());
    }

    public ResponseUsuarioDTO cadastrarUsuario(RequestUsuarioCreateDTO usuario) throws RegraDeNegocioException {
        usuario.setAtivo("S");
        usuario.setUsuarioId(null);
        conferirSenha(usuario.getSenha(), usuario.getSenhaConf());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        RequestEnderecoCreateDTO endereco = usuario.getEndereco();
        Usuario usuarioEntity = UsuarioMapper.requestUsuarioParaUsuario(usuario);
        usuarioEntity.setCargos(cargoRepository.findCargosByIdCargo(2));
        Usuario usuarioSalvo = usuarioRepository.save(usuarioEntity);
        endereco.setId(usuarioSalvo.getUsuarioId());
        endereco.setUsuarioID(usuarioSalvo.getUsuarioId());
        ResponseEnderecoDTO enderecoDTO = enderecoService.adicionarEndereco(endereco);
        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioSalvo);
        responseUsuario.setEndereco(enderecoDTO);
        //emailService.sendEmailUsuario(responseUsuario, 1);
        return responseUsuario;
    }

    public ResponseAdminDTO criarAdmin(RequestAdminDTO admin) throws RegraDeNegocioException {
        conferirSenha(admin.getSenha(), admin.getSenhaConf());
        admin.setSenha(passwordEncoder.encode(admin.getSenha()));
        Usuario usuario = UsuarioMapper.usuarioParaRequestAdminUsuario(admin);
        usuario.setAtivo("S");
        usuario.setCargos(cargoRepository.findCargosByIdCargo(1));
        Usuario usuarioCadastrado = usuarioRepository.save(usuario);
        return UsuarioMapper.usuarioParaRequestAdminUsuario(usuarioCadastrado);
    }

    public void alterarSenha(RequestSenhaDTO senha) throws RegraDeNegocioException {
        Usuario usuarioRecuperado = getLoggedUser();
        if (passwordEncoder.matches(senha.getSenhaAtual(), usuarioRecuperado.getSenha())){
                conferirSenha(senha.getSenhaNova(), senha.getSenhaNovaConf());
                String senhaCripto = passwordEncoder.encode(senha.getSenhaNova());
                usuarioRecuperado.setSenha(senhaCripto);
                usuarioRepository.save(usuarioRecuperado);
        }else{
            throw new RegraDeNegocioException(PASSWORD_NOT_CHECK);
        }
    }

    public void deletarUsuario(DeletarContaDTO confirmacao) throws RegraDeNegocioException {
        if (!confirmacao.getConfirmacao().equals("DELETAR MINHA CONTA")) {
            throw new RegraDeNegocioException(OPERATION_CANCELED);
        }
        Usuario usuarioRecuperado = usuarioRepository.findByUsuarioIdAndAtivoEquals(sessaoUsuarioService.getIdLoggedUserId(), "S");
        if (usuarioRecuperado == null) {
            throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_USUARIO);
        }
        List<Contrato> listaContratos = contratoService.buscarContratoPorLocatario(usuarioRecuperado.getUsuarioId());
        for (Contrato contrato: listaContratos){
            if(contrato.getAtivo().equals("S")){
                throw new RegraDeNegocioException(NOT_FOUND_MESSAGE_CONTRATOS);
            }
        }
        terrenoService.alterarTerrenosUsuarioDeletado(sessaoUsuarioService.getIdLoggedUserId());
        usuarioRecuperado.setAtivo("N");
        usuarioRecuperado.setSenha(passwordEncoder.encode(Random.class.toString()));
        usuarioRepository.save(usuarioRecuperado);
    }

    public ResponseEnderecoDTO alterarEndereco(RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        return enderecoService.alterar(getLoggedUser(), endereco);
    }

    public void conferirSenha(String senha, String senhaConf) throws RegraDeNegocioException {
        if (!senha.equals(senhaConf)){
            throw new RegraDeNegocioException(PASSWORD_NOT_CHECK);
        }
    }

    public ResponseUsuarioDTO alterarUsuarioComToken(RequestUsuarioUpdateDTO usuario) throws RegraDeNegocioException {

        Usuario usuarioExistente = getLoggedUser();

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setSobrenome(usuario.getSobrenome());
        usuarioExistente.setEmail(usuario.getEmail());

        usuarioRepository.save(usuarioExistente);

        return UsuarioMapper.usuarioParaResponseUsuario(usuarioExistente);
    }

}