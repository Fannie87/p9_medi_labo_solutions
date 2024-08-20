package com.openclassrooms.medi_labo.api_2_note.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.openclassrooms.medi_labo.api_2_note.model.Note;

public interface NoteRepository extends MongoRepository<Note, String> {
	
	@Query("{patId:'?0'}")
	List<Note> findItemByPatId(String patId);

}
