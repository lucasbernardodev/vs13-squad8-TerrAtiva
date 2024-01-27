package br.com.dbc.vemser.terrativa.controllers;


import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedUsuarioAlugadosDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedUsuarioDTO;
import br.com.dbc.vemser.terrativa.services.FeedUsuariosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


@Slf4j
@Validated
@RequiredArgsConstructor
@Tag(name = "Feed Usuarios", description = "Endpoints do Feed para os usuários")
@RestController
@RequestMapping("feed/usuario")
public class FeedUsuarioController {

    private final FeedUsuariosService feedUsuariosService;

    @GetMapping("/disponiveis/{id}")
    public ResponseEntity<ArrayList<ResponseFeedUsuarioDTO>> mostrarTerrenosDisponiveis(@PathVariable("id") @NotNull Integer id) throws Exception{
        return new ResponseEntity<>(feedUsuariosService.mostrarTerrenosDisponiveis(id), HttpStatus.OK);
    }

    @GetMapping("/todosterrenos/{id}")
    public ResponseEntity<ArrayList<ResponseFeedUsuarioDTO>> mostrarTerrenosDoUsuario(@PathVariable("id") @NotNull Integer id) throws Exception{
        return new ResponseEntity<>(feedUsuariosService.mostrarTerrenosDoUsuario(id), HttpStatus.OK);
    }

    @GetMapping("/alugados/{id}")
    public ResponseEntity<ArrayList<ResponseFeedUsuarioAlugadosDTO>> mostrarTerrenosAlugados(@PathVariable("id") @NotNull Integer id) throws Exception{
        return new ResponseEntity<>(feedUsuariosService.mostrarTerrenosAlugados(id), HttpStatus.OK);
    }

    @GetMapping("/arrendados/{id}")
    public ResponseEntity<ArrayList<ResponseFeedUsuarioAlugadosDTO>> mostrarTerrenosArrendados(@PathVariable("id") @NotNull Integer id) throws Exception{
        return new ResponseEntity<>(feedUsuariosService.mostrarTerrenosArrendados(id), HttpStatus.OK);
    }

}
