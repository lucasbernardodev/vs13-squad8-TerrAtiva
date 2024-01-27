package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.requests.RequestTerrenoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseTerrenoDTO;
import br.com.dbc.vemser.terrativa.services.TerrenoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Tag(name = "Terreno", description = "Endpoints do CRUD de Terreno")
@RequiredArgsConstructor
@RequestMapping("/terreno") // localhost:8081/usuario
public class TerrenoController {

    private final TerrenoService terrenoService;

    @GetMapping("/{idTerreno}") // GET localhost:8081/terreno/1
    public ResponseEntity<ResponseTerrenoDTO> buscarTerrenoPorId(@PathVariable("idTerreno") Integer idTerreno) throws Exception {
        log.info("Buscando terreno por id");
        ResponseTerrenoDTO responseTerreno = terrenoService.buscarTerreno(idTerreno);
        log.info("Buscou terreno por id");
        return new ResponseEntity<>(responseTerreno, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseTerrenoDTO> cadastrarTerreno(
                                        @Valid @RequestBody RequestTerrenoCreateDTO terreno) throws Exception {
        log.info("Criando terreno");
        ResponseTerrenoDTO responseTerreno = terrenoService.cadastrarTerreno(terreno);
        log.info("Criando terreno");
        return new ResponseEntity<>(responseTerreno, HttpStatus.CREATED);

    }

    @PutMapping("/{idTerreno}")
    public ResponseEntity<ResponseTerrenoDTO> atualizarTerreno(@PathVariable("idTerreno") Integer idTerreno,
                                                               @Valid @RequestBody RequestTerrenoCreateDTO terreno) {
        log.info("Atualizando terreno");
        terreno.setId(idTerreno);
        ResponseTerrenoDTO responseTerreno = terrenoService.alterarTerreno(terreno);
        log.info("Atualizou terreno");
        return new ResponseEntity<>(responseTerreno, HttpStatus.OK);

    }

    @DeleteMapping("/{idTerreno}")
    public ResponseEntity<String> deletarDados(@PathVariable Integer idTerreno) throws Exception {
        log.info("Deletando terreno...");
        terrenoService.deletarTerreno(idTerreno);
        log.info("Terreno deletado");
        return new ResponseEntity<>(HttpStatus.OK);
    }}

//    public String arrendarTerreno(Integer proprietarioID, Integer terrenoID, LocalDate dataAssinatura, LocalDate dataInicio, LocalDate dataFinal,
//                                  Integer dataVencimentoAluguel, // CONTRATO
//                                  double valorMensal, Integer anoExercicio, // MENSALIDADE
//                                  Integer mesReferencia, LocalDate dataEmissao, LocalDate dataVencimento,
//                                  double taxas, String codigoBarras, LocalDate dataPagamento) {
//
//            terrenoService.arrendarTerreno(proprietarioID, terrenoID, dataAssinatura, dataInicio, dataFinal, dataVencimentoAluguel,
//                                            valorMensal, anoExercicio,
//                                            mesReferencia, dataEmissao, dataVencimento, taxas, codigoBarras, dataPagamento);
//
//            return "Terreno Arrendado com Sucesso!";
//    }
//    public String cancelarContratoTerreno(Integer usuarioID, Integer contratoID) {
//            terrenoService.cancelarContratoTerreno(usuarioID, contratoID);
//            return "Contrato cancelado com sucesso!";
//    }
//
//}
