package fr.eni.projet_enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet_enchere.bll.ArticleService;
import fr.eni.projet_enchere.bll.EnchereService;
import fr.eni.projet_enchere.bll.UtilisateurService;
import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

@Controller
@SessionAttributes("encheresSession")
public class EnchereController {
	@Autowired
	private EnchereService enchereService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UtilisateurService utilisateurService;

	public EnchereController(EnchereService enchereService, ArticleService articleService) {
		this.enchereService = enchereService;
		this.articleService = articleService;
	}

	@PostMapping("/creerEnchere")
	public String creerEnchere(@ModelAttribute Enchere enchere, @RequestParam("articleId") long articleId,
			Authentication authentication) {
		// Récupérer l'article correspondant à l'ID
		Article article = articleService.consulterArticleParId(articleId);
		enchere.setArticle(article);
		// Récupérer l'utilisateur connecté
		String username = authentication.getName(); // Nom d'utilisateur actuel
		Utilisateur utilisateur = enchereService.getUtilisateurParNom(username);
		enchere.setUtilisateur(utilisateur);
		// Enregistrer l'enchère
		enchereService.add(enchere);
		enchereService.creerEnchere(utilisateur, article, enchere.getMontantEnchere());
		// Redirection vers les détails de l'article
		return "redirect:/article/details?articleId=" + articleId;
	}

	@GetMapping("/gagnerEnchere")
	@PreAuthorize("isAuthenticated()")
	public String gagnerEnchere(@RequestParam("articleId") long articleId, Authentication authentication, Model model) {
	    // Récupérer l'utilisateur authentifié
	    String username = authentication.getName();
	    Utilisateur utilisateur = utilisateurService.getUtilisateurParNom(username);
	    
	    // Gagne l'enchère
	    Article articleGagne = enchereService.gagnerEnchere(articleId);
	    if (articleGagne == null) {
	        return "redirect:/error";
	    }
	    
	    // Ajouter les informations nécessaires au modèle
	    model.addAttribute("article", articleGagne);
	    model.addAttribute("gagnant", utilisateur);
	    model.addAttribute("montantGagnant", enchereService.getDerniereEncherePourArticle(articleId).getMontantEnchere());

	    return "view-detail-vente-gagne?articleId=" + articleId;
	}


}
