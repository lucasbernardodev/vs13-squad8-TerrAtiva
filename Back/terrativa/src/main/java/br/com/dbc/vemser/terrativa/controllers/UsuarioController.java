package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.interfaces.IUsuarioController;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioLoginDTO;
import br.com.dbc.vemser.terrativa.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Usuários", description = "Endpoints do CRUD de Usuários")
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController implements IUsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<ResponseUsuarioDTO>> listarUsuarios() throws Exception {
        log.info("Buscando todos os usuários");
        List<ResponseUsuarioDTO> responseUsuario = usuarioService.listarUsuarios();
        log.info("Buscou todos os usuários");
        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ResponseUsuarioDTO> buscarUsuarioPorId(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        log.info("Buscando usuario por id");
        ResponseUsuarioDTO responseUsuario = usuarioService.buscarUsuarioPorId(idUsuario);
        log.info("Buscou usuario por id");
        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseUsuarioDTO> cadastrarUsuario(
            @Valid @RequestBody RequestUsuarioCreateDTO usuario) throws Exception {
        log.info("Criando usuário");
        ResponseUsuarioDTO responseUsuario = usuarioService.cadastrarUsuario(usuario);
        log.info("Criou usuário");
        return new ResponseEntity<>(responseUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseUsuarioDTO> loginUsuario(
            @Valid @RequestBody RequestUsuarioLoginDTO usuario) throws Exception {
        log.info("Logando usuário");
        ResponseUsuarioDTO responseUsuario = usuarioService.loginUsuario(usuario);
        log.info("Logou usuário");
        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<ResponseUsuarioDTO> atualizarUsuario(@PathVariable("idUsuario") Integer idUsuario,
                                                               @Valid @RequestBody RequestUsuarioCreateDTO usuario) throws Exception {
        log.info("Atualizando usuário");
        usuario.setUsuarioId(idUsuario);
        ResponseUsuarioDTO responseUsuario = usuarioService.alterarUsuario(usuario);
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