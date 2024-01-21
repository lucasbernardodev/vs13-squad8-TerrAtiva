package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.RequestEndereco;
import br.com.dbc.vemser.terrativa.dto.RequestUsuario;
import br.com.dbc.vemser.terrativa.dto.ResponseEndereco;
import br.com.dbc.vemser.terrativa.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco") // localhost:8081/endereco
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEndereco> resgatarEnderecoPorID(@PathVariable Integer id) throws Exception {
        log.info("Buscando endereço por Id.");
        ResponseEndereco endereco = enderecoService.resgatarPorId(id);
        log.info("Endereço Listado!");
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @PostMapping // POST localhost:8081/endereco
    public  ResponseEntity<ResponseEndereco> cadastrarEndereco(
            @Valid @RequestBody RequestEndereco endereco) throws Exception {
            log.info("Criando Endereço.");
            ResponseEndereco responseEndereco = enderecoService.adicionarEndereco(endereco);
            log.info("Endereço Criado!");

            return new ResponseEntity<>(responseEndereco, HttpStatus.OK);
    }
    @PutMapping ("/{id}") // PUT localhost:8081/endereco/1
    public  ResponseEntity<ResponseEndereco> atualizarEndereco(@Valid @RequestBody  RequestEndereco endereco)throws Exception {
             log.info("Alterando Endereço.");
                ResponseEndereco responseEndereco = enderecoService.alterar(endereco);
        log.info("Endereço Criado!");

        return new ResponseEntity<>(responseEndereco, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEndereco(@PathVariable Integer id) throws Exception {
        log.info("Deletando Endereço.");
            enderecoService.deletar(id);
        log.info("Endereço Deletado!");
            return new ResponseEntity<>("Endereço deletado com Sucesso", HttpStatus.OK);
    }
}
