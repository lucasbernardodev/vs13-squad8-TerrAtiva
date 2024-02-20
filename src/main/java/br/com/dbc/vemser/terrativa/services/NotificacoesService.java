package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.requests.RequestNotificacoesDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseNotificacoesDTO;
import br.com.dbc.vemser.terrativa.entity.Notificacoes;
import br.com.dbc.vemser.terrativa.repository.NotificacoesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificacoesService {

    private final NotificacoesRepository notificacoesRepository;
    private final ObjectMapper objectMapper;

    public ResponseNotificacoesDTO enviarNotificacoes(RequestNotificacoesDTO not){
        Notificacoes notificacoes = objectMapper.convertValue(not, Notificacoes.class);
        notificacoes.setData(LocalDate.now());
        notificacoesRepository.save(notificacoes);
        return objectMapper.convertValue(notificacoes, ResponseNotificacoesDTO.class);
    }

    public List<ResponseNotificacoesDTO> buscarDatasAnterior(String data){
        LocalDate dataProcurada = LocalDate.parse(data, DateTimeFormatter.ISO_DATE);
        List<Notificacoes> lista = notificacoesRepository.findAllByDataBefore(dataProcurada);
        List<ResponseNotificacoesDTO> dtoList = lista.stream().map(obj -> objectMapper.convertValue(obj, ResponseNotificacoesDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    public List<ResponseNotificacoesDTO> buscarDatasPosterior(String data){
        LocalDate dataProcurada = LocalDate.parse(data, DateTimeFormatter.ISO_DATE);
        List<Notificacoes> lista = notificacoesRepository.findAllByDataAfter(dataProcurada);
        List<ResponseNotificacoesDTO> dtoList = lista.stream().map(obj -> objectMapper.convertValue(obj, ResponseNotificacoesDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    public List<ResponseNotificacoesDTO> buscarNotificacoesUsuarios(Integer id){
        List<Notificacoes> lista = notificacoesRepository.findAllByUsuariosContains(id);
        List<ResponseNotificacoesDTO> dtoList = lista.stream().map(obj -> objectMapper.convertValue(obj, ResponseNotificacoesDTO.class)).collect(Collectors.toList());
        return dtoList;
    }
}
