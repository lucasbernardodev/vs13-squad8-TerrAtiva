package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.RequestUsuario;
import br.com.dbc.vemser.terrativa.dto.ResponseUsuario;
import br.com.dbc.vemser.terrativa.dto.mappers.UsuarioMapper;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<ResponseUsuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios().stream()
                .map(UsuarioMapper::usuarioParaResponseUsuario).toList();
    }

    public ResponseUsuario buscarUsuarioPorId(Integer id) {
        return UsuarioMapper.usuarioParaResponseUsuario(usuarioRepository.resgatarDadosPorId(id));
    }

    public ResponseUsuario cadastrarUsuario(RequestUsuario usuario) throws Exception {

        return UsuarioMapper.usuarioParaResponseUsuario(
                usuarioRepository.adicionar(
                        UsuarioMapper.requestUsuarioParaUsuario(usuario)));
    }

    public ResponseUsuario alterarUsuario(RequestUsuario usuario) {
        return UsuarioMapper.usuarioParaResponseUsuario(
                usuarioRepository.alterar(
                        UsuarioMapper.requestUsuarioParaUsuario(usuario)));
    }
//    public ResponseUsuario alterarUsuario(RequestUsuario usuario) {
//        Usuario alteracoesUsuario = UsuarioMapper.requestUsuarioParaUsuario(usuario);
//        Usuario usuarioAlterado = usuarioRepository.alterar(alteracoesUsuario);
//        ResponseUsuario responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioAlterado);
//        return responseUsuario;
//    }

    public void deletarUsuario(int id) {
        usuarioRepository.deletar(id);
    }

}