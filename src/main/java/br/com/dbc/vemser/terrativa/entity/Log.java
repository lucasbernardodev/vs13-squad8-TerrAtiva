package br.com.dbc.vemser.terrativa.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Setter
@Document(collection = "logs")
public class Log {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TipoLog tipoLog;
    private String descricao;
    private String data;
}