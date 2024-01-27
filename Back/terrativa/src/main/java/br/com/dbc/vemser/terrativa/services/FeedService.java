package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.repository.FeedRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final ObjectMapper objectMapper;

    public List<ResponseFeedDTO> buscarTerrenos(String preco, String campoDeBusca, String estado, String tamanho) {
        List<Feed> terrenos = feedRepository.buscarTerrenos(preco, estado, tamanho);

        List<ResponseFeedDTO> responseFeedDTOS = terrenos.stream()
                .map(feed -> objectMapper.convertValue(feed, ResponseFeedDTO.class))
                .toList();

        return filtrarTerrenos(terrenos, campoDeBusca)
                .stream()
                .map(feed -> objectMapper.convertValue(feed, ResponseFeedDTO.class))
                .collect(Collectors.toList());
    }

    private List<Feed> filtrarTerrenos(List<Feed> terrenos, String campoDeBusca) {
        boolean encontrado = true;
        log.info(campoDeBusca);
        List<Feed> terrenosFiltrados = new ArrayList<>();
        List<String> palavrasChaveList = Arrays.stream(campoDeBusca.split(" ")).toList();
        log.info("Palavras chave: {}", palavrasChaveList);
        for (Feed terreno : terrenos) {
            for (String palavraChave : palavrasChaveList) {
                if (!terreno.getDescricao().toLowerCase().contains(palavraChave.toLowerCase()) && !terreno.getTitulo().toLowerCase().contains(palavraChave.toLowerCase())) {
                    encontrado = false;
                }
            }
            if (encontrado) {
                terrenosFiltrados.add(terreno);
            }
        }
        return terrenosFiltrados;
    }

    public List<ResponseFeedDTO> quantidadeAnuncios() {
        List<ResponseFeedDTO> anuncios = feedRepository.quantidadeAnuncios();

        return anuncios.stream()
                .map(feed -> objectMapper.convertValue(feed, ResponseFeedDTO.class))
                .collect(Collectors.toList());
    }
}
