package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.projet_enchere.bll.EnchereService;
import fr.eni.projet_enchere.bo.Enchere;

@Controller
public class EnchereController {
	
	private EnchereService enchereService;

	public EnchereController(EnchereService enchereService) {
		this.enchereService = enchereService;
	}

	// Affiche la liste des films
	@GetMapping("/encheres")
	public String afficherListeDesEncheres() {
		return "view-encheres";
	}

	@PostMapping("/creerEnchere")
	public String creerEnchere(@ModelAttribute Enchere enchere) {
		this.enchereService.add(enchere);
		return "redirect:/view-details-vente";
	}


}
