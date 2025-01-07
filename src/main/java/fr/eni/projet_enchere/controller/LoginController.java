package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet_enchere.bo.Utilisateur;

@Controller
@SessionAttributes("utilisateurConnecte")
public class LoginController {
	public LoginController() {
		super();
	}

	@ModelAttribute("utilisateurConnecte")
	public Utilisateur utilisateurConnecte() {
		return new Utilisateur();
	}

	@GetMapping("/login")
	public String login(Model model) {
		if (!model.containsAttribute("utilisateurConnecte")) {
			model.addAttribute("utilisateurConnecte", new Utilisateur());
		}
		return "login";
	}
}
