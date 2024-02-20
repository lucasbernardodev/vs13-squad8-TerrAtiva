package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Log;
import br.com.dbc.vemser.terrativa.exceptions.EntidadeNaoEncontradaException;
import br.com.dbc.vemser.terrativa.repository.LogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogServiceTest {

    @Mock
    private LogRepository logRepository;

    @Spy
    @InjectMocks
    private LogService logService;

    @Test
    void listAllLogsPageable() throws EntidadeNaoEncontradaException {
        when(logRepository.findAll((Pageable) any())).thenReturn(Entidades.retornaListaLog());

        logService.listAllLogsPageable(any());

        verify(logRepository, times(1)).findAll((Pageable) any());
    }

    @Test
    void listAllLogsByTipoLog() {

        when(logRepository.findAllByTipoLog(any())).thenReturn(Entidades.retornaListaLog().toList());

        logService.listAllLogsByTipoLog(any());

        verify(logRepository, times(1)).findAllByTipoLog(any());
    }

    @Test
    void groupByTipoLogAndCount() {

        when(logRepository.groupByTipoLogAndCount()).thenReturn(Entidades.retornaListaLogList());

        logService.groupByTipoLogAndCount();

        verify(logRepository, times(1)).groupByTipoLogAndCount();
    }

    @Test
    void groupByDateAndCountTipoLog() {

            when(logRepository.findAllByDateAndCountTipoLog(any())).thenReturn(Entidades.retornaListaLogList());

            logService.groupByDateAndCountTipoLog(any());

            verify(logRepository, times(1)).findAllByDateAndCountTipoLog(any());
    }

    @Test
    void listAllByData() throws EntidadeNaoEncontradaException {

        when(logRepository.findAllByDataContains(anyString())).thenReturn(Entidades.retornaListaLog().toList());

        logService.listAllByData("2001-01-01");

        verify(logRepository, times(1)).findAllByDataContains(any());
    }

    @Test
    void listAllByDataFutura() {
        assertThrows(EntidadeNaoEncontradaException.class, () -> logService.listAllByData("2050-01-01"));
    }

    @Test
    void listById() throws EntidadeNaoEncontradaException {

        doReturn(Entidades.retornaLog()).when(logService).retornarPeloId(anyString());

        logService.listById(anyString());

        verify(logService, times(1)).retornarPeloId(anyString());
    }

    @Test
    void listByIdThrows() {

        assertThrows(EntidadeNaoEncontradaException.class, () -> logService.listById(""));
    }

    @Test
    void retornarPeloId() throws EntidadeNaoEncontradaException {

        when(logRepository.findById(anyString())).thenReturn(Optional.of(Entidades.retornaLog()));

        logService.retornarPeloId(anyString());

        verify(logRepository, times(1)).findById(anyString());
    }

    @Test
    void retornarPeloIdThrows() {

        when(logRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> logService.retornarPeloId(""));
    }

    @Test
    void findAllAfterDate() {

            when(logRepository.findAllAfterDate(anyString())).thenReturn(Entidades.retornaListaLog().toList());

            logService.findAllAfterDate(anyString());

            verify(logRepository, times(1)).findAllAfterDate(anyString());
    }

    @Test
    void save() {

        Log log = Entidades.retornaLog();

        when(logRepository.save(any())).thenReturn(log);

        logService.save(log);

        assertTrue(new ReflectionEquals(log).matches(log));
    }
}