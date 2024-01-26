package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.dto.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.services.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "Enderecos", description = "Endpoints CRUD enderecos")
@RequestMapping("/endereco") // localhost:8081/endereco
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping ("/todos")// GET localhost:8081/endereco/todos
    public ResponseEntity<List<ResponseEnderecoDTO>> listarEndereco() throws Exception {
        log.info("Buscando todos os endereço");
        List<ResponseEnderecoDTO> responseEndereco = enderecoService.listarEnderecos();
        log.info("Buscou todos os endereços");
        return new ResponseEntity<>(responseEndereco, HttpStatus.OK);
    }

    @GetMapping("/{id}")// localhost:8081/endereco/1
    public ResponseEntity<ResponseEnderecoDTO> resgatarEnderecoPorID(@PathVariable("id") Integer id) throws Exception {
        log.info("Buscando endereço por Id.");
        ResponseEnderecoDTO endereco = enderecoService.resgatarPorId(id);
        log.info("Endereço Listado!");
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @PostMapping // POST localhost:8081/endereco
    public  ResponseEntity<ResponseEnderecoDTO> cadastrarEndereco(
            @Valid @RequestBody RequestEnderecoCreateDTO endereco) throws Exception {
            log.info("Criando Endereço.");
            ResponseEnderecoDTO responseEndereco = enderecoService.adicionarEndereco(endereco);
            log.info("Endereço Criado!");

            return new ResponseEntity<>(responseEndereco, HttpStatus.OK);
    }
    @PutMapping ("/{id}") // PUT localhost:8081/endereco/1
    public  ResponseEntity<ResponseEnderecoDTO> atualizarEndereco(@PathVariable("id") Integer idUsuario,
                                                                  @Valid @RequestBody RequestEnderecoCreateDTO endereco, RequestUsuarioCreateDTO usuario)throws Exception {
             log.info("Alterando Endereço.");
                usuario.setUsuarioId(idUsuario);
                ResponseEnderecoDTO responseEndereco = enderecoService.alterar(endereco);
        log.info("Endereço Criado!");

        return new ResponseEntity<>(responseEndereco, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")// DELETE localhost:8081/endereco/1
    public ResponseEntity<String> deletarEndereco(@PathVariable Integer id) throws Exception {
        log.info("Deletando Endereço.");
            enderecoService.deletar(id);
        log.info("Endereço Deletado!");
            return new ResponseEntity<>("Endereço deletado com Sucesso", HttpStatus.OK);
    }
}
