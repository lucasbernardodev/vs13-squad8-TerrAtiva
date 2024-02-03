package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseFeedQuantidadeAnunciosDTO;
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
            ESTADO_MUNICIPIOS.nomeEstado,
            COUNT(*),
            ESTADO_MUNICIPIOS.estadoCod
        )
        FROM TERRENOS t
        JOIN ENDERECO_TERRENOS et ON t.enderecoID = et.id
        JOIN ESTADO_MUNICIPIOS em ON et.codMunicipioIBGE = em.municipioCodIBGE
        WHERE t.disponivel = 'S'
        GROUP BY em.nomeEstado , em.estadoCod
        ORDER BY em.nomeEstado
    """)
    List<ResponseFeedQuantidadeAnunciosDTO> quantidadeAnuncios();



//    @Query(value = """
//    SELECT NOME_ESTADO as nomeEstado, COUNT(*) as quantidadeAnuncios, em.ESTADO_COD as codigoEstado
//    FROM TERRENOS t
//    JOIN ENDERECO_TERRENOS et ON t.ENDERECO_TERRENO_ID = et.ENDERECO_TERRENO_ID
//    JOIN ESTADO_MUNICIPIOS em ON et.MUNICIPIO_COD_IBGE = em.MUNICIPIO_COD_IBGE
//    WHERE t.DISPONIVEL = 'S'
//    GROUP BY NOME_ESTADO, em.ESTADO_COD
//    ORDER BY NOME_ESTADO
//""", nativeQuery = true)
//    List<ResponseFeedQuantidadeAnunciosDTO> quantidadeAnuncios();
}