package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/utilisateurs") 
public class UtilisateurController {
	
	@GetMapping
	public String afficherUtilisateur() {
		return "view-utilisateur";
	}
	
	@GetMapping("/detail")
	public String afficherDetailUtilisateur() {
		return "view-utilisateur-detail";
	}
	
	@PostMapping("/detail")
	public String mettreAJourUtilisateur() {
		return "redirect:/utilisateurs";
	}
	
	@GetMapping("/signin")
	public String afficherCreationUtilisateur() {
		return "view-utilisateur-creation";
	}
	
	@PostMapping("/signin")
	public String creerUtilisateur() {
		return "redirect:/utilisateurs";
	}
	
}
