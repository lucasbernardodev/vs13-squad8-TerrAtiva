package br.com.dbc.vemser.terrativa.controllers.documentacao;
import br.com.dbc.vemser.terrativa.dto.RequestEnderecoTerrenos;
import br.com.dbc.vemser.terrativa.dto.ResponseEnderecoTerrenos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IEnderecoTerreno {


    @Operation(summary = "Adicionar endereço para os terrenos.", description = "Adiciona endereço para os terrenos.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna endereço cadastrado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<ResponseEnderecoTerrenos> cadastrarEnderecoTerrenos(@Valid @RequestBody RequestEnderecoTerrenos endereco) throws Exception;

    @Operation(summary = "Atualiza endereço para os terrenos.", description = "Atualiza endereço para os terrenos com seu respectivo ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna endereço atualizado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEnderecoTerrenos> atualizarEndereco(@PathVariable("id") @NotNull Integer id, @RequestBody @Valid RequestEnderecoTerrenos endereco) throws Exception;


    @Operation(summary = "Lista endereço solicitado.", description = "Lista endereços solicitados pelo seu ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna endereço solicitado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEnderecoTerrenos> resgatarEnderecoPorID(@PathVariable("id") @NotNull Integer id) throws Exception;


    @Operation(summary = "Deleta endereço solicitado.", description = "Deleta endereço solicitado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço deletado com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEnderecoTerrenos> deletarEndereco(@PathVariable("id") @NotNull Integer id) throws Exception;

}
