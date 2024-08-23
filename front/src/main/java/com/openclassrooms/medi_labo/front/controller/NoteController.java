package com.openclassrooms.medi_labo.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.medi_labo.front.config.GatewayClient;
import com.openclassrooms.medi_labo.front.model.Note;

@Controller
public class NoteController {

	
	@Autowired
	private GatewayClient gatewayClient;
	
	@GetMapping("/note/{id}")
	public String getListPatient(@PathVariable("id") int id, Model model) {
		
		List<Note> notes = gatewayClient.getNotesByPatientId(id);
		model.addAttribute("noteLists", notes);
		model.addAttribute("id", id); // pour pouvoir rajouter une note sur l'id souhaité
		return "note/list-note";
	}

	@GetMapping("/note/add/{id}/{nom}")
	public String getNotePatient(@PathVariable("id") String id, @PathVariable("nom") String nom, Model model) {
		Note note = new Note();
		note.setPatId(id);
		note.setPatient(nom);
		model.addAttribute("note", note);
		return "note/add-note";
	}
	
	// pour avoir la meme url
	@PostMapping("/note/add/{id}/{nom}")
	public String addNotePatient(Note note, Model model, BindingResult result) {

		if (note.getNote().isBlank())
			result.rejectValue("note", null, "Veuillez écrire une note.");
		
		if (result.hasErrors())
			return "note/add-note";
		note.setId(null);
		gatewayClient.createNote(note);

		return "redirect:/note/" + note.getPatId();
	}
	
	@GetMapping("/note/update/{id}")
	public String modifyNotePatient(@PathVariable("id") String id, Model model) {
		Note note = gatewayClient.getNoteById(id);
		model.addAttribute("note", note);
		return "note/update-note";
	}
	
	// pour avoir la meme url
	@PostMapping("/note/update/{id}")
	public String modifyNotePatient(Note note, Model model, BindingResult result) {

		if (note.getNote().isBlank())
			result.rejectValue("note", null, "Veuillez écrire une note.");
		
		if (result.hasErrors())
			return "note/update-note";
		
		gatewayClient.modifyNote(note);

		return "redirect:/note/" + note.getPatId();
	}
	
	
	@GetMapping("/note/delete/{id}/{patId}")
	public String deleteNotePatient(@PathVariable("id") String id, @PathVariable("patId") String patId,  Model model) {
		gatewayClient.deleteNote(id);
		return "redirect:/note/" + patId;
	}
	
}
