package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.RequestMensalidade;
import br.com.dbc.vemser.terrativa.dto.ResponseMensalidade;
import br.com.dbc.vemser.terrativa.services.ContratoService;
import br.com.dbc.vemser.terrativa.services.MensalidadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
@RequestMapping("/mensalidade") // localhost:8081/mensalidade
@Slf4j
public class MensalidadeController {

    private final MensalidadeService mensalidadeService;


    @PutMapping("/{id}")
    public ResponseEntity<ResponseMensalidade> atualizarMensalidade(@PathVariable("id") @NotNull Integer id, @RequestBody @Valid RequestMensalidade requestMensalidade) throws Exception{
        log.info("Atualizando mensalidade");
        ResponseMensalidade responseMensalidade = mensalidadeService.alterarMensalidade(id, requestMensalidade);
        log.info("Mensalidade atualizada");
        return new ResponseEntity<>(responseMensalidade, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMensalidade> resgatarEnderecoPorID(@PathVariable Integer id) throws Exception{
        return new ResponseEntity<>(mensalidadeService.resgatarMensalidadePorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMensalidade(@PathVariable Integer id) throws Exception {
        log.info("Deletando mensalidade");
        mensalidadeService.deletarMensalidade(id);
        log.info("Mensalidade deletada");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
