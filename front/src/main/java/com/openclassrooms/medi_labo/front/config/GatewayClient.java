package com.openclassrooms.medi_labo.front.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.medi_labo.front.model.Note;
import com.openclassrooms.medi_labo.front.model.Patient;

@FeignClient(name = "gateway", url = "http://localhost:8090")
public interface GatewayClient {

    //////////// PATIENT-SERVICE/////////////
    @GetMapping(value = "/api-1-patient/{id}")
    Patient getPatient(@PathVariable("id") int id);

    @GetMapping(value = "/api-1-patient")
    Patient getPatient(@RequestParam String firstName, @RequestParam String lastName);

    @GetMapping(value = "/api-1-patient/")
    List<Patient> getPatients();

    @PostMapping(value = "/api-1-patient/")
    void createPatient(Patient patient);

    @PutMapping(value = "/api-1-patient/")
    void updatePatient(Patient patient);

    @DeleteMapping(value = "/api-1-patient/{id}")
    void deletePatient(@PathVariable("id") int id);


    ////////////NOTE-SERVICE/////////////
    @GetMapping(value = "/api-2-note/{id}")
    Note getNoteById(@PathVariable("id") String id);

    
    @GetMapping(value = "/api-2-note/patId/{patId}")
    List<Note> getNotesByPatientId(@PathVariable("patId") int patId);

    @PostMapping(value = "/api-2-note/")
    void createNote(Note note);
    
    @PutMapping(value = "/api-2-note/")
    void modifyNote(Note note);

    @DeleteMapping(value = "/api-2-note/{id}")
    void deleteNote(@PathVariable("id") String id);


    ////////////DIABETES-ASSESSMENT-SERVICE/////////////
    @GetMapping(value = "/api-3-risque/{id}")
    String getRisque(@PathVariable("id") int id);
}