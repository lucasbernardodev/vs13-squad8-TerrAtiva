package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.interfaces.IContratoController;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
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
@RequestMapping("/contrato")
public class ContratoController implements IContratoController {
    private final ContratoService contratoService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContratoDTO> resgatarContratoPorID(
            @PathVariable("id") Integer id) throws Exception {
        log.info("Buscando contrato por Id.");
        ResponseContratoDTO contrato = contratoService.resgatarContratoPorId(id);
        log.info("Contrato Listado!");
        return new ResponseEntity<>(contrato, HttpStatus.OK);
           }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseContratoDTO> atualizarContrato(@PathVariable("id") Integer id,
                                                                 @Valid @RequestBody RequestContratoCreateDTO contrato)throws Exception {
        log.info("Alterando Contrato.");
        ResponseContratoDTO responseContrato = contratoService.alterar(contrato);
        log.info("Contrato Criado!");
        return new ResponseEntity<>(responseContrato, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletarContrato(@PathVariable Integer id) {
        log.info("Deletando Contrato.");
        contratoService.deletar(id);
        log.info("Contrato Deletado!");
        return new ResponseEntity<>("Contrato deletado com Sucesso", HttpStatus.OK);
    }}
