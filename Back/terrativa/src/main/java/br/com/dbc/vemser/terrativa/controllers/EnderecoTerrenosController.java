package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.interfaces.IEnderecoTerrenoController;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseEnderecoTerrenosDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoTerrenosCreateDTO;
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
@RequestMapping("/endereco-terreno")
@Slf4j
@Tag(name = "Endereços dos Terrenos", description = "Endpoints do CRUD de EndereçosTerrenos")
public class EnderecoTerrenosController {

    private final EnderecoTerrenosService enderecoTerrenosService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEnderecoTerrenosDTO> resgatarEnderecoPorID(@PathVariable("id") @NotNull Integer id) throws Exception{
        ResponseEnderecoTerrenosDTO responseEnderecoTerrenos = enderecoTerrenosService.resgatarPorId(id);
        return new ResponseEntity<>(responseEnderecoTerrenos, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEnderecoTerrenosDTO> deletarEndereco(@PathVariable("id") @NotNull Integer id) throws Exception{
        log.info("Deletando Endereço");
        enderecoTerrenosService.deletar(id);
        log.info("Endereço Deletado");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
