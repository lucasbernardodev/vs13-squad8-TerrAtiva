package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.LogMapper;
import br.com.dbc.vemser.terrativa.dto.responses.LogContadorDTO;
import br.com.dbc.vemser.terrativa.dto.responses.LogDTO;
import br.com.dbc.vemser.terrativa.entity.Log;
import br.com.dbc.vemser.terrativa.entity.TipoLog;
import br.com.dbc.vemser.terrativa.exceptions.EntidadeNaoEncontradaException;
import br.com.dbc.vemser.terrativa.repository.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    private final String NOT_FOUND_MESSAGE = "Id da pessoa não encontrado";

    public Page<LogDTO> listAllLogsPageable(Pageable pageable) throws EntidadeNaoEncontradaException {
        Page<Log> all = logRepository.findAll(pageable);
        return all.map(LogMapper::EntityToDTO);
    }

    public List<LogDTO> listAllLogsByTipoLog(TipoLog tipoLog) {
        return logRepository.findAllByTipoLog(tipoLog).stream().map(LogMapper::EntityToDTO).collect(Collectors.toList());
    }

    public List<LogContadorDTO> groupByTipoLogAndCount() {
        return logRepository.groupByTipoLogAndCount().stream().map(log -> {
            return new LogContadorDTO(log.getTipoLog(), log.getQuantidade());
        }).collect(Collectors.toList());
    }

    public List<LogContadorDTO> groupByDateAndCountTipoLog(String date) {
        String dateRegex = ".*" + date + ".*";
        return logRepository.findAllByDateAndCountTipoLog(dateRegex).stream().map(log -> {
            return new LogContadorDTO(log.getTipoLog(), log.getQuantidade());
        }).collect(Collectors.toList());
    }

    public List<LogDTO> listAllByData(String date) throws EntidadeNaoEncontradaException {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataProcurada = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        if(dataProcurada.isAfter(dataAtual)) {
            throw new EntidadeNaoEncontradaException("Não há logs para datas futuras!");
        }

        return logRepository.findAllByDataContains(date).stream().map(LogMapper::EntityToDTO).collect(Collectors.toList());
    }

    public LogDTO listById(String id) throws EntidadeNaoEncontradaException {
        return LogMapper.EntityToDTO(retornarPeloId(id));
    }

    public Log retornarPeloId(String id) throws EntidadeNaoEncontradaException {
        return logRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOT_FOUND_MESSAGE));
    }

    public List<LogDTO> findAllAfterDate(String data) {
        return logRepository.findAllAfterDate(data).stream().map(LogMapper::EntityToDTO).collect(Collectors.toList());
    }

    public void save(Log log) {
        logRepository.save(log);
    }

}