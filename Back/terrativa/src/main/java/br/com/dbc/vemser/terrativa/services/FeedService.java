package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.dto.mappers.FeedMapper;
import br.com.dbc.vemser.terrativa.entity.Feed;
import br.com.dbc.vemser.terrativa.repository.FeedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FeedService {
    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public List<ResponseFeedDTO> buscarTerrenos(String preco, String campoDeBusca, String estado, String tamanho) {
        List<Feed> terrenos = feedRepository.buscarTerrenos(preco, estado, tamanho);

        return filtrarTerrenos(terrenos, campoDeBusca).stream().map(FeedMapper::FeedParaResponseFeed).collect(Collectors.toList());
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




//    public ArrayList<Feed> buscarEstados() {
//        return feedRepository.buscarEstados();
//    }



}
