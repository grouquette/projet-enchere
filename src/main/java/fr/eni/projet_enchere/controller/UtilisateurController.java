package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.projet_enchere.bll.UtilisateurService;
import fr.eni.projet_enchere.bo.Utilisateur;

@Controller

@RequestMapping("/utilisateurs") 
public class UtilisateurController {
	
	private UtilisateurService utilisateurService;
	
	
	
	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

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
	public String afficherCreationUtilisateur(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		
		return "view-utilisateur-creation";
	}
	
	@PostMapping("/signin")
	public String creerUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		this.utilisateurService.creerUtilisateur(utilisateur);
		
		return "redirect:/utilisateurs";
	}
	
}
