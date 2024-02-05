package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.TerrenoMapper;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedQuantidadeAnunciosDTO;
import br.com.dbc.vemser.terrativa.entity.Estados;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import br.com.dbc.vemser.terrativa.repository.TerrenoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Page<ResponseFeedQuantidadeAnunciosDTO> quantidadeAnuncios(Pageable pageable) {
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("enderecoTerrenoID.codIBGE.nomeEstado").ascending());
        return terrenoRepository.quantidadeAnuncios(page);
    }
}
