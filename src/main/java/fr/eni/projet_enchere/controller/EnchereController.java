package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnchereController {

	// Affiche la liste des films
	@GetMapping("/encheres")
	public String afficherListeDesEncheres() {
		return "view-encheres";
	}

}
