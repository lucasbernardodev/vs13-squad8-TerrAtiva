package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedFiltradoDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedQuantidadeAnunciosDTO;
import br.com.dbc.vemser.terrativa.entity.Estados;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrenoRepository extends JpaRepository<Terreno, Integer> {

    List<Terreno> findAllByProprietarioID(Integer donoID);

    List<Terreno> findAllByDisponivelEqualsAndProprietarioID(String disponivel, Integer donoID);

    @Query("""
    SELECT new br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedQuantidadeAnunciosDTO(
        em.nomeEstado,
        COUNT(*),
        em.estadoCod
    )
    FROM TERRENOS t
    JOIN ENDERECO_TERRENOS et ON t.enderecoID = et.id
    JOIN ESTADO_MUNICIPIOS em ON et.codMunicipioIBGE = em.municipioCodIBGE
    WHERE t.disponivel = 'S'
    GROUP BY em.nomeEstado, em.estadoCod
    ORDER BY em.nomeEstado
""")
    List<ResponseFeedQuantidadeAnunciosDTO> quantidadeAnuncios();

//    @Query("""
//    SELECT new br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedFiltradoDTO(
//        t.id,
//        t.titulo,
//        t.preco,
//        t.tamanho,
//        t.criado,
//        em.nomeMunicipio,
//        em.nomeEstado)
//     FROM TERRENOS t
//        JOIN ENDERECO_TERRENOS et
//        ON t.enderecoID = et.id
//        JOIN ESTADO_MUNICIPIOS em
//        ON et.codMunicipioIBGE = em.municipioCodIBGE
//        WHERE t.disponivel = 'S'
//            AND t.preco BETWEEN
//                (NVL(SELECT regexp_replace(?, '[^0-9]', ''), 0))
//                AND
//                (NVL(SELECT regexp_replace(?, '[^0-9]', ''), 100000000))
//            AND t.tamanho
//                BETWEEN
//                (NVL((SELECT regexp_replace(?, '[^0-9]', '') - 10000 FROM DUAL), 0))
//                AND
//                (NVL((SELECT regexp_replace(?, '[^0-9]', '') + 10000 FROM DUAL), 100000000))
//            AND (NVL(em.estadoCod = ?, 0))
//    """)
//        List<ResponseFeedFiltradoDTO> feedFiltrado(Double precoMinimo, Double precoMaximo, Integer tamanhoMinimo, Integer tamanhoMaximo, Estados estado, String tamanho);
}