package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.time.LocalDate;

@Validated
@Slf4j
@RestController
@RequestMapping("/usuario") // localhost:8081/pessoa
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

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> deletarDados(@PathVariable Integer idUsuario) throws Exception {
        usuarioService.deletarUsuario(idUsuario);
        return new ResponseEntity<>("Usuário deletado com Sucesso", HttpStatus.OK);
    }
}