package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.projet_enchere.bll.ArticleService;
import fr.eni.projet_enchere.bll.CategorieService;
import fr.eni.projet_enchere.bo.Article;

@Controller
@RequestMapping("/article")
public class ArticleController {

	private ArticleService articleService;
	private CategorieService categorieService;

	@GetMapping("/creer")
	public String creerArticleForm(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("categories", categorieService.findAll());
		return "view-article-creation";
	}

	@PostMapping("/creer")
	public String creerArticleSubmit(@ModelAttribute Article article) {
		articleService.add(article);
		return "redirect: view-encheres";
	}

	@GetMapping("/details")
	public String afficherUnArticle(@RequestParam("articleId") long id, Model model) {
		Article a = this.articleService.consulterArticleParId(id);

		model.addAttribute("article", a);

		return "detail-vente";
	}
}
