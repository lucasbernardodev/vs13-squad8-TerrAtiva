package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.documentacao.IEnderecoTerrenoController;
import br.com.dbc.vemser.terrativa.dto.RequestEnderecoTerrenos;
import br.com.dbc.vemser.terrativa.dto.ResponseEnderecoTerrenos;
import br.com.dbc.vemser.terrativa.services.EnderecoTerrenosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/endereco/terreno") // localhost:8081/endereco/terreno
@Slf4j
@Tag(name = "EndereçosTerrenos", description = "Endpoints do CRUD de EndereçosTerrenos")
public class EnderecoTerrenosController implements IEnderecoTerrenoController {

    private final EnderecoTerrenosService enderecoTerrenosService;

    @PostMapping
    public ResponseEntity<ResponseEnderecoTerrenos> cadastrarEnderecoTerrenos(@Valid @RequestBody RequestEnderecoTerrenos endereco) throws Exception {
        log.info("Adicionado endereço para os terrenos");
        ResponseEnderecoTerrenos responseEnderecoTerrenos = enderecoTerrenosService.adicionarEnderecoTerrenos(endereco);
        log.info("Endereço adicionado");
        return new ResponseEntity<>(responseEnderecoTerrenos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEnderecoTerrenos> atualizarEndereco(@PathVariable("id") @NotNull Integer id, @RequestBody @Valid RequestEnderecoTerrenos endereco) throws Exception {
            log.info("Atualizando endereço");
            ResponseEnderecoTerrenos responseEnderecoTerrenos = enderecoTerrenosService.alterar(id, endereco);
            return new ResponseEntity<>(responseEnderecoTerrenos, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEnderecoTerrenos> resgatarEnderecoPorID(@PathVariable("id") @NotNull Integer id) throws Exception{
        ResponseEnderecoTerrenos responseEnderecoTerrenos = enderecoTerrenosService.resgatarPorId(id);
        return new ResponseEntity<>(responseEnderecoTerrenos, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEnderecoTerrenos> deletarEndereco(@PathVariable("id") @NotNull Integer id) throws Exception{
        log.info("Deletando Endereço");
        enderecoTerrenosService.deletar(id);
        log.info("Endereço Deletado");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
