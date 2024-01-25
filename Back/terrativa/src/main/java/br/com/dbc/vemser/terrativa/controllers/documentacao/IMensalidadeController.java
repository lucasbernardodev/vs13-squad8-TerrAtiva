package br.com.dbc.vemser.terrativa.controllers.documentacao;

import br.com.dbc.vemser.terrativa.dto.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.ResponseMensalidadeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IMensalidadeController {

    @Operation(summary = "Atualiza mensalidade", description = "Atualiza mensalidade ja criada.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna mensalidade atualizada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMensalidadeDTO> atualizarMensalidade(@PathVariable("id") @NotNull Integer id, @RequestBody @Valid RequestMensalidadeCreateDTO requestMensalidade) throws Exception;


    @Operation(summary = "Retorna mensalidade", description = "Retorna mensalidade do respectivo ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna mensalidade."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMensalidadeDTO> resgatarEnderecoPorID(@PathVariable Integer id) throws Exception;


    @Operation(summary = "Deleta mensalidade", description = "Deleta mensalidade do respectivo ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Mensalidade deletada."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMensalidade(@PathVariable Integer id) throws Exception;

}
