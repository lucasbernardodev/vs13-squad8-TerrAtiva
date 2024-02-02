package br.com.dbc.vemser.terrativa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class TerrativaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerrativaApplication.class, args);
	}

}
