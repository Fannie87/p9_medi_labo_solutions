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
import com.openclassrooms.medi_labo.front.model.Patient;

@Controller
public class PatientListController {

	
	@Autowired
	private GatewayClient gatewayClient;
	
	@GetMapping("/listpatient")
	public String getListPatient(Model model) {
		List<Patient> patients = gatewayClient.getPatients();
		
		for (Patient patient : patients) {
			String risque = gatewayClient.getRisque(patient.getId());
			patient.setRisque(risque);
		}
		
		model.addAttribute("patientLists", patients);
		return "patient/list-patient";
	}

	@GetMapping("/patient/update/{id}")
	public String getUpdate(@PathVariable("id") Integer id, Model model) {
		Patient patient = gatewayClient.getPatient(id);
		model.addAttribute("patient", patient);
		return "patient/update-patient";
	}

	@PostMapping("/patient/update/{id}")
	public String updatePatient(@PathVariable("id") Integer id, Patient patient, BindingResult result, Model model) {
		validatePatient(patient, result);
		if (result.hasErrors())
			return "patient/update-patient";
		gatewayClient.updatePatient(patient);

		return "redirect:/listpatient";
	}

	@GetMapping("/patient/create")
	public String getCreate(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "patient/create-patient";
	}

	@PostMapping("/patient/create")
	public String createPatient(Patient patient, Model model, BindingResult result) {
		validatePatient(patient, result);
		if (result.hasErrors())
			return "patient/create-patient";
		gatewayClient.createPatient(patient);
		return "redirect:/listpatient";
	}

	@GetMapping("/patient/delete/{id}")
	public String getDelete(@PathVariable("id") Integer id, Model model) {
		gatewayClient.deletePatient(id);
		return "redirect:/listpatient";
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
