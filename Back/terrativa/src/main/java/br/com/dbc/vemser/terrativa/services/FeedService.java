package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedQuantidadeAnunciosDTO;
import br.com.dbc.vemser.terrativa.entity.EnderecoTerrenos;
import br.com.dbc.vemser.terrativa.entity.Estados;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedService {
    private final TerrenoRepository terrenoRepository;
    private final TerrenoMapper terrenoMapper;

    public Page<ResponseFeedDTO> listarTerrenos(Pageable pageable, String campoDebusca, Integer precoInicial, Integer precoFinal, Estados estados) {
        Integer estadosINT = null;
        if (estados != null){
            estadosINT = estados.getCode();
        }
        Page<Terreno> terrenos = terrenoRepository.buscarFeedComFiltros(pageable, campoDebusca, campoDebusca, precoInicial, precoFinal, estadosINT);
        return terrenos.map(terrenoMapper::terrenoToFeedDTO);
    }

//    Page<Terreno> terrenos = terrenoRepository.findAll(pageable);
//        return terrenos.map(terrenoMapper::terrenoToFeedDTO);


//    public List<ResponseFeedDTO> buscarTerrenos(String preco, String campoDeBusca, Estados estado, String tamanho) {
//
//        List<ResponseFeedDTO> terrenos = terrenoRepository.;
//
////        return filtrarTerrenos(terrenos, campoDeBusca)
////                .stream()
////                .map(FeedMapper::FeedParaResponseFeed)
////                .collect(Collectors.toList());
//        return terrenos;
//    }
//
//    private List<Feed> filtrarTerrenos(List<Feed> terrenos, String campoDeBusca) {
//
//        List<Feed> terrenosFiltrados = new ArrayList<>();
//        List<String> palavrasChaveList = Arrays.stream(campoDeBusca.split(" ")).toList();
//        for (Feed terreno : terrenos) {
//            boolean encontrado = true;
//            for (String palavraChave : palavrasChaveList) {
//                if (!terreno.getDescricao().toLowerCase().contains(palavraChave.toLowerCase()) && !terreno.getTitulo().toLowerCase().contains(palavraChave.toLowerCase())) {
//                    encontrado = false;
//                    break;
//                }
//            }
//            if (encontrado) {
//                terrenosFiltrados.add(terreno);
//            }
//        }
//        return terrenosFiltrados;
//    }

    public List<ResponseFeedQuantidadeAnunciosDTO> quantidadeAnuncios() {
        return terrenoRepository.quantidadeAnuncios();
    }
}
