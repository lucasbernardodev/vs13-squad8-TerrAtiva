package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.interfaces.ITerrenoController;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.services.TerrenoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Tag(name = "Terrenos", description = "Endpoints do CRUD de Terreno")
@RequiredArgsConstructor
@RequestMapping("/terreno")
public class TerrenoController implements ITerrenoController {

    private final TerrenoService terrenoService;

    @GetMapping("/{idTerreno}")
    public ResponseEntity<ResponseTerrenoDTO> buscarTerrenoPorId(@PathVariable("idTerreno") Integer idTerreno) throws Exception {
        log.info("Buscando terreno por id");
        ResponseTerrenoDTO responseTerreno = terrenoService.buscarTerreno(idTerreno);
        log.info("Buscou terreno por id");
        return new ResponseEntity<>(responseTerreno, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseTerrenoDTO> cadastrarTerreno(
                                        @Valid @RequestBody RequestTerrenoCreateDTO terreno) throws Exception {
        log.info("Criando terreno");
        ResponseTerrenoDTO responseTerreno = terrenoService.cadastrarTerreno(terreno);
        log.info("Criando terreno");
        return new ResponseEntity<>(responseTerreno, HttpStatus.CREATED);

    }

    @PutMapping("/{idTerreno}")
    public ResponseEntity<ResponseTerrenoDTO> atualizarTerreno(@PathVariable("idTerreno") Integer idTerreno,
                                                               @Valid @RequestBody RequestTerrenoCreateDTO terreno) {
        log.info("Atualizando terreno");
        terreno.setId(idTerreno);
        ResponseTerrenoDTO responseTerreno = terrenoService.alterarTerreno(terreno);
        log.info("Atualizou terreno");
        return new ResponseEntity<>(responseTerreno, HttpStatus.OK);

    }

    @DeleteMapping("/{idTerreno}")
    public ResponseEntity<String> deletarDados(@PathVariable Integer idTerreno) throws Exception {
        log.info("Deletando terreno...");
        terrenoService.deletarTerreno(idTerreno);
        log.info("Terreno deletado");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{idTerreno}/arrendar")
    public ResponseEntity<String> arrendarTerreno(@PathVariable Integer idTerreno,
                                                  @Valid @RequestBody RequestContratoCreateDTO contrato) throws Exception {
        log.info("Arrendando terreno...");
        terrenoService.arrendarTerreno(idTerreno, contrato);
        log.info("Terreno arrendado");
        return new ResponseEntity<>("Operação realizada com sucesso!", HttpStatus.OK);
    }

}
