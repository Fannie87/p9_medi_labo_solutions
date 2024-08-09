package com.openclassrooms.medi_labo.api_1_patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medi_labo.api_1_patient.model.Patient;
import com.openclassrooms.medi_labo.api_1_patient.repository.PatientRepository;

@RestController
public class Controller {

	@Autowired
	PatientRepository patientRepository;

	@GetMapping
	public List<Patient> getListPatient() {
		return patientRepository.findAll();
	}
}
