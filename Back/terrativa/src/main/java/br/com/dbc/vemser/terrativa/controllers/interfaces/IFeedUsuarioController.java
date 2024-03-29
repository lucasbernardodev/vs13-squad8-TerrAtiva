package br.com.dbc.vemser.terrativa.controllers.interfaces;

import br.com.dbc.vemser.terrativa.dto.responses.ResponseTerrenoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface IFeedUsuarioController {

    @Operation(summary = "Lista terrenos do usuário ainda disponíveis para serem alugados", description = "Lista os terrenos por ID do banco")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna terreno por ID"),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
    })
    @GetMapping("/disponiveis/{id}")
    ResponseEntity<List<ResponseTerrenoDTO>> mostrarTerrenosDisponiveis();

    @Operation(summary = "Lista todos terrenos do usuário", description = "Lista os terrenos por ID do banco")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna terreno por ID"),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
    })
    @GetMapping("/todosterrenos/{id}")
    ResponseEntity<List<ResponseTerrenoDTO>> mostrarTerrenosDoUsuario();

    @Operation(summary = "Lista terrenos que o Usuário alugou dos outros", description = "Lista os terrenos alugados por ID do banco")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna terreno alugados por ID"),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
    })
    @GetMapping("/alugados/{id}")
    ResponseEntity<List<ResponseTerrenoDTO>> mostrarTerrenosAlugados() throws Exception;

    @Operation(summary = "Lista terrenos do Usuário alugado por outras pessoas", description = "Lista os terrenos arrendados por ID do banco")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna terreno arrendados por ID"),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
    })
    @GetMapping("/arrendados/{id}")
    ResponseEntity<List<ResponseTerrenoDTO>> mostrarTerrenosArrendados();
}
