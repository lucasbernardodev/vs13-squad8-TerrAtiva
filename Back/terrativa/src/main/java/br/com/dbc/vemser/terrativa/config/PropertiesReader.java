package br.com.dbc.vemser.terrativa.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertiesReader {
    @Value("${admin}")
    private Boolean admin;
    @Value("${dbuser}")
    private String dbuser;
    @Value("${dbpassword}")
    private String dbpassword;
    @Value("${dburl}")
    private String dburl;

}
