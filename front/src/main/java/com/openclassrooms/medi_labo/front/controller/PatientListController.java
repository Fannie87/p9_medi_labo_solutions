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

}
