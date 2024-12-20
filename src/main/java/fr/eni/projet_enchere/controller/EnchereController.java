package fr.eni.projet_enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet_enchere.bll.EnchereService;
import fr.eni.projet_enchere.bll.contexte.ContexteService;
import fr.eni.projet_enchere.bo.Enchere;

@Controller
@SessionAttributes("encheresSession")
public class EnchereController {
	@Autowired
	private EnchereService enchereService;
//	private final ContexteService contexteService;

	public EnchereController(EnchereService enchereService, ContexteService contexteService) {
		this.enchereService = enchereService;
//		this.contexteService = contexteService;

	}

//	@GetMapping("/encheres")
//	public String afficherListeDesEncheres() {
//		return "view-encheres";
//	}
//
//	@ModelAttribute("encheresSession")
//	public List<Enchere> chargerEnchereEnSession() {
//		return this.contexteService.getAllEncheres(); // Cette méthode doit maintenant retourner une List<Membre>
//	}

	@PostMapping("/creerEnchere")
	public String creerEnchere(@ModelAttribute Enchere enchere) {
		this.enchereService.add(enchere);
		return "redirect:/view-details-vente";
	}

}
