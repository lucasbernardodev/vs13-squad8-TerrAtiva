package br.com.dbc.vemser.terrativa.controllers.interfaces;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IContratoController {

    @Operation(summary = "Resgatar contrato por ID", description = "Resgata um contrato específico pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o contrato solicitado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<ResponseContratoRelatorioDTO> resgatarContratoPorID(@PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Atualizar contrato", description = "Atualiza um contrato existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza e retorna o contrato atualizado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<ResponseContratoDTO> atualizarContrato(@PathVariable("id") Integer id,
                                                          @Valid @RequestBody RequestContratoCreateDTO contrato) throws Exception;

    @Operation(summary = "Cancelar contrato", description = "Cancelar um contrato existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cancelar o contrato solicitado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletarContrato(@PathVariable Integer id) throws Exception;
}