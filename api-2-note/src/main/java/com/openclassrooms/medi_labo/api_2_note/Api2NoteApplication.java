package com.openclassrooms.medi_labo.api_2_note;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Api2NoteApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(Api2NoteApplication.class, args);
	}
	
}
