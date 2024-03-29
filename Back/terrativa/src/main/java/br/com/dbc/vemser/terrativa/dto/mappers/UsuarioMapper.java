package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.requests.RequestAdminDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseAdminDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.entity.Usuario;

public class UsuarioMapper {

    public static ResponseUsuarioDTO usuarioParaResponseUsuario(Usuario usuario){
        ResponseUsuarioDTO responseUsuario = new ResponseUsuarioDTO();
        responseUsuario.setUsuarioId(usuario.getUsuarioId());
        responseUsuario.setNome(usuario.getNome());
        responseUsuario.setSobrenome(usuario.getSobrenome());
        responseUsuario.setEmail(usuario.getEmail());
        responseUsuario.setCpf(usuario.getCpf());
        responseUsuario.setDataNascimento(usuario.getDataNascimento());
        responseUsuario.setSexo(usuario.getSexo());
        responseUsuario.setCelular(usuario.getCelular());
        responseUsuario.setTelefoneFixo(usuario.getTelefoneFixo());
        return responseUsuario;
    }

    public static Usuario requestUsuarioParaUsuario(RequestUsuarioCreateDTO requestUsuario){
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(requestUsuario.getUsuarioId());
        usuario.setNome(requestUsuario.getNome());
        usuario.setSobrenome(requestUsuario.getSobrenome());
        usuario.setEmail(requestUsuario.getEmail());
        usuario.setSenha(requestUsuario.getSenha());
        usuario.setCpf(requestUsuario.getCpf());
        usuario.setDataNascimento(requestUsuario.getDataNascimento());
        usuario.setAtivo(requestUsuario.getAtivo());
        usuario.setSexo(requestUsuario.getSexo());
        usuario.setCelular(requestUsuario.getCelular());
        usuario.setTelefoneFixo(requestUsuario.getTelefoneFixo());
        return usuario;
    }

    public static ResponseAdminDTO usuarioParaRequestAdminUsuario(Usuario usuario){
        ResponseAdminDTO responseUsuario = new ResponseAdminDTO();
        responseUsuario.setUsuarioId(usuario.getUsuarioId());
        responseUsuario.setNome(usuario.getNome());
        responseUsuario.setSobrenome(usuario.getSobrenome());
        responseUsuario.setEmail(usuario.getEmail());
        responseUsuario.setCpf(usuario.getCpf());
        responseUsuario.setDataNascimento(usuario.getDataNascimento());
        responseUsuario.setSexo(usuario.getSexo());
        responseUsuario.setCelular(usuario.getCelular());
        responseUsuario.setTelefoneFixo(usuario.getTelefoneFixo());
        return responseUsuario;
    }

    public static Usuario usuarioParaRequestAdminUsuario(RequestAdminDTO usuario){
        Usuario responseUsuario = new Usuario();
        responseUsuario.setUsuarioId(usuario.getUsuarioId());
        responseUsuario.setNome(usuario.getNome());
        responseUsuario.setSobrenome(usuario.getSobrenome());
        responseUsuario.setEmail(usuario.getEmail());
        responseUsuario.setSenha(usuario.getSenha());
        responseUsuario.setCpf(usuario.getCpf());
        responseUsuario.setDataNascimento(usuario.getDataNascimento());
        responseUsuario.setSexo(usuario.getSexo());
        responseUsuario.setCelular(usuario.getCelular());
        responseUsuario.setTelefoneFixo(usuario.getTelefoneFixo());
        return responseUsuario;
    }
}
