package services;

import java.time.LocalDate;

import models.Usuario;
import repository.UsuarioRepository;

import util.validar.ValidarModel;

public class UsuarioService {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void cadastrarUsuario(String nome, String sobrenome, String email, String senha, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {
        ValidarModel.NOVOUSUARIO(nome, sobrenome, email, senha, cpf, dataNascimento, sexo, ativo, celular, telefoneFixo);
        Usuario usuario = new Usuario(nome, sobrenome, email, senha, cpf, dataNascimento, sexo, ativo, celular, telefoneFixo);
        usuarioRepository.adicionar(usuario);
    }

    public void alterarUsuario(Integer id, String nome, String sobrenome, String email, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {
        ValidarModel.USUARIOS(nome, sobrenome, email, cpf, dataNascimento, sexo, ativo, celular, telefoneFixo);
        Usuario usuario = new Usuario(nome, sobrenome, email, cpf, dataNascimento, sexo, ativo, celular, telefoneFixo);
        usuarioRepository.alterar(id, usuario);
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

    public Usuario realizarLogin(String email, String senha){
        Usuario usuario =  buscarUsuarioPorEmail(email, senha);

        if (usuario != null) {
            return Usuario.login(
                    usuario.getUsuarioId(),
                    usuario.getNome(),
                    usuario.getSobrenome(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getCpf(),
                    usuario.getDataNascimento(),
                    usuario.getSexo(),
                    usuario.getAtivo(),
                    usuario.getCelular(),
                    usuario.getTelefoneFixo()
            );
        }
        return usuarioRepository.resgatarDadosPorEmail(email,senha);
    }

}
