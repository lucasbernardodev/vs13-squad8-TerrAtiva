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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final CargoRepository cargoRepository;


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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        conferirSenha(usuario.getSenha(), usuario.getSenhaConf());
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setAtivo("S");
        usuario.setUsuarioId(null);
        RequestEnderecoCreateDTO endereco = usuario.getEndereco();
        Usuario usuarioEntity = UsuarioMapper.requestUsuarioParaUsuario(usuario);
        usuarioEntity.setCargos(cargoRepository.findCargosByIdCargo(2));
        Usuario usuarioSalvo = usuarioRepository.save(usuarioEntity);
        endereco.setUsuarioID(usuarioSalvo.getUsuarioId());
        ResponseEnderecoDTO enderecoDTO = enderecoService.adicionarEndereco(endereco);
        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioSalvo);
        responseUsuario.setEndereco(enderecoDTO);
        //emailService.sendEmailUsuario(responseUsuario, 1);
        return responseUsuario;

    }

    public ResponseAdminDTO criarAdmin(RequestAdminDTO admin) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        conferirSenha(admin.getSenha(), admin.getSenhaConf());
        admin.setSenha(bCryptPasswordEncoder.encode(admin.getSenha()));
        Usuario usuario = UsuarioMapper.usuarioParaRequestAdminUsuario(admin);
        usuario.setAtivo("S");
        usuario.setCargos(cargoRepository.findCargosByIdCargo(1));
        Usuario usuarioCadastrado = usuarioRepository.save(usuario);
        return UsuarioMapper.usuarioParaRequestAdminUsuario(usuarioCadastrado);
    }

    public ResponseUsuarioDTO alterarUsuario(Integer idUsuario, RequestUsuarioUpdateDTO usuario) throws RegraDeNegocioException {
        buscarUsuarioPorId(idUsuario);
        usuario.setUsuarioId(idUsuario);
        Usuario usuarioAtualizado = UsuarioMapper.requestUsuarioParaUsuario(usuario);
        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioRepository.save(usuarioAtualizado));
//        emailService.sendEmailUsuario(responseUsuario, 2);
        return responseUsuario;
    }

    public String alterarSenha(Integer idUsuario, RequestSenhaDTO senha) throws Exception{
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Usuario usuarioRecuperado = findById(idUsuario).get();
        if (bCryptPasswordEncoder.matches(senha.getSenhaAtual(), usuarioRecuperado.getSenha())){
            conferirSenha(senha.getSenhaNova(), senha.getSenhaNovaConf());
            String senhaCripto = bCryptPasswordEncoder.encode(senha.getSenhaNova());
            usuarioRecuperado.setSenha(senhaCripto);
            usuarioRepository.save(usuarioRecuperado);
            return "Senha alterada com sucesso!";
        }else{
            throw new RegraDeNegocioException("Senha apresentada não confere com a atual");
        }
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

//Login
    public Optional<Usuario> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Optional<Usuario> findByEmail(String username) {
        return usuarioRepository.findByEmail(username);
    }

    public Optional<Usuario> getLoggedUser() throws RegraDeNegocioException {
        return findById(getIdLoggedUser());
    }

    public Optional<Usuario> findByEmailAndSenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public ResponseEnderecoDTO resgatarPorId(Integer id) throws RegraDeNegocioException {
        buscarUsuarioPorId(id);
        return enderecoService.resgatarPorId(id);
    }


    public ResponseEnderecoDTO alterarEndereco(Integer id, RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        buscarUsuarioPorId(id);
        return enderecoService.alterar(id, endereco);
    }

    public String conferirSenha(String senha, String senhaConf) throws RegraDeNegocioException {
        if(senha.equals(senhaConf)){
            return null;
        }else {
            throw new RegraDeNegocioException("As senhas informadas não são iguais!");
        }
    }
}