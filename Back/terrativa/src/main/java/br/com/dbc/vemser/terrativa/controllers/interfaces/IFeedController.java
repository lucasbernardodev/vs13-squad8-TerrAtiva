package br.com.dbc.vemser.terrativa.controllers.interfaces;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IFeedController {
    @Operation(summary = "Listar Terrenos", description = "Lista todas os terrenos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de terrenos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseFeedDTO>> mostrarTerrenosDisponveis(@RequestParam(value = "preco", required=false, defaultValue = "") String preco,
                                                                           @RequestParam(value = "campodebusca", required = false, defaultValue = "") String campoDeBusca,
                                                                           @RequestParam(value = "estado", required = false, defaultValue = "") String estado,
                                                                           @RequestParam(value = "tamanho", required = false, defaultValue = "") String tamanho);


    @Operation(summary = "Listar Quantidade de Terrenos", description = "Lista todas os terrenos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de terrenos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/quantidade")
    public ResponseEntity<List<ResponseFeedDTO>> quantidadeAnuncios();
    }

