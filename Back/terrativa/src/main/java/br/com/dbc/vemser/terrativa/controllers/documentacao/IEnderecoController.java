package br.com.dbc.vemser.terrativa.controllers.documentacao;

import br.com.dbc.vemser.terrativa.dto.RequestEndereco;
import br.com.dbc.vemser.terrativa.dto.RequestUsuario;
import br.com.dbc.vemser.terrativa.dto.ResponseEndereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IEnderecoController {
    @Operation(summary = "Listar Endereco", description = "Lista todas os enderecos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de enderecos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/todos")// GET localhost:8081/endereco/todos
    public ResponseEntity<List<ResponseEndereco>> listarEndereco() throws Exception;

    @Operation(summary = "Listar Endereco por ID", description = "Lista todas os enderecos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna enderecos por ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")// localhost:8081/endereco/1
    public ResponseEntity<ResponseEndereco> resgatarEnderecoPorID(@PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Criar Endereco", description = "Cria enderecos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna endereco criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping // POST localhost:8081/endereco
    public  ResponseEntity<ResponseEndereco> cadastrarEndereco(
            @Valid @RequestBody RequestEndereco endereco) throws Exception;

    @Operation(summary = "Altera Endereco", description = "Altera endereco no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de alugueis"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}") // PUT localhost:8081/endereco/1
    public  ResponseEntity<ResponseEndereco> atualizarEndereco(@PathVariable("id") Integer idUsuario,
                                                               @Valid @RequestBody  RequestEndereco endereco, RequestUsuario usuario)throws Exception;

    @Operation(summary = "Deletar Endereco", description = "Deletar endereco do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta endereco banco "),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")// DELETE localhost:8081/endereco/1
    public ResponseEntity<String> deletarEndereco(@PathVariable Integer id) throws Exception;
}
