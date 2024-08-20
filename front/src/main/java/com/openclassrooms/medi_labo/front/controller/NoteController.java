package com.openclassrooms.medi_labo.front.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.medi_labo.front.model.Note;

@Controller
public class NoteController {

	@GetMapping("/patient/note/{id}")
	public String getListPatient(@PathVariable("id") String id, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Note[]> entity = restTemplate.getForEntity("http://localhost:8090/api-2-note/" + id,
				Note[].class);
		model.addAttribute("noteLists", entity.getBody());
		model.addAttribute("id", id); // pour pouvoir rajouter une note sur l'id souhaité
		return "form/list-note";
	}

	@GetMapping("/patient/note/add/{id}/{nom}")
	public String getNotePatient(@PathVariable("id") String id, @PathVariable("nom") String nom, Model model) {
		Note note = new Note();
		note.setPatId(id);
		note.setPatient(nom);
		model.addAttribute("note", note);
		return "form/add-note";
	}

	@PostMapping("/patient/note/add/{id}/{nom}")
	public String addNotePatient(Note note, Model model, BindingResult result) {

		if (note.getNote().isBlank())
			result.rejectValue("note", null, "Veuillez écrire une note.");
		
		if (result.hasErrors())
			return "form/add-note";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity("http://localhost:8090/api-2-note/", note, Void.class);
		return "redirect:/patient/note/" + note.getPatId();
	}
}
