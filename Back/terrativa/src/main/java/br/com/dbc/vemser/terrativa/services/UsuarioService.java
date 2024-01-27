package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.UsuarioMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioLoginDTO;
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
        return usuarioRepository.listarUsuarios().stream()
                .map(UsuarioMapper::usuarioParaResponseUsuario).toList();
    }

    public ResponseUsuarioDTO buscarUsuarioPorId(Integer id) throws Exception {
        return UsuarioMapper.usuarioParaResponseUsuario(usuarioRepository.resgatarDadosPorId(id));
    }

    public ResponseUsuarioDTO cadastrarUsuario(RequestUsuarioCreateDTO usuario) throws Exception {
         ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(
                usuarioRepository.adicionar(
                        UsuarioMapper.requestUsuarioParaUsuario(usuario)));
//        emailService.sendEmailUsuario(responseUsuario, 1);
        return responseUsuario;
    }

    public ResponseUsuarioDTO alterarUsuario(RequestUsuarioCreateDTO usuario) throws Exception{
        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(
                usuarioRepository.alterar(
                        UsuarioMapper.requestUsuarioParaUsuario(usuario)));
//        emailService.sendEmailUsuario(responseUsuario, 2);
        return responseUsuario;

    }

    public void deletarUsuario(int id) throws Exception {
        ResponseUsuarioDTO responseUsuarioDTO = buscarUsuarioPorId(id);
        usuarioRepository.deletar(id);
//        emailService.sendEmailUsuario(responseUsuarioDTO, 3);
    }

    public ResponseUsuarioDTO loginUsuario(RequestUsuarioLoginDTO usuario) {
        return UsuarioMapper.usuarioParaResponseUsuario(usuarioRepository.loginUsuario(usuario));
    }
}