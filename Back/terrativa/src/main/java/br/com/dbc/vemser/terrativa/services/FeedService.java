package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedService {
    private final TerrenoRepository terrenoRepository;

    public List<Terreno> listarTerrenos() {
        return terrenoRepository.findAll();
    }

    public List<Terreno> listarTerrenos(Integer id) {
        return terrenoRepository.findById(id).stream().toList();
    }


    public List<Terreno> buscarTerrenos(String preco, String campoDeBusca, String estado, String tamanho) {
        return null;
    }

    public List<Terreno> quantidadeAnuncios() {
        return null;
    }

//    public List<ResponseFeedDTO> buscarTerrenos(String preco, String campoDeBusca, Estados estado, String tamanho) {
//
//        List<ResponseFeedDTO> terrenos = feedRepository.findAll();
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
//
//    public List<ResponseFeedQuantidadeAnunciosDTO> quantidadeAnuncios() {
//        return feedRepository.quantidadeAnuncios();
//    }
}
