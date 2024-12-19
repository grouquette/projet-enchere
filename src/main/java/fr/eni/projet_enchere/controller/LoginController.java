package fr.eni.projet_enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet_enchere.bll.contexte.ContexteService;
import fr.eni.projet_enchere.bo.Utilisateur;

@Controller
@SessionAttributes("utilisateurConnecte")
public class LoginController {
	@Autowired
	private final ContexteService contexteService;

	public LoginController(ContexteService contexteService) {
		super();
		this.contexteService = contexteService;
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
