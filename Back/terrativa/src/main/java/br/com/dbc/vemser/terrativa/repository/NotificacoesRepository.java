package br.com.dbc.vemser.terrativa.repository;

import br.com.dbc.vemser.terrativa.entity.Log;
import br.com.dbc.vemser.terrativa.entity.Notificacoes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface NotificacoesRepository extends MongoRepository<Notificacoes, String> {

    List<Notificacoes> findAllByDataBefore(LocalDate data);
    List<Notificacoes> findAllByDataAfter(LocalDate data);
    List<Notificacoes> findAllByUsuariosContains(Integer id);

}
