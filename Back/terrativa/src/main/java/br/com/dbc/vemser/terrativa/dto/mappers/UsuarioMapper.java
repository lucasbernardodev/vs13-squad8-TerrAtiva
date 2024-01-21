package br.com.dbc.vemser.terrativa.dto.mappers;

import br.com.dbc.vemser.terrativa.dto.RequestUsuario;
import br.com.dbc.vemser.terrativa.dto.ResponseUsuario;
import br.com.dbc.vemser.terrativa.entity.Usuario;

import java.time.LocalDate;

public class UsuarioMapper {

    public static ResponseUsuario usuarioParaResponseUsuario(Usuario usuario){
        ResponseUsuario responseUsuario = new ResponseUsuario();
        responseUsuario.setUsuarioId(usuario.getUsuarioId());
        responseUsuario.setNome(usuario.getNome());
        responseUsuario.setSobrenome(usuario.getSobrenome());
        responseUsuario.setEmail(usuario.getEmail());
        responseUsuario.setSenha(usuario.getSenha());
        responseUsuario.setCpf(usuario.getCpf());
        responseUsuario.setDataNascimento(usuario.getDataNascimento());
        responseUsuario.setSexo(usuario.getSexo());
        responseUsuario.setAtivo(usuario.getAtivo());
        responseUsuario.setCelular(usuario.getCelular());
        responseUsuario.setTelefoneFixo(usuario.getTelefoneFixo());
        return responseUsuario;
    }

    public static Usuario requestUsuarioParaUsuario(RequestUsuario requestUsuario){
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(requestUsuario.getUsuarioId());
        usuario.setNome(requestUsuario.getNome());
        usuario.setSobrenome(requestUsuario.getSobrenome());
        usuario.setEmail(requestUsuario.getEmail());
        usuario.setSenha(requestUsuario.getSenha());
        usuario.setCpf(requestUsuario.getCpf());
        usuario.setDataNascimento(requestUsuario.getDataNascimento());
        usuario.setSexo(requestUsuario.getSexo());
        usuario.setAtivo(requestUsuario.getAtivo());
        usuario.setCelular(requestUsuario.getCelular());
        usuario.setTelefoneFixo(requestUsuario.getTelefoneFixo());
        return usuario;
    }
}
