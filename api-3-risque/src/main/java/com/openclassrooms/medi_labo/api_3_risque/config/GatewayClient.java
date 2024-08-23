package com.openclassrooms.medi_labo.api_3_risque.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.medi_labo.api_3_risque.model.Note;
import com.openclassrooms.medi_labo.api_3_risque.model.Patient;

@FeignClient(name = "gateway", url = "http://localhost:8090")
public interface GatewayClient {

//    //////////// PATIENT-SERVICE/////////////
    @GetMapping(value = "/api-1-patient/{id}")
    Patient getPatient(@PathVariable("id") int id);
//
//    ////////////NOTE-SERVICE/////////////
    @GetMapping(value = "/api-2-note/patId/{patId}")
    List<Note> getNotesByPatientId(@PathVariable("patId") int patId);
//
}