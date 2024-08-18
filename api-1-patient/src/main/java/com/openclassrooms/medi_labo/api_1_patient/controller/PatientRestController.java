package com.openclassrooms.medi_labo.api_1_patient.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medi_labo.api_1_patient.model.Patient;
import com.openclassrooms.medi_labo.api_1_patient.repository.PatientRepository;

@RestController
public class PatientRestController {

	@Autowired
	PatientRepository patientRepository;

	@GetMapping
	public List<Patient> getListPatient() {
		return patientRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Patient> getPatient(@PathVariable("id") Integer id) {
		return patientRepository.findById(id);
	}

	@PutMapping("/{id}")
	public void putPatient(@PathVariable("id") Integer id,@RequestBody Patient patient) {
		patientRepository.save(patient);
	}
	
	
	@PostMapping
	public void postPatient(@RequestBody Patient patient) {
		patientRepository.save(patient);
	}
	
	@DeleteMapping ("/{id}")
	public void deletePatient (@PathVariable("id") Integer id) {
		patientRepository.deleteById(id);
	}

}
