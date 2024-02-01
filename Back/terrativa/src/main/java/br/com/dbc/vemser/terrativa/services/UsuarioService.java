package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.UsuarioMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioLoginDTO;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    public List<ResponseUsuarioDTO> listarUsuarios() throws Exception{
        return usuarioRepository.findAllByAtivoEquals("S").stream()
                .map(UsuarioMapper::usuarioParaResponseUsuario).toList();
    }

    public ResponseUsuarioDTO buscarUsuarioPorId(Integer id) throws Exception {
        return UsuarioMapper.usuarioParaResponseUsuario(usuarioRepository.findAllByUsuarioIdAndAtivoEquals(id, "S"));
    }

    public ResponseUsuarioDTO cadastrarUsuario(RequestUsuarioCreateDTO usuario) throws Exception {
        usuario.setAtivo("S");
        usuario.setUsuarioId(null);
         ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(
                usuarioRepository.save(
                        UsuarioMapper.requestUsuarioParaUsuario(usuario)));
        //emailService.sendEmailUsuario(responseUsuario, 1);
        return responseUsuario;
    }

    public ResponseUsuarioDTO alterarUsuario(Integer idUsuario, RequestUsuarioCreateDTO usuario) throws Exception{
        Usuario usuarioRecuperado = usuarioRepository.findById(idUsuario).get();
        usuario.setUsuarioId(usuarioRecuperado.getUsuarioId());
        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(
                usuarioRepository.save(
                        UsuarioMapper.requestUsuarioParaUsuario(usuario)));
//        emailService.sendEmailUsuario(responseUsuario, 2);
        return responseUsuario;
    }

    public void deletarUsuario(int id) throws Exception {
        Usuario usuarioRecuperado = usuarioRepository.findAllByUsuarioIdAndAtivoEquals(id, "S");
        usuarioRecuperado.setAtivo("N");
        usuarioRepository.save(usuarioRecuperado);
//        emailService.sendEmailUsuario(responseUsuarioDTO, 3);
    }

    public ResponseUsuarioDTO loginUsuario(RequestUsuarioLoginDTO usuario) {
        Usuario usuarioLogin = usuarioRepository.findByEmailAndSenhaAndAtivoEquals(usuario.getEmail(), usuario.getSenha(), "S");
        return UsuarioMapper.usuarioParaResponseUsuario(usuarioLogin);
    }
}