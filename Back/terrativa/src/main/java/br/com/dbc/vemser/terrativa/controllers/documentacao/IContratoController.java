package br.com.dbc.vemser.terrativa.controllers.documentacao;

import br.com.dbc.vemser.terrativa.dto.RequestContrato;
import br.com.dbc.vemser.terrativa.dto.ResponseContrato;
import io.swagger.v3.oas.annotations.Operation;
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
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<ResponseContrato> resgatarContratoPorID(@PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Criar contrato", description = "Cria um novo contrato")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Cria e retorna o contrato criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<ResponseContrato> criarContrato(@Valid @RequestBody RequestContrato contrato) throws Exception;

    @Operation(summary = "Atualizar contrato", description = "Atualiza um contrato existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza e retorna o contrato atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<ResponseContrato> atualizarContrato(@PathVariable("id") Integer id,
                                                       @Valid @RequestBody RequestContrato contrato) throws Exception;

    @Operation(summary = "Deletar contrato", description = "Deleta um contrato existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta o contrato solicitado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletarContrato(@PathVariable Integer id) throws Exception;
}