package fr.eni.projet_enchere.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet_enchere.bll.UtilisateurService;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.exception.BusinessException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/utilisateur")
@SessionAttributes("utilisateurConnecte")
public class UtilisateurController {

	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	@GetMapping("/signin")
	public String afficherCreationUtilisateur(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "view-utilisateur-creation";
	}

	@PostMapping("/signin")
	public String creerUtilisateur(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult)
			throws BusinessException {
		if (bindingResult.hasErrors()) {
			// Retourne la vue avec les erreurs affichées
			return "view-utilisateur-creation";
		} else {
			try {
				this.utilisateurService.creerUtilisateur(utilisateur);
				return "redirect:/encheres";
			} catch (BusinessException e) {
				e.printStackTrace();
				e.getListeMessage().forEach(m -> {
					ObjectError error = new ObjectError("globalError", m);
					bindingResult.addError(error);
				});
				return "view-utilisateur-creation";
			}
		}
	}

	@GetMapping
	public String afficherUtilisateur(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			User userDetails = (User) authentication.getPrincipal();
			String pseudo = userDetails.getUsername();
			Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);

			model.addAttribute("utilisateurConnecte", utilisateur);
		} else {
			model.addAttribute("utilisateurConnecte", null);
		}
		return "view-utilisateur";
	}
	
	@GetMapping("/profil/{noUtilisateur}")
	public String afficherProfil(@PathVariable Long noUtilisateur, Model model) {
	    Utilisateur utilisateur = utilisateurService.consulterProfilUtilisateurParId(noUtilisateur);
	    model.addAttribute("utilisateur", utilisateur);
	    return "view-utilisateur"; // Nom de la vue pour afficher le profil
	}

	@GetMapping("/modifier")
	public String afficherModificationUtilisateurs(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			User userDetails = (User) authentication.getPrincipal();
			String pseudo = userDetails.getUsername();
			Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);

			model.addAttribute("utilisateurConnecte", utilisateur);
		} else {
			model.addAttribute("utilisateurConnecte", null);
		}
		return "view-utilisateur-modifier";
	}

	@PostMapping("/modifier")
	public String mettreAJourUtilisateur(@Valid @ModelAttribute("utilisateurConnecte") Utilisateur utilisateurConnecte, BindingResult bindingResult) {
		// Appel au service pour mettre à jour l'utilisateur
		if (bindingResult.hasErrors()) {
			// Retourne la vue avec les erreurs affichées
			return "view-utilisateur-modifier";
		}else {
			try {
				this.utilisateurService.modifierUtilisateur(utilisateurConnecte);
				return "redirect:/utilisateur";
			} catch (BusinessException e) {
				e.printStackTrace();
				e.getListeMessage().forEach(m -> {
					ObjectError error = new ObjectError("globalError", m);
					bindingResult.addError(error);
				});
				System.out.println(bindingResult.getAllErrors());
				return "view-utilisateur-modifier";
			}
		}
	}

	@PostMapping("/supprimer")
	public String supprimerCompte(@ModelAttribute("utilisateurConnecte") Utilisateur utilisateurConnecte) {
		utilisateurService.supprimerUtilisateur(utilisateurConnecte.getNoUtilisateur());
		return "redirect:/logout";
	}

}
