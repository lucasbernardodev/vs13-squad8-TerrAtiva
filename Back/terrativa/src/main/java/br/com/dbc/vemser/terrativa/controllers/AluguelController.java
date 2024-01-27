package br.com.dbc.vemser.terrativa.controllers;


import br.com.dbc.vemser.terrativa.dto.reponses.ResponseAluguelDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestAluguelCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.services.AluguelService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Aluguel", description = "Endpoints CRUD aluguel")
@RequestMapping("/aluguel")

public class AluguelController {
        private final AluguelService aluguelService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAluguelDTO> listarPorId (
            @PathVariable("id") Integer id) throws Exception {
        log.info("Buscando Aluguel por Id.");
        ResponseAluguelDTO aluguel =aluguelService.resgatarAluguelPorId(id);
        log.info("Aluguel Listado!");
        return new ResponseEntity<>(aluguel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseAluguelDTO> criar(@Valid @RequestBody RequestAluguelCreateDTO aluguel) {
        log.info("Criando Aluguel.");
        ResponseAluguelDTO responseAluguel = aluguelService.criar(aluguel);
        log.info("Aluguel Criado!");
        return new ResponseEntity<>(responseAluguel, HttpStatus.CREATED);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<ResponseAluguelDTO> alterar(@PathVariable("id") Integer idUsuario,
                                                      @Valid @RequestBody RequestAluguelCreateDTO aluguel, RequestUsuarioCreateDTO usuario) throws Exception {
        log.info("Criando Aluguel ");
        usuario.setUsuarioId(idUsuario);
        ResponseAluguelDTO responseAluguel = aluguelService.alterar(aluguel);
        log.info("Aluguel Criado!");
        return new ResponseEntity<>(responseAluguel, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) throws Exception{
        log.info("Deletando Aluguel.");
                aluguelService.deletar(id);
        log.info("Aluguel deletado com Sucesso");
        return new ResponseEntity<>("Aluguel deletado com Sucesso", HttpStatus.OK);
    }
}
