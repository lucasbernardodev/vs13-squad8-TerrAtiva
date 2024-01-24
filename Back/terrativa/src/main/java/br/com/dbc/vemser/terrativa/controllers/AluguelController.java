package br.com.dbc.vemser.terrativa.controllers;


import br.com.dbc.vemser.terrativa.dto.*;
import br.com.dbc.vemser.terrativa.exceptions.DataNotFoundException;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import br.com.dbc.vemser.terrativa.services.AluguelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "Aluguel", description = "Endpoints CRUD aluguel")
@RequestMapping("/aluguel") // localhost:8081/aluguel

public class AluguelController {
        private final AluguelService aluguelService;

        @GetMapping("/{id}") // GET localhost:8081/aluguel/1
        public ResponseEntity<ResponseAluguel> listarPorId(
                @PathVariable("id") Integer id) throws Exception {
            log.info("Buscando Aluguel por Id.");
            ResponseAluguel aluguel =aluguelService.resgatarAluguelPorId(id);
            log.info("Aluguel Listado!");
            return new ResponseEntity<>(aluguel, HttpStatus.OK);
        }

        @PostMapping // POST localhost:8081/aluguel
        public ResponseEntity<ResponseAluguel> criar(@Valid @RequestBody RequestAluguel aluguel) throws Exception {
            log.info("Criando Aluguel.");
            ResponseAluguel responseAluguel = aluguelService.criar(aluguel);
            log.info("Aluguel Criado!");
            return new ResponseEntity<>(responseAluguel, HttpStatus.CREATED);
        }
        @PutMapping ("/{id}")// PUT localhost:8081/aluguel/1
        public ResponseEntity<ResponseAluguel> alterar(@PathVariable("id") Integer idUsuario,
                      @Valid @RequestBody RequestAluguel aluguel, RequestUsuario usuario) throws Exception {
            log.info("Criando Aluguel ");
            usuario.setUsuarioId(idUsuario);
            ResponseAluguel responseAluguel = aluguelService.alterar(aluguel);
            log.info("Aluguel Criado!");
            return new ResponseEntity<>(responseAluguel, HttpStatus.OK);
        }
    @DeleteMapping("/{id}")// DELETE localhost:8081/aluguel/1
    public ResponseEntity<String> deletar(@PathVariable int id) throws Exception{
        log.info("Deletando Aluguel.");
                aluguelService.deletar(id);
        log.info("Aluguel deletado com Sucesso");
        return new ResponseEntity<>("Aluguel deletado com Sucesso", HttpStatus.OK);
    }
}
