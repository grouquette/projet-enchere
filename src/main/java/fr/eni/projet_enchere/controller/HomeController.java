package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "redirect:/encheres"; // Remplacez "ma-page" par le nom de votre fichier HTML (sans l'extension .html)
	}
}
