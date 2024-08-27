package com.openclassrooms.medi_labo.api_1_patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.medi_labo.api_1_patient.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
