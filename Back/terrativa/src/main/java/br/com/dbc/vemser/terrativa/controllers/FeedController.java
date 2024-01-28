package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.interfaces.IFeedController;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedQuantidadeAnunciosDTO;
import br.com.dbc.vemser.terrativa.entity.Estados;
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
@RequestMapping("/feed")
public class FeedController implements IFeedController {
    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<List<ResponseFeedDTO>> mostrarTerrenosDisponveis(@RequestParam(value = "preco", required=false, defaultValue = "") String preco,
                                                                     @RequestParam(value = "campodebusca", required = false, defaultValue = "") String campoDeBusca,
                                                                     @RequestParam(value = "estado", required = false, defaultValue = "") Estados estado,
                                                                     @RequestParam(value = "tamanho", required = false, defaultValue = "") String tamanho) {
        log.info("Mostrando terrenos disponíveis.");

        List<ResponseFeedDTO> response = feedService.buscarTerrenos(preco, campoDeBusca, estado, tamanho);
        log.info("Consulta concluída. Retornando {} resultados.", response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/quantidade")
    public ResponseEntity<List<ResponseFeedQuantidadeAnunciosDTO>> quantidadeAnuncios() {
        log.info("Recebida a requisição para obter a quantidade de anúncios.");
        List<ResponseFeedQuantidadeAnunciosDTO> response = feedService.quantidadeAnuncios();
        log.info("Consulta concluída. Retornando {} resultados.", response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
