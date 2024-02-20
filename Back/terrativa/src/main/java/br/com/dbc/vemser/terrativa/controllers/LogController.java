package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.responses.LogContadorDTO;
import br.com.dbc.vemser.terrativa.dto.responses.LogDTO;
import br.com.dbc.vemser.terrativa.entity.TipoLog;
import br.com.dbc.vemser.terrativa.exceptions.EntidadeNaoEncontradaException;
import br.com.dbc.vemser.terrativa.services.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {

    private final LogService logService;

    @GetMapping("/pageable")
    public Page<LogDTO> listPageable(@PageableDefault(size = 10, page = 0, sort = {"data"}) Pageable pageable) throws EntidadeNaoEncontradaException {
        return logService.listAllLogsPageable(pageable);
    }

    @GetMapping("/by-id")
    public LogDTO listById(String id) throws EntidadeNaoEncontradaException {
        return logService.listById(id);
    }

    @GetMapping("/by-tipolog")
    public List<LogDTO> listByTipoLog(TipoLog tipoLog) {
        return logService.listAllLogsByTipoLog(tipoLog);
    }

    @GetMapping("/group-by-tipolog-and-count")
    public List<LogContadorDTO> groupByTipoLogAndCount() {
        return logService.groupByTipoLogAndCount();
    }

    @GetMapping("/group-by-date-and-count-tipolog")
    public List<LogContadorDTO> groupByDateAndCountTipoLog(String date) {
        return logService.groupByDateAndCountTipoLog(date);
    }

    @GetMapping("/find-all-by-date")
    public List<LogDTO> listByDate(String date) throws Exception {
        return logService.listAllByData(date);
    }

    @GetMapping("/return-all-after-date")
    public List<LogDTO> returnAllAfterDate(String data) {
        return logService.findAllAfterDate(data);
    }


}
