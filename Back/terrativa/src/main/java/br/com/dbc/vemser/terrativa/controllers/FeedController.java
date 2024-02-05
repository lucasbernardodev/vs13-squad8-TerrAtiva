package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.interfaces.IFeedController;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedQuantidadeAnunciosDTO;
import br.com.dbc.vemser.terrativa.entity.Estados;
import br.com.dbc.vemser.terrativa.services.FeedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@Tag(name = "Feed de Anúncios", description = "Endpoints do Feed")
@RestController
@RequestMapping("/feed")
public class FeedController implements IFeedController {
    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<Page<ResponseFeedDTO>> mostrarTerrenosDisponiveis(
            @PageableDefault(sort = {"criado"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "campoDeBusca", required = false) String campoDebusca,
            @RequestParam(value = "precoInicial", required = false) Integer precoIncial,
            @RequestParam(value = "precoFinal", required = false) Integer precoFinal,
            @RequestParam(value = "estado", required = false)Estados estados) {

        log.info("Mostrando terrenos disponíveis.");
        Page<ResponseFeedDTO> response = feedService.listarTerrenos(pageable, campoDebusca, precoIncial, precoFinal, estados);
        log.info("Consulta concluída. Retornando {} resultados.", response.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/quantidade/estado")
    public ResponseEntity<Page<ResponseFeedQuantidadeAnunciosDTO>> quantidadeAnunciosPorEstado(
            @PageableDefault(page = 0, size = 5, sort = {"enderecoTerrenoID.codIBGE.nomeEstado"}) Pageable pageable) {
        Page<ResponseFeedQuantidadeAnunciosDTO> resultado = feedService.quantidadeAnuncios(pageable);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
