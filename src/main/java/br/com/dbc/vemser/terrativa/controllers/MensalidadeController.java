package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.controllers.interfaces.IMensalidadeController;
import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseMensalidadeDTO;
import br.com.dbc.vemser.terrativa.services.MensalidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/mensalidade")
@Slf4j
@Tag(name = "Mensalidades", description = "Endpoints do CRUD de Mensalidades")
public class MensalidadeController implements IMensalidadeController {

    private final MensalidadeService mensalidadeService;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMensalidadeDTO> atualizarMensalidade(@PathVariable("id") @NotNull Integer id, @RequestBody @Valid RequestMensalidadeCreateDTO requestMensalidade) throws Exception{
        log.info("Atualizando mensalidade");
        ResponseMensalidadeDTO responseMensalidade = mensalidadeService.alterarMensalidade(id, requestMensalidade);
        log.info("Mensalidade atualizada");
        return new ResponseEntity<>(responseMensalidade, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMensalidadeDTO> resgatarMensalidadePorId(@PathVariable Integer id) throws Exception{
        return new ResponseEntity<>(mensalidadeService.resgatarMensalidadePorId(id), HttpStatus.OK);
    }

}
