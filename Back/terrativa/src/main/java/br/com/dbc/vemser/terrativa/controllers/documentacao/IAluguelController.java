package br.com.dbc.vemser.terrativa.controllers.documentacao;

import br.com.dbc.vemser.terrativa.dto.RequestAluguel;
import br.com.dbc.vemser.terrativa.dto.RequestUsuario;
import br.com.dbc.vemser.terrativa.dto.ResponseAluguel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IAluguelController {
    @Operation(summary = "Listar Aluguel", description = "Lista todas os alugueis do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de alugueis"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}") // GET localhost:8081/aluguel/1
    public ResponseEntity<ResponseAluguel> listarPorId(
            @PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Listar Aluguel por ID", description = "Lista alugueis por ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de alugueis por ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping // POST localhost:8081/aluguel
    public ResponseEntity<ResponseAluguel> criar(@Valid @RequestBody RequestAluguel aluguel) throws Exception;

    @Operation(summary = "Criar Aluguel", description = "cria um endpoint de aluguel no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria aluguel no banco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")// PUT localhost:8081/aluguel/1
    public ResponseEntity<ResponseAluguel> alterar(@PathVariable("id") Integer idUsuario,
                                                   @Valid @RequestBody RequestAluguel aluguel, RequestUsuario usuario) throws Exception;

    @Operation(summary = "Deletar Aluguel por ID", description = "deleta todas os alugueis por do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de alugueis"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")// DELETE localhost:8081/aluguel/1
    public ResponseEntity<String> deletar(@PathVariable int id) throws Exception;

}
