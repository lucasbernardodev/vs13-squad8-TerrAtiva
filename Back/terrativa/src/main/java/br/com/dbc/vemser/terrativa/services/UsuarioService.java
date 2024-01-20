package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import br.com.dbc.vemser.terrativa.util.validar.ValidarModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario(String nome, String sobrenome, String email, String senha, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {
        ValidarModel.NOVOUSUARIO(nome, sobrenome, email, senha, cpf, dataNascimento, sexo, celular, telefoneFixo);
        Usuario usuario = new Usuario(1, nome, sobrenome, email, senha, cpf, dataNascimento, sexo, celular, telefoneFixo);
        usuarioRepository.adicionar(usuario);
    }

    public void alterarUsuario(Integer id, String nome, String sobrenome, String email, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {
        ValidarModel.USUARIOS(nome, sobrenome, email, cpf, dataNascimento, sexo, celular, telefoneFixo);
        usuarioRepository.alterar(new Usuario(id, nome, sobrenome, email, cpf, dataNascimento, sexo, celular, telefoneFixo));
    }

    public void deletarUsuario(int id) {
        usuarioRepository.deletar(id);
    }

    public Usuario buscarUsuario(int id) {
        return usuarioRepository.resgatarDadosPorId(id);
    }

    public Usuario buscarUsuarioPorEmail(String email, String senha){
        return usuarioRepository.resgatarDadosPorEmail(email,senha);
    }

}
