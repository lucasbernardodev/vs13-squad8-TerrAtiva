package br.com.dbc.vemser.terrativa.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertiesReader {
    @Value("${admin}")
    private Boolean admin;
    @Value("${spring.datasource.username}")
    private String dbuser;
    @Value("${spring.datasource.password}")
    private String dbpassword;
    @Value("${spring.datasource.url}")
    private String dburl;
    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private String SCHEMA;

    @Value("${spring.mail.username}")
    private String emailFrom;

}
