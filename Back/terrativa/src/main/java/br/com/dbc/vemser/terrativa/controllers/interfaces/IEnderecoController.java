package br.com.dbc.vemser.terrativa.controllers.interfaces;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IEnderecoController {

    @Operation(summary = "Listar Endereco por ID", description = "Lista todas os enderecos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna enderecos por ID"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")// localhost:8081/endereco/1
    ResponseEntity<ResponseEnderecoDTO> resgatarEnderecoPorID(@PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Criar Endereco", description = "Cria enderecos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna endereco criado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @PostMapping // POST localhost:8081/endereco
    ResponseEntity<ResponseEnderecoDTO> cadastrarEndereco(
            @Valid @RequestBody RequestEnderecoCreateDTO endereco) throws Exception;

    @Operation(summary = "Altera Endereco", description = "Altera endereco no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de alugueis"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}") // PUT localhost:8081/endereco/1
    ResponseEntity<ResponseEnderecoDTO> atualizarEndereco(@PathVariable("id") Integer id,
                                                          @Valid @RequestBody RequestEnderecoCreateDTO endereco)throws Exception;

    @Operation(summary = "Deletar Endereco", description = "Deletar endereco do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta endereco banco "),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")// DELETE localhost:8081/endereco/1
    ResponseEntity<String> deletarEndereco(@PathVariable Integer id) throws Exception;
}
