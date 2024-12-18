package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.projet_enchere.bll.ArticleService;
import fr.eni.projet_enchere.bll.EnchereService;
import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;

@Controller
public class EnchereController {
	
	private EnchereService enchereService;
	private ArticleService articleService;

	// Affiche la liste des films
	@GetMapping("/encheres")
	public String afficherListeDesEncheres() {
		return "view-encheres";
	}
	
	@GetMapping("/details")
	public String afficherUnArticle(@RequestParam("articleId") long id, Model model) {
		Article a = this.articleService.consulterArticleParId(id);
		
		model.addAttribute("article", a);
		
		return "detail-vente";
	}

	@PostMapping("/creerEnchere")
	public String creerEnchere(@ModelAttribute Enchere enchere) {
		this.enchereService.add(enchere);
		return "redirect:detail-vente";
	}


}
