package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.requests.RequestNotificacoesDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseNotificacoesDTO;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.services.NotificacoesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Notificações", description = "Endpoints de Notificações")
@RequiredArgsConstructor
@RequestMapping("/notificacoes")
@Validated
public class NotificacoesController {

    private final NotificacoesService notificacoesService;

    @PostMapping
    public ResponseEntity<ResponseNotificacoesDTO> enviarNotificacoes(@RequestBody @Valid RequestNotificacoesDTO not) throws RegraDeNegocioException {
        return new ResponseEntity<>(notificacoesService.enviarNotificacoes(not), HttpStatus.CREATED);
    }

    @GetMapping("/dataanterior/{data}")
    public ResponseEntity<List<ResponseNotificacoesDTO>> buscarDatasAnterior(@PathVariable("data") String data){
        return new ResponseEntity<>(notificacoesService.buscarDatasAnterior(data), HttpStatus.OK);
    }

    @GetMapping("/dataposterior/{data}")
    public ResponseEntity<List<ResponseNotificacoesDTO>> buscarDatasPosterior(@PathVariable("data") String data){
        return new ResponseEntity<>(notificacoesService.buscarDatasPosterior(data), HttpStatus.OK);
    }

    @GetMapping("/notificacoes-usuarios/{id}")
    public ResponseEntity<List<ResponseNotificacoesDTO>> buscarNotificacoesUsuarios(@PathVariable("id") Integer id){
        return new ResponseEntity<>(notificacoesService.buscarNotificacoesUsuarios(id), HttpStatus.OK);
    }
}
