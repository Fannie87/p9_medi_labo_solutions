package com.openclassrooms.medi_labo.api_3_risque.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.medi_labo.api_3_risque.model.Note;
import com.openclassrooms.medi_labo.api_3_risque.model.Patient;

@RestController
public class RisqueController {


	@GetMapping("{id}")
	public void getRisque(@PathVariable String id) {
		RestTemplate restTemplate = new RestTemplate();
		Patient patient = restTemplate.getForEntity("http://localhost:8090/api-1-patient/" + id, Patient.class).getBody();
		Note[] notes = restTemplate.getForEntity("http://localhost:8090/api-2-note/" + id,
				Note[].class).getBody();
	
	}


}
