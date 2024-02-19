package br.com.dbc.vemser.terrativa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
public class TerrativaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerrativaApplication.class, args);
	}

}
