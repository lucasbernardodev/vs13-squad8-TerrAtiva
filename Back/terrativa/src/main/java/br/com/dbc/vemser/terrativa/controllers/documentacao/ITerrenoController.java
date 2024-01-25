package br.com.dbc.vemser.terrativa.controllers.documentacao;

import br.com.dbc.vemser.terrativa.dto.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.ResponseTerrenoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ITerrenoController {

    @Operation(summary = "Buscar terreno por id", description = "Busca um terreno específico pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o terreno solicitado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idTerreno}")
    ResponseEntity<ResponseTerrenoDTO> buscarTerrenoPorId(@PathVariable("idTerreno") Integer idTerreno) throws Exception;

    @Operation(summary = "Criar terreno", description = "Cria um novo terreno")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Cria e retorna o terreno criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<ResponseTerrenoDTO> cadastrarTerreno(@Valid @RequestBody RequestTerrenoCreateDTO terreno) throws Exception;

    @Operation(summary = "Atualizar terreno", description = "Atualiza um terreno existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza e retorna o terreno atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idTerreno}")
    ResponseEntity<ResponseTerrenoDTO> atualizarTerreno(@PathVariable("idTerreno") Integer idTerreno,
                                                        @Valid @RequestBody RequestTerrenoCreateDTO terreno) throws Exception;

    @Operation(summary = "Deletar terreno", description = "Deleta um terreno existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta o terreno solicitado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idTerreno}")
    ResponseEntity<String> deletarDados(@PathVariable Integer idTerreno) throws Exception;
}