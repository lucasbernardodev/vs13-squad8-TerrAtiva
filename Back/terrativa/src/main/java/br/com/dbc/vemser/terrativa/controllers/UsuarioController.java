package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.interfaces.IUsuarioController;
import br.com.dbc.vemser.terrativa.dto.requests.*;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Tag(name = "Usuários", description = "Endpoints do CRUD de Usuários")
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController implements IUsuarioController {

    private final UsuarioService usuarioService;

    //COMENTADO PORQUE NÃO É PRA SER USADO, MAS FOI MANTIDO POIS TALVEZ SEJA NECESSÁRIO NO FUTURO
//    @GetMapping
//    public ResponseEntity<List<ResponseUsuarioDTO>> listarUsuarios() throws Exception {
//        log.info("Buscando todos os usuários");
//        List<ResponseUsuarioDTO> responseUsuario = usuarioService.listarUsuarios();
//        log.info("Buscou todos os usuários");
//        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
//    }

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

//    @PostMapping("/login")
//    public ResponseEntity<ResponseUsuarioDTO> loginUsuario(
//            @Valid @RequestBody RequestUsuarioLoginDTO usuario) throws Exception {
//        log.info("Logando usuário");
//        ResponseUsuarioDTO responseUsuario = usuarioService.loginUsuario(usuario);
//        log.info("Logou usuário");
//        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
//    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<ResponseUsuarioDTO> atualizarUsuario(@PathVariable("idUsuario") Integer idUsuario,
                                                               @Valid @RequestBody RequestUsuarioUpdateDTO usuario) throws Exception {
        log.info("Atualizando usuário");
        ResponseUsuarioDTO responseUsuario = usuarioService.alterarUsuario(idUsuario, usuario);
        log.info("Atualizou usuário");
        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
    }

    @PostMapping("/{idUsuario}/alterarsenha")
    public ResponseEntity<String> alterarSenha(@PathVariable("idUsuario") Integer idusuario, @RequestBody @Valid RequestSenhaDTO senha) throws Exception{
        usuarioService.alterarSenha(idusuario, senha);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> deletarDados(@PathVariable Integer idUsuario) throws Exception {
        log.info("Deletando usuário");
        usuarioService.deletarUsuario(idUsuario);
        log.info("Deletou usuário");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/endereco")
    public ResponseEntity<ResponseEnderecoDTO> resgatarEnderecoPorID(@PathVariable("id") Integer id) throws Exception {
        log.info("Buscando endereço por Id.");
        ResponseEnderecoDTO endereco = usuarioService.resgatarPorId(id);
        log.info("Endereço Listado!");
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @PutMapping ("/{id}/endereco")
    public  ResponseEntity<ResponseEnderecoDTO> atualizarEndereco(@PathVariable("id") Integer id,
                                                                  @Valid @RequestBody RequestEnderecoCreateDTO endereco)throws Exception {
        log.info("Alterando Endereço.");
        ResponseEnderecoDTO responseEndereco = usuarioService.alterarEndereco(id, endereco);
        log.info("Endereço Criado!");

        return new ResponseEntity<>(responseEndereco, HttpStatus.OK);
    }
}