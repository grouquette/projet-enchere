package fr.eni.projet_enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import fr.eni.projet_enchere.bll.UtilisateurService;
import fr.eni.projet_enchere.bo.Utilisateur;

@Controller
@RequestMapping("/profil")
public class ProfilController {
	
	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping("/{id}")
	public String afficherProfilVendeur(@PathVariable("id") Long id, Model model) {
		// Récupérer les informations du vendeur depuis la base de données
		Utilisateur vendeur = utilisateurService.consulterProfilUtilisateurParId(id);
		if (vendeur == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
		}
		model.addAttribute("utilisateurVendeur", vendeur);
		return "view-vendeur"; // Le nom du fichier HTML pour cette vue
	}
}
