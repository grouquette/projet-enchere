package fr.eni.projet_enchere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet_enchere.bll.ArticleService;
import fr.eni.projet_enchere.bll.CategorieService;
import fr.eni.projet_enchere.bll.contexte.ContexteService;
import fr.eni.projet_enchere.bo.Article;

@Controller
//@RequestMapping("/article")
@SessionAttributes("articleSession")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	private CategorieService categorieService;
	private final ContexteService contexteService;

	public ArticleController(ArticleService articleService, CategorieService categorieService,
			ContexteService contexteService) {

		this.articleService = articleService;
		this.categorieService = categorieService;
		this.contexteService = contexteService;
	}

	@GetMapping("/article/creer")
	public String creerArticleForm(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("categories", categorieService.findAll());
		return "view-article-creation";
	}

	@PostMapping("/article/creer")
	public String creerArticleSubmit(@ModelAttribute Article article) {
		articleService.add(article);
		return "view-detail-vente";
	}

	@GetMapping("/article/details")
	public String afficherUnArticle(@RequestParam("articleId") long id, Model model) {
		Article a = this.articleService.consulterArticleParId(id);

		model.addAttribute("article", a);

		return "detail-vente";
	}

	@GetMapping("/encheres")
	public String afficherListeDesArticles(Model model) {
		List<Article> articles = contexteService.getAllArticles();
	    model.addAttribute("articleSession", articles);
		return "view-encheres";
	}

	@ModelAttribute("articleSession")
	public List<Article> chargerArticlesEnSession() {
		return this.contexteService.getAllArticles();
	}
}
