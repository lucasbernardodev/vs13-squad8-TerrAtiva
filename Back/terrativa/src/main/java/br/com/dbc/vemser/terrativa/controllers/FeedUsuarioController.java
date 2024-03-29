package br.com.dbc.vemser.terrativa.controllers;


import br.com.dbc.vemser.terrativa.controllers.interfaces.IFeedUsuarioController;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.services.FeedUsuariosService;
import br.com.dbc.vemser.terrativa.services.SessaoUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@Validated
@RequiredArgsConstructor
@Tag(name = "Feed do Usuário", description = "Endpoints do Feed para os usuários")
@RestController
@RequestMapping("feed-usuario")
public class FeedUsuarioController implements IFeedUsuarioController{

    private final FeedUsuariosService feedUsuariosService;
    private final SessaoUsuarioService sessaoUsuarioService;

    @GetMapping("/disponiveis")
    public ResponseEntity<List<ResponseTerrenoDTO>> mostrarTerrenosDisponiveis() {
        Integer id = sessaoUsuarioService.getIdLoggedUserId();
        log.info("Recebida a requisição para mostrar terrenos disponíveis para o usuário com ID {}", id);
        return new ResponseEntity<>(feedUsuariosService.mostrarTerrenosDisponiveis(id), HttpStatus.OK);
    }

    @GetMapping("/todosterrenos")
    public ResponseEntity<List<ResponseTerrenoDTO>> mostrarTerrenosDoUsuario() {
        Integer id = sessaoUsuarioService.getIdLoggedUserId();
        log.info("Recebida a requisição para mostrar todos os terrenos do usuário com ID {}", id);
        return new ResponseEntity<>(feedUsuariosService.mostrarTerrenosDoUsuario(id), HttpStatus.OK);
    }

    @GetMapping("/alugados")
    public ResponseEntity<List<ResponseTerrenoDTO>> mostrarTerrenosAlugados() throws Exception{
        Integer id = sessaoUsuarioService.getIdLoggedUserId();
        log.info("Recebida a requisição para mostrar terrenos alugados pelo usuário com ID {}", id);
        return new ResponseEntity<>(feedUsuariosService.mostrarTerrenosAlugados(id), HttpStatus.OK);
    }

    @GetMapping("/arrendados")
    public ResponseEntity<List<ResponseTerrenoDTO>> mostrarTerrenosArrendados() {
        Integer id = sessaoUsuarioService.getIdLoggedUserId();
        log.info("Recebida a requisição para mostrar terrenos arrendados pelo usuário com ID {}", id);
        return new ResponseEntity<>(feedUsuariosService.mostrarTerrenosArrendados(id), HttpStatus.OK);
    }

}
