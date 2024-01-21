package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.RequestUsuario;
import br.com.dbc.vemser.terrativa.dto.ResponseUsuario;
import br.com.dbc.vemser.terrativa.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario") // localhost:8081/usuario
public class UsuarioController {

    public final UsuarioService usuarioService;

    @GetMapping // GET localhost:8081/usuario
    public ResponseEntity<List<ResponseUsuario>> listarUsuarios() throws Exception {
        log.info("Buscando todos os usuários");
        List<ResponseUsuario> responseUsuario = usuarioService.listarUsuarios();
        log.info("Buscou todos os usuários");
        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}") // GET localhost:8081/usuario/1
    public ResponseEntity<ResponseUsuario> buscarUsuarioPorId(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        log.info("Buscando usuario por id");
        ResponseUsuario responseUsuario = usuarioService.buscarUsuarioPorId(idUsuario);
        log.info("Buscou usuario por id");
        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
    }

    @PostMapping // POST localhost:8081/usuario
    public ResponseEntity<ResponseUsuario> cadastrarUsuario(
            @Valid @RequestBody RequestUsuario usuario) throws Exception {

        log.info("Criando usuário");
        ResponseUsuario responseUsuario = usuarioService.cadastrarUsuario(usuario);
        log.info("Criou usuário");

        return new ResponseEntity<>(responseUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}") // PUT localhost:8081/usuario/1000
    public ResponseEntity<ResponseUsuario> atualizarUsuario(@PathVariable("idUsuario") Integer idUsuario,
                                                            @Valid @RequestBody RequestUsuario usuario) {
            log.info("Atualizando usuário");
            usuario.setUsuarioId(idUsuario);
            ResponseUsuario responseUsuario = usuarioService.alterarUsuario(usuario);
            log.info("Atualizou usuário");
        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> deletarDados(@PathVariable Integer idUsuario) throws Exception {
        log.info("Deletando usuário");
        usuarioService.deletarUsuario(idUsuario);
        log.info("Deletou usuário");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}