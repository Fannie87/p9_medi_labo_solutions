package com.openclassrooms.medi_labo.front.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.medi_labo.front.model.Patient;

@Controller
public class PatientListController {

	@GetMapping("/listpatient")
	public String getListPatient(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Patient[]> entity = restTemplate.getForEntity("http://localhost:8081/api-1-patient",
				Patient[].class);
		model.addAttribute("patientLists", entity.getBody());

		return "form/list-patient";
	}

	@GetMapping("/patient/update/{id}")
	public String getUpdate(@PathVariable("id") Integer id, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Patient> entity = restTemplate.getForEntity("http://localhost:8081/api-1-patient/" + id,
				Patient.class);
		model.addAttribute("patient", entity.getBody());
		return "form/update-patient";
	}

	@PostMapping("/patient/update/{id}")
	public String updatePatient(@PathVariable("id") Integer id, Patient patient, BindingResult result, Model model) {
		validatePatient(patient, result);
		if (result.hasErrors())
			return "form/update-patient";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put("http://localhost:8081/api-1-patient/" + id, patient);
		return getListPatient(model);
	}

	@GetMapping("/patient/create")
	public String getCreate(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "form/create-patient";
	}
	
	@PostMapping("/patient/create")
	public String createPatient(Patient patient, Model model, BindingResult result) {
		validatePatient(patient, result);
		if (result.hasErrors())
			return "form/create-patient";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity("http://localhost:8081/api-1-patient/" , patient, Void.class);
		return getListPatient(model);
	}
	
	
	@GetMapping("/patient/delete/{id}")
	public String getDelete(@PathVariable("id") Integer id, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8081/api-1-patient/" + id);
		return getListPatient(model);
	}

	private void validatePatient(Patient patient, BindingResult result) {
		if (patient.getNom().isBlank())
			result.rejectValue("nom", null, "Nom est obligatoire");
		
		if (patient.getPrenom().isBlank())
			result.rejectValue("prenom", null, "Prenom est obligatoire");
		
		if (patient.getDateNaissance().isBlank())
			result.rejectValue("dateNaissance", null, "Date de naissance est obligatoire");
		
		if (patient.getGenre().isBlank() || (!patient.getGenre().equals("H") && !patient.getGenre().equals("F")))
			result.rejectValue("genre", null, "Genre est obligatoire H ou F");
	}
}
