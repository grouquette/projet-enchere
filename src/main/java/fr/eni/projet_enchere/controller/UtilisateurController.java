package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.projet_enchere.bll.EnchereService;
import fr.eni.projet_enchere.bll.UtilisateurService;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.exception.BusinessException;
import jakarta.validation.Valid;

@Controller

@RequestMapping("/utilisateurs")
public class UtilisateurController {

	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	@GetMapping
	public String afficherUtilisateurs() {
		return "view-utilisateurs";
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
<<<<<<< HEAD

=======
>>>>>>> 81d5eebc0a5de5aca5d5e9d22a1bca3bcf2baa70
		return "view-utilisateur-creation";
	}

	@PostMapping("/signin")
<<<<<<< HEAD
	public String creerUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		this.utilisateurService.creerUtilisateur(utilisateur);

		return "redirect:/utilisateurs";
=======
	public String creerUtilisateur(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult)
			throws BusinessException {
		if (bindingResult.hasErrors()) {
			// Retourne la vue avec les erreurs affichées
			return "view-utilisateur-creation";
		} else {
			try {
				this.utilisateurService.creerUtilisateur(utilisateur);
				return "redirect:/utilisateurs";
			} catch (BusinessException e) {
				e.printStackTrace();
				e.getListeMessage().forEach(m -> {
					ObjectError error = new ObjectError("globalError", m);
					bindingResult.addError(error);
				});
				return "view-utilisateur-creation";
			}
		}
>>>>>>> 81d5eebc0a5de5aca5d5e9d22a1bca3bcf2baa70
	}

}
