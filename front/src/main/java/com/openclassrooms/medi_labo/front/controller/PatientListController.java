package com.openclassrooms.medi_labo.front.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.medi_labo.front.model.Patient;

@Controller
public class PatientListController {

	@GetMapping("/listpatient")
	public String getListPatient(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Patient[]> getEntity = restTemplate.getForEntity("http://localhost:8081/api-1-patient", Patient[].class);
		model.addAttribute("patientLists",getEntity.getBody());
		
		return "listpatient";
	}
	
	
}
