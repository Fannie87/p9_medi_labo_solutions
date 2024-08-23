package com.openclassrooms.medi_labo.api_3_risque.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medi_labo.api_3_risque.config.GatewayClient;
import com.openclassrooms.medi_labo.api_3_risque.model.Note;
import com.openclassrooms.medi_labo.api_3_risque.model.Patient;

@RestController
public class RisqueController {

	private static final String NONE = "None";
	private static final String BORDERLINE = "Borderline";
	private static final String IN_DANGER = "In Danger";
	private static final String EARLY_ONSET = "Early onset";
	
	@Autowired
	private GatewayClient gatewayClient;
	
	private List<String> declencheurs = Arrays.asList("hémoglobine A1C", //
			"microalbumine", //
			"taille", //
			"poids", //
			"fumeur", //
			"fumeuse", //
			"fumer", //
			"anormal", //
			"cholestérol", //
			"vertige", //
			"rechute", //
			"réaction", //
			"anticorps");

	@GetMapping("{id}")
	public String getRisques(@PathVariable int id) {
		Patient patient = gatewayClient.getPatient(id);
		
		List<Note> notes = gatewayClient.getNotesByPatientId(id);
		
		// Calcul age
		LocalDate dateDujour = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.FRENCH);
		LocalDate dateNaissance = LocalDate.parse(patient.getDateNaissance(), formatter);

		int age = Period.between(dateNaissance, dateDujour).getYears();

		// Récupération des déclencheurs
		int compteurDeclencheur = 0;
		for (Note note : notes)
			for (String declencheur : declencheurs)
				if (note.getNote().toLowerCase().contains(declencheur))
					compteurDeclencheur++;

		if (compteurDeclencheur == 0)
			return NONE;

		if (compteurDeclencheur >= 2 && compteurDeclencheur <= 5 && age >= 30) 
			return BORDERLINE;
		
		if (patient.getGenre().equals("M") && age <= 30 && compteurDeclencheur >= 3 && compteurDeclencheur < 5) 
			return IN_DANGER;
		
		if (patient.getGenre().equals("F") && age <= 30 && compteurDeclencheur >= 4 && compteurDeclencheur < 7) 
			return IN_DANGER;
		
		if (age >= 30 && (compteurDeclencheur == 6 || compteurDeclencheur == 7)) 
			return IN_DANGER;
		
		if (patient.getGenre().equals("M") && age <= 30 && compteurDeclencheur >= 5) 
			return EARLY_ONSET;
		
		if (patient.getGenre().equals("F") && age <= 30 && compteurDeclencheur >= 7) 
			return EARLY_ONSET;
		
		if (age >= 30 && compteurDeclencheur >= 8) 
			return EARLY_ONSET;
		
		return NONE;

	}

}
