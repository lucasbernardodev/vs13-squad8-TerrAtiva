package br.com.dbc.vemser.terrativa.controllers.interfaces;

import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedQuantidadeAnunciosDTO;
import br.com.dbc.vemser.terrativa.entity.Estados;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IFeedController {
    @Operation(summary = "Listar Terrenos", description = "Lista todas os terrenos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de terrenos"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
public ResponseEntity<Page<ResponseFeedDTO>> mostrarTerrenosDisponiveis(
        @PageableDefault(sort = {"criado"}, direction = Sort.Direction.DESC) Pageable pageable,
        @RequestParam(value = "campoDeBusca", required = false) String campoDebusca,
        @RequestParam(value = "precoInicial", required = false) Integer precoIncial,
        @RequestParam(value = "precoFinal", required = false) Integer precoFinal,
        @RequestParam(value = "estado", required = false)Estados estados);


    @Operation(summary = "Quantidade de Anúncios por Estado", description = "Retorna a quantidade de anúncios por estado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a quantidade de anúncios por estado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/quantidade/estado")
    ResponseEntity<Page<ResponseFeedQuantidadeAnunciosDTO>> quantidadeAnunciosPorEstado(@PageableDefault(sort = {"enderecoTerrenoID.codIBGE.nomeEstado"}) @Schema(example = "{\"page\": 0, \"size\": 5}") Pageable pageable);

}

