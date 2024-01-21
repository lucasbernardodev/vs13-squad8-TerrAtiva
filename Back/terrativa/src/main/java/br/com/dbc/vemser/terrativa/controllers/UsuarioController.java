package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.RequestUsuario;
import br.com.dbc.vemser.terrativa.dto.ResponseUsuario;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario") // localhost:8081/usuario
public class UsuarioController {

    public final UsuarioService usuarioService;


    @PostMapping // POST localhost:8081/usuario
    public ResponseEntity<ResponseUsuario> cadastrarUsuario(
            @Valid @RequestBody RequestUsuario usuario) throws Exception {

        log.info("Criando usuário");
        ResponseUsuario responseUsuario = usuarioService.cadastrarUsuario(usuario);
        log.info("Criou usuário");

        return new ResponseEntity<>(responseUsuario, HttpStatus.CREATED);
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