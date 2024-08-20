package com.openclassrooms.medi_labo.api_2_note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medi_labo.api_2_note.model.Note;
import com.openclassrooms.medi_labo.api_2_note.repository.NoteRepository;

@RestController
public class NoteRestController {

	  @Autowired
	  private NoteRepository noteRepository;
	  

	  @GetMapping("{id}")
	  public List<Note> getOne(@PathVariable String id) {
	    return noteRepository.findItemByPatId(id);
	  }

	  @PostMapping
	  public Note insert(@RequestBody Note noteItem) {
	    return noteRepository.insert(noteItem);
	  }
}
