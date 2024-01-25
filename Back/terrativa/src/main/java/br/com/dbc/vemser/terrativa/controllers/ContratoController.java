package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.documentacao.IContratoController;
import br.com.dbc.vemser.terrativa.dto.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.services.ContratoService;
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
@Tag(name = "Contrato", description = "Endpoints do CRUD de Contrato")
@RequiredArgsConstructor
@RequestMapping("/contrato") // localhost:8081/contrato
public class ContratoController implements IContratoController {
    private final ContratoService contratoService;
    @GetMapping("/{id}")// localhost:8081/contrato/1
    public ResponseEntity<ResponseContratoDTO> resgatarContratoPorID(
            @PathVariable("id") Integer id) throws Exception {
        log.info("Buscando contrato por Id.");
        ResponseContratoDTO contrato = contratoService.resgatarContratoPorId(id);
        log.info("Contrato Listado!");
        return new ResponseEntity<>(contrato, HttpStatus.OK);
           }
    @PostMapping // POST localhost:8081/contrato
    public ResponseEntity<ResponseContratoDTO> criarContrato(@Valid @RequestBody RequestContratoCreateDTO contrato) throws Exception {
        log.info("Criando Contrato.");
        ResponseContratoDTO responseContrato = contratoService.criar(contrato);
        log.info("Contrato Criado!");
        return new ResponseEntity<>(responseContrato, HttpStatus.CREATED);
    }
    @PutMapping("/{id}") // PUT localhost:8081/contrato/1
    public ResponseEntity<ResponseContratoDTO> atualizarContrato(@PathVariable("id") Integer id,
                                                                 @Valid @RequestBody RequestContratoCreateDTO contrato)throws Exception {
        log.info("Alterando Contrato.");
        ResponseContratoDTO responseContrato = contratoService.alterar(contrato);
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
