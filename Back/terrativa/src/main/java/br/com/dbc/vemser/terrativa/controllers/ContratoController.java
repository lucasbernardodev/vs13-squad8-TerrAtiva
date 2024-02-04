package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.services.ContratoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@Tag(name = "Contratos", description = "Endpoints do CRUD de Contrato")
@RequiredArgsConstructor
@RequestMapping("/contrato")
public class ContratoController {
    private final ContratoService contratoService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContratoRelatorioDTO> resgatarContratoPorID(
            @PathVariable("id") Integer id) throws RegraDeNegocioException {
        log.info("Buscando contrato por Id.");
        ResponseContratoRelatorioDTO contrato = contratoService.resgatarContratoPorId(id);
        log.info("Contrato Listado!");
        return new ResponseEntity<>(contrato, HttpStatus.OK);
           }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>encerrarContrato(@PathVariable Integer id) throws RegraDeNegocioException {
        log.info("encerrado Contrato.");
        contratoService.deletar(id);
        log.info("Contrato encerrado!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
