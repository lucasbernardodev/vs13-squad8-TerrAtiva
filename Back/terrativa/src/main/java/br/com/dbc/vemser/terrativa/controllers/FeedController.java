package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.entity.Estados;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.services.FeedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Tag(name = "Feed de Anúncios", description = "Endpoints do Feed")
@RestController
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<Page<ResponseFeedDTO>> mostrarTerrenosDisponveis(
            @PageableDefault(size = 10, page = 0, sort = {"criado"}, direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("Mostrando terrenos disponíveis.");
        Page<ResponseFeedDTO> response = feedService.listarTerrenos(pageable);
        log.info("Consulta concluída. Retornando {} resultados.", response.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/buscar")
//    public ResponseEntity<Page<ResponseFeedDTO>> mostrarTerrenosDisponveis(
//            @PageableDefault(size = 10, page = 0, sort = {"criado"}, direction = Sort.Direction.DESC) Pageable pageable) {
//        log.info("Mostrando terrenos disponíveis.");
//        Page<ResponseFeedDTO> response = feedService.listarTerrenos(pageable);
//        log.info("Consulta concluída. Retornando {} resultados.", response.getTotalElements());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @GetMapping("/quantidade")
//    public ResponseEntity<List<ResponseFeedQuantidadeAnunciosDTO>> quantidadeAnuncios() {
//        log.info("Recebida a requisição para obter a quantidade de anúncios.");
//        List<ResponseFeedQuantidadeAnunciosDTO> response = feedService.quantidadeAnuncios();
//        log.info("Consulta concluída. Retornando {} resultados.", response.size());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
