package br.com.dbc.vemser.terrativa.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

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
