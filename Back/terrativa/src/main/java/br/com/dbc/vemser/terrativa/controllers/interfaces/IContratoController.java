package br.com.dbc.vemser.terrativa.controllers.interfaces;

import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    ResponseEntity<ResponseContratoRelatorioDTO> resgatarContratoPorID(@PathVariable("id") Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Cancelar contrato", description = "Cancelar um contrato existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cancelar o contrato solicitado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<String>encerrarContrato(@PathVariable Integer id) throws RegraDeNegocioException;
}
