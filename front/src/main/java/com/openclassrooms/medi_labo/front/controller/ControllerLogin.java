package com.openclassrooms.medi_labo.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.openclassrooms.medi_labo.front.model.UserAuthentification;
@Controller
public class ControllerLogin {
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		UserAuthentification userAuthentification = new UserAuthentification();
		userAuthentification.setUsername("user");
		userAuthentification.setPassword("password");
		
		model.addAttribute("userAuthentification", userAuthentification);
		
		return "login";
	}
	
	@GetMapping("/")
	public String getIndex() {
		return "redirect:listpatient";
	}
}
