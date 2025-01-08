package fr.eni.projet_enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String gagnerEnchere(@RequestParam("articleId") long articleId, Model model) {
		Article article = articleService.consulterArticleParId(articleId);
		if (article == null) {
			return "redirect:/error";
		}
		Enchere derniereEnchere = enchereService.getDerniereEncherePourArticle(articleId);
		if (derniereEnchere == null) {
			return "redirect:/error";
		}
		model.addAttribute("article", article);
		model.addAttribute("gagnant", derniereEnchere.getUtilisateur());
		model.addAttribute("montantGagnant", derniereEnchere.getMontantEnchere());
		return "view-detail-vente-gagne";
	}

}
