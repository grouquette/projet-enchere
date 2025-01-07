package fr.eni.projet_enchere.controller;

import java.util.List;

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
import fr.eni.projet_enchere.bll.CategorieService;
import fr.eni.projet_enchere.bll.contexte.ContexteService;
import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Categorie;

@Controller
//@RequestMapping("/article")
@SessionAttributes("articleSession")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategorieService categorieService;
	private final ContexteService contexteService;
	public ArticleController(ArticleService articleService, CategorieService categorieService,
			ContexteService contexteService) {
		this.articleService = articleService;
		this.categorieService = categorieService;
		this.contexteService = contexteService;
	}
	@GetMapping("/article/creer")
	@PreAuthorize("isAuthenticated()")
	public String creerArticleForm(Model model, Authentication authentication) {
		model.addAttribute("article", new Article());
		model.addAttribute("categories", categorieService.findAll());
		return "view-article-creation";
	}

	@PostMapping("/article/creer")
	@PreAuthorize("isAuthenticated()")
	public String creerArticleSubmit(@ModelAttribute Article article, Authentication authentication, @RequestParam("categorie") int categorieId) {
	    article.setCategorie(categorieService.findById(categorieId));
	    articleService.save(article);
	    return "view-detail-vente";
	}


	@GetMapping("/article/details")
	public String afficherUnArticle(@RequestParam("articleId") long id, Model model) {
		Article a = this.articleService.consulterArticleParId(id);

		model.addAttribute("article", a);
		
		return "detail-vente";
	}

//	@GetMapping("/encheres")
//	public String afficherListeDesArticles(@RequestParam(value = "nomArticle", required = false) String nomArticle, Model model) {
//		List<Article> articles = contexteService.getAllArticles();
//	    model.addAttribute("articleSession", articles);
//		return "view-encheres";
//	}

	@GetMapping("/encheres")
	public String afficherListeArticles(
	        @RequestParam(value = "nomArticle", required = false) String nomArticle,
	        @RequestParam(value = "noCategorie", required = false) Long noCategorie,  // Ajout de cette ligne
	        Model model) {
	    List<Article> articles;

	    if (nomArticle != null && !nomArticle.isEmpty()) {
	        articles = contexteService.consulterArticleParNom(nomArticle);
	    } else if (noCategorie != null) {
	        articles = contexteService.consulterArticleParCategorie(noCategorie);  // Ajout de cette méthode
	    } else {
	        articles = contexteService.getAllArticles();
	    }

	    List<Categorie> categories = categorieService.findAll();
	    model.addAttribute("categoriesSession", categories);
	    model.addAttribute("articleSession", articles);
	    model.addAttribute("nomArticle", nomArticle); // Pour pré-remplir le champ de recherche
	    model.addAttribute("noCategorie", noCategorie); // Pour pré-remplir la sélection de la catégorie

	    return "view-encheres";
	}

	@PostMapping("/encheres")
	public String afficherDetailArticle(@RequestParam("nomArticle") String nomArticle, Model model) {
		Article a = this.articleService.consulterArticleParNom(nomArticle);
		model.addAttribute("article", a);
		return "view-detail-vente";
	}
//	@GetMapping("/article/details")
//	public String afficherUnArticle(@RequestParam("articleId") long id, Model model) {
//		Article a = this.articleService.consulterArticleParId(id);
//		model.addAttribute("article", a);
//		return "view-detail-vente";
//	}
	@ModelAttribute("articleSession")
	public List<Article> chargerArticlesEnSession() {
		return this.contexteService.getAllArticles();
	}
	@ModelAttribute("categoriesSession")
	public List<Categorie> chargerCategorieEnSession() {
		return this.articleService.consulterCategorie();
	}
}
