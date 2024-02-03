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

}