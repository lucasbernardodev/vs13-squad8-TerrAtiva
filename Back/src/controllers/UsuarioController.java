package controllers;

import infra.exceptions.*;
import models.Usuario;
import services.UsuarioService;

import java.time.LocalDate;

public class UsuarioController {

    UsuarioService usuarioService = new UsuarioService();

    public String cadastrarUsuario(String nome, String sobrenome, String email, String senha, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {
        try {
            usuarioService.cadastrarUsuario(nome, sobrenome, email, senha, cpf, dataNascimento, sexo, ativo, celular, telefoneFixo);
            return "Usuário cadastrado com sucesso!";
        } catch (InvalidParamException e) {
            return e.getMessage();
        } catch (DataFormatInvalidException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String atualizarUsuario(int id, String nome, String sobrenome, String email, String senha, String cpf, LocalDate dataNascimento, String sexo, String ativo, String celular, String telefoneFixo) {
        try {
            usuarioService.alterarUsuario(id, nome, sobrenome, email, senha, cpf, dataNascimento, sexo, ativo, celular, telefoneFixo);
            return "Dados do Usuário alterados com Sucesso!";

        } catch (InvalidParamException e) {
            return e.getMessage();
        } catch (DataFormatInvalidException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public String resgatarUsuarioPorID(int id) {
        try {
            return usuarioService.buscarUsuario(id).toString();
        } catch (DataNotFoundException e) {
            return e.getMessage();
        } catch (DbException e) {
            return e.getMessage();
        }
    }

    public Usuario resgatarUsuarioPorEmail(String email,String senha) {
        try {
            return usuarioService.buscarUsuarioPorEmail(email,senha);
        } catch (DataNotFoundException e) {
            throw new DataFormatInvalidException(e.getMessage());
        } catch (DbException e) {
            throw new DbException(e.getMessage());
        }
    }

    public String deletarDados(int idUsuario) {
        try {
            usuarioService.deletarUsuario(idUsuario);
            return "Usuário deletado com Sucesso";

        } catch (DbException e) {
            return e.getMessage();
        } catch (UnauthorizedOperationException e) {
            return e.getMessage();
        }
    }
}