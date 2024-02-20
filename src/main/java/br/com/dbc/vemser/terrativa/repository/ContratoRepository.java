package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    List<Contrato> findAllByLocatarioID(Integer locatarioId);

    @Query(value = """
            SELECT  * FROM VS_13_EQUIPE_8.CONTRATOS c
            LEFT JOIN VS_13_EQUIPE_8.USUARIOS u ON (c.LOCATARIO_ID = u.USUARIO_ID)
            LEFT JOIN VS_13_EQUIPE_8.TERRENOS t ON (c.TERRENO_ID  = t.TERRENO_ID)
            LEFT JOIN VS_13_EQUIPE_8.USUARIOS u2 ON (t.DONO_ID = u2.USUARIO_ID)
            LEFT JOIN VS_13_EQUIPE_8.ENDERECO_TERRENOS et ON (t.ENDERECO_TERRENO_ID = et.ENDERECO_TERRENO_ID)
            LEFT JOIN VS_13_EQUIPE_8.ESTADO_MUNICIPIOS em ON(et.MUNICIPIO_COD_IBGE = em.MUNICIPIO_COD_IBGE)
            WHERE c.CONTRATO_ID = ?1
            """, nativeQuery = true)
    Contrato retornaContratoPorID(Integer id);
}
