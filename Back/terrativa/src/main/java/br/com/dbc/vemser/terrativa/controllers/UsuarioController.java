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

    //TODO: ESTE DEVE SER APENAS PARA OS ADMS
    @GetMapping("/{idUsuario}")
    public ResponseEntity<ResponseUsuarioDTO> buscarUsuarioPorId(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        log.info("Buscando usuario por id");
        ResponseUsuarioDTO responseUsuario = usuarioService.buscarUsuarioPorId(idUsuario);
        log.info("Buscou usuario por id");
        return new ResponseEntity<>(responseUsuario, HttpStatus.OK);
    }

    @GetMapping("/logado")
    public ResponseEntity<ResponseUsuarioDTO> buscarUsuarioPorId() throws Exception {
        log.info("Buscando usuario por id");
        ResponseUsuarioDTO responseUsuario = usuarioService.getUserDTO();
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

    @PostMapping("/alterarsenha")
    public ResponseEntity<String> alterarSenha(@RequestBody @Valid RequestSenhaDTO senha) throws Exception{
        return new ResponseEntity<>(usuarioService.alterarSenha(senha), HttpStatus.OK);
    }

    @PostMapping("/deletar-conta")
    public ResponseEntity<String> deletarDados(@RequestBody @Valid DeletarContaDTO confirmacao) throws Exception {
        log.info("Deletando usuário");
        usuarioService.deletarUsuario(confirmacao);
        log.info("Deletou usuário");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/endereco")
    public ResponseEntity<ResponseEnderecoDTO> resgatarEnderecoPorID() throws Exception {
        log.info("Buscando endereço por Id.");
        ResponseEnderecoDTO endereco = usuarioService.resgatarEnderecoUsuario();
        log.info("Endereço Listado!");
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @PutMapping ("/endereco")
    public  ResponseEntity<ResponseEnderecoDTO> atualizarEndereco(@Valid @RequestBody RequestEnderecoCreateDTO endereco)throws Exception {
        log.info("Alterando Endereço.");
        ResponseEnderecoDTO responseEndereco = usuarioService.alterarEndereco(endereco);
        log.info("Endereço Criado!");

        return new ResponseEntity<>(responseEndereco, HttpStatus.OK);
    }


    @PutMapping("/alterar")
    public ResponseEntity<ResponseUsuarioDTO> alterarUsuarioComToken(@Valid @RequestBody RequestUsuarioUpdateDTO usuario) throws Exception {
        log.info("Alterando usuário com base no token");
        ResponseUsuarioDTO responseUsuario = usuarioService.alterarUsuarioComToken(usuario);
        log.info("Usuário alterado com sucesso");
        return ResponseEntity.ok(responseUsuario);
    }


}