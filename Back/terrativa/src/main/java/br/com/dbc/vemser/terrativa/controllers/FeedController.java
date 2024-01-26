package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.services.FeedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Tag(name = "Feed", description = "Endpoints do Feed")
@RestController
@RequestMapping("/feed") // localhost:8080/feed
public class FeedController {
    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<List<ResponseFeedDTO>> mostrarTerrenosDisponveis(@RequestParam(value = "preco", required=false, defaultValue = "") String preco,
                                                                     @RequestParam(value = "campodebusca", required = false, defaultValue = "") String campoDeBusca,
                                                                     @RequestParam(value = "estado", required = false, defaultValue = "") String estado,
                                                                     @RequestParam(value = "tamanho", required = false, defaultValue = "") String tamanho) {

        List<ResponseFeedDTO> response = feedService.buscarTerrenos(preco, campoDeBusca, estado, tamanho);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
