package br.com.dbc.vemser.terrativa.controllers.interfaces;

import br.com.dbc.vemser.terrativa.dto.requests.DeletarContaDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IUsuarioController {


    //COMENTADO PORQUE NÃO É PRA SER USADO, MAS FOI MANTIDO POIS TALVEZ SEJA NECESSÁRIO NO FUTURO
//    @Operation(summary = "Listar usuários", description = "Lista todos os usuários do banco")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(responseCode = "200", description = "Retorna a lista dos usuários"),
//                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
//                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
//            }
//    )
//    @GetMapping
//    ResponseEntity<List<ResponseUsuarioDTO>> listarUsuarios() throws Exception;

    @Operation(summary = "Listar usuário pelo id", description = "Lista todos os usuários do banco que contenham o id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário solicitado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idUsuario}")
    ResponseEntity<ResponseUsuarioDTO> buscarUsuarioPorId(@PathVariable("idUsuario") Integer idUsuario) throws Exception;

    @Operation(summary = "Criar usuário", description = "Cria um usuário no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Cria e retorna o usuário criado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<ResponseUsuarioDTO> cadastrarUsuario(@Valid @RequestBody RequestUsuarioCreateDTO usuario) throws Exception;

    @Operation(summary = "Resgatar endereço por id", description = "Resgata o endereço de um usuário pelo id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço do usuário solicitado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}/endereco")
    ResponseEntity<ResponseEnderecoDTO> resgatarEnderecoPorID() throws Exception;

    @Operation(summary = "Atualizar endereço", description = "Atualiza o endereço de um usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza e retorna o endereço atualizado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @PutMapping ("/{id}/endereco")
    ResponseEntity<ResponseEnderecoDTO> atualizarEndereco(@Valid @RequestBody RequestEnderecoCreateDTO endereco)throws Exception;

    @Operation(summary = "Deletar usuário", description = "Deletar um usuário no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta o usuário solicitado"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/deletar-conta")
    ResponseEntity<String> deletarDados(@RequestBody @Valid DeletarContaDTO confirmacao) throws Exception;
}