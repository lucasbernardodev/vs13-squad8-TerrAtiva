package br.com.dbc.vemser.terrativa.backup.controllers;

import br.com.dbc.vemser.terrativa.models.Usuario;
import br.com.dbc.vemser.terrativa.services.UsuarioService;


import java.time.LocalDate;

public class UsuarioController {

    UsuarioService usuarioService = new UsuarioService();

    public String cadastrarUsuario(String nome, String sobrenome, String email, String senha, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {

            usuarioService.cadastrarUsuario(nome, sobrenome, email, senha, cpf, dataNascimento, sexo, ativo, celular, telefoneFixo);
            return "Usuário cadastrado com sucesso!";
    }

    public String atualizarUsuario(Integer id, String nome, String sobrenome, String email, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {

            usuarioService.alterarUsuario(id, nome, sobrenome, email, cpf, dataNascimento, sexo, ativo, celular, telefoneFixo);
            return "Dados do Usuário alterados com Sucesso!";

    }

    public String resgatarUsuario(int id) {

            return usuarioService.buscarUsuario(id).toString();

    }

    public Usuario resgatarUsuario(String email, String senha) {

            return usuarioService.buscarUsuarioPorEmail(email,senha);

    }

    public String deletarDados(int idUsuario) {

            usuarioService.deletarUsuario(idUsuario);
            return "Usuário deletado com Sucesso";


    }
}