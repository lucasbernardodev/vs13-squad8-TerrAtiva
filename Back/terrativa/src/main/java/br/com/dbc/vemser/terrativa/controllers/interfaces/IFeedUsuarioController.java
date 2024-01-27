package br.com.dbc.vemser.terrativa.controllers.interfaces;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedUsuarioAlugadosDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public interface IFeedUsuarioController {
    @Operation(summary = "Listar Terrenos Por ID", description = "Lista os terrenos por ID do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna terreno por ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/disponiveis/{id}")
    public ResponseEntity<ArrayList<ResponseFeedUsuarioDTO>> mostrarTerrenosDisponiveis(@PathVariable("id") @NotNull Integer id) throws Exception;

    @Operation(summary = "Listar Terrenos Disponiveis Por ID", description = "Lista os terrenos por ID do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna terreno por ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/todosterrenos/{id}")
    public ResponseEntity<ArrayList<ResponseFeedUsuarioDTO>> mostrarTerrenosDoUsuario(@PathVariable("id") @NotNull Integer id) throws Exception;

    @Operation(summary = "Listar Terrenos Alugados Por ID", description = "Lista os terrenos alugados por ID do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna terreno alugados por ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/alugados/{id}")
    public ResponseEntity<ArrayList<ResponseFeedUsuarioAlugadosDTO>> mostrarTerrenosAlugados(@PathVariable("id") @NotNull Integer id) throws Exception;

    @Operation(summary = "Listar Terrenos Arrendados Por ID", description = "Lista os terrenos arrendados por ID do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna terreno arrendados por ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/arrendados/{id}")
    public ResponseEntity<ArrayList<ResponseFeedUsuarioAlugadosDTO>> mostrarTerrenosArrendados(@PathVariable("id") @NotNull Integer id) throws Exception;
}
