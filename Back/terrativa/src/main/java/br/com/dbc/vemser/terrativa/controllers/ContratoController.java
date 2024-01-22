package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.*;
import br.com.dbc.vemser.terrativa.exceptions.DbException;
import br.com.dbc.vemser.terrativa.exceptions.UnauthorizedOperationException;
import br.com.dbc.vemser.terrativa.services.ContratoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/contrato") // localhost:8081/endereco
public class ContratoController {
    private final ContratoService contratoService;
    @GetMapping("/{id}")// localhost:8081/contrato/1
    public ResponseEntity<ResponseContrato> resgatarContratoPorID(
            @PathVariable("id") Integer id) throws Exception {
        log.info("Buscando contrato por Id.");
        ResponseContrato contrato = contratoService.resgatarContratoPorId(id);
        log.info("Contrato Listado!");
        return new ResponseEntity<>(contrato, HttpStatus.OK);
           }
    @PutMapping("/{id}") // PUT localhost:8081/contrato/1
    public ResponseEntity<ResponseContrato> atualizarContrato(@PathVariable("id") Integer id,
                                          @Valid @RequestBody RequestContrato contrato)throws Exception {
        log.info("Alterando Contrato.");
        ResponseContrato responseContrato = contratoService.alterar(contrato);
        log.info("Contrato Criado!");
        return new ResponseEntity<>(responseContrato, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")// DELETE localhost:8081/contrato/1
    public ResponseEntity<String>deletarContrato(@PathVariable Integer id)throws Exception {
        log.info("Deletando Contrato.");
        contratoService.deletar(id);
        log.info("Contrato Deletado!");
        return new ResponseEntity<>("Contrato deletado com Sucesso", HttpStatus.OK);
    }}
