package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.ResponseFeed;
import br.com.dbc.vemser.terrativa.dto.ResponseTerreno;
import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.services.FeedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

@RestController
@RequestMapping("/feedterrenos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "FeedTerrenos", description = "Endpoints do FEED de terrenos.")
public class FeedController {

    private final FeedService feedService;


    @GetMapping
    public ResponseEntity<ArrayList<ResponseFeed>> mostrarTerrenosDisponveis() throws Exception {
        return new ResponseEntity<>(feedService.mostrarTerrenosDisponiveis(), HttpStatus.OK);

    }

    @GetMapping("/preco/{valor}")
    public ResponseEntity<ArrayList<ResponseFeed>> mostrarTerrenosPorPreco(@PathVariable("valor") @NotNull String valor) throws Exception {
        return new ResponseEntity<>(feedService.mostrarTerrenosPorPreco(valor), HttpStatus.OK);
    }

    @GetMapping("/caracteristica/{car}")
    public ResponseEntity<ArrayList<ResponseFeed>> mostrarTerrenosPorCaracteristica(@PathVariable("car") String caracteristica) throws Exception{
        return new ResponseEntity<>(feedService.mostrarTerrenosPorCaracteristica(caracteristica), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<ArrayList<ResponseFeed>> mostrarTerrenosPorLocal(@PathVariable("estado") String local) throws Exception{
        return new ResponseEntity<>(feedService.mostrarTerrenosPorLocal(local), HttpStatus.OK);
    }

    @GetMapping("/tamanho/{tam}")
    public ResponseEntity<ArrayList<ResponseFeed>> mostrarTerrenosPorTamanho(@PathVariable("tam") String tamanho) throws Exception{
        return new ResponseEntity<>(feedService.mostrarTerrenosPorTamanho(tamanho), HttpStatus.OK);

    }


    public String buscarEstados() {
        ArrayList<Feed> response = feedService.buscarEstados();
        return response.isEmpty() ? "Não Existem Dados Disponiveis" : response.toString();
    }


}
