package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedQuantidadeAnunciosDTO;
import br.com.dbc.vemser.terrativa.entity.Terreno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrenoRepository extends JpaRepository<Terreno, Integer> {

    List<Terreno> findAllByProprietarioID(Integer donoID);

    List<Terreno> findAllByDisponivelEqualsAndProprietarioID(String disponivel, Integer donoID);


    @Query(value = """
            SELECT * FROM VS_13_EQUIPE_8.TERRENOS t
            LEFT JOIN VS_13_EQUIPE_8.USUARIOS u ON (t.DONO_ID = u.USUARIO_ID)
            LEFT JOIN VS_13_EQUIPE_8.ENDERECO_TERRENOS et ON (t.ENDERECO_TERRENO_ID = et.ENDERECO_TERRENO_ID)
            LEFT JOIN VS_13_EQUIPE_8.ESTADO_MUNICIPIOS em ON (et.MUNICIPIO_COD_IBGE = em.MUNICIPIO_COD_IBGE)
            WHERE (UPPER(t.TITULO) LIKE UPPER('%'||:campoDeBuscaTitulo||'%') OR
            UPPER(t.DESCRICAO) LIKE UPPER('%'||:campoDeBuscaDescricao||'%')) AND
            (t.PRECO BETWEEN NVL(TO_NUMBER(:precoInicial), t.PRECO) AND NVL(TO_NUMBER(:precoFinal), t.PRECO)) AND
            (em.ESTADO_COD = NVL(TO_NUMBER(:estadoCod), em.ESTADO_COD))
            """,
            nativeQuery = true,
            countQuery = """
                    SELECT COUNT(*) FROM VS_13_EQUIPE_8.TERRENOS t
                    LEFT JOIN VS_13_EQUIPE_8.USUARIOS u ON (t.DONO_ID = u.USUARIO_ID)
                    LEFT JOIN VS_13_EQUIPE_8.ENDERECO_TERRENOS et ON (t.ENDERECO_TERRENO_ID = et.ENDERECO_TERRENO_ID)
                    LEFT JOIN VS_13_EQUIPE_8.ESTADO_MUNICIPIOS em ON (et.MUNICIPIO_COD_IBGE = em.MUNICIPIO_COD_IBGE)
                    WHERE (UPPER(t.TITULO) LIKE UPPER('%'||:campoDeBuscaTitulo||'%') OR
                    UPPER(t.DESCRICAO) LIKE UPPER('%'||:campoDeBuscaDescricao||'%')) AND
                    (t.PRECO BETWEEN NVL(TO_NUMBER(:precoInicial), t.PRECO) AND NVL(TO_NUMBER(:precoFinal), t.PRECO)) AND
                    (em.ESTADO_COD = NVL(TO_NUMBER(:estadoCod), em.ESTADO_COD))
            """)
    Page<Terreno> buscarFeedComFiltros(Pageable pageable, @Param("campoDeBuscaTitulo") String campoDeBuscaTitulo,
                                       @Param("campoDeBuscaDescricao") String campoDeBuscaDescricao, @Param("precoInicial") Integer precoInicial,
                                       @Param("precoFinal") Integer precoFinal,
                                       @Param("estadoCod") Integer estadoCod);


    @Query("""
    SELECT new br.com.dbc.vemser.terrativa.dto.responses.ResponseFeedQuantidadeAnunciosDTO(
        t.enderecoTerrenoID.codIBGE.nomeEstado,
        COUNT(*),
        em.estadoCod
    )
    FROM TERRENOS t
    JOIN ENDERECO_TERRENOS et ON t.enderecoID = et.id
    JOIN ESTADO_MUNICIPIOS em ON et.codMunicipioIBGE = em.municipioCodIBGE
    WHERE t.disponivel = 'S'
    GROUP BY t.enderecoTerrenoID.codIBGE.nomeEstado, em.estadoCod
    """)
    Page<ResponseFeedQuantidadeAnunciosDTO> quantidadeAnuncios(Pageable pageable);


}