package fr.eni.projet_enchere.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet_enchere.bll.ArticleService;
import fr.eni.projet_enchere.bll.CategorieService;
import fr.eni.projet_enchere.bll.EnchereService;
import fr.eni.projet_enchere.bll.contexte.ContexteService;
import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Categorie;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Retrait;
import fr.eni.projet_enchere.bo.Utilisateur;
import jakarta.validation.Valid;

@Controller
//@RequestMapping("/article")
@SessionAttributes("articleSession")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategorieService categorieService;
	@Autowired
	private ContexteService contexteService;
	@Autowired
	private EnchereService enchereService;

	public ArticleController(ArticleService articleService, CategorieService categorieService,
			ContexteService contexteService) {
		this.articleService = articleService;
		this.categorieService = categorieService;
		this.contexteService = contexteService;
	}

	@GetMapping("/article/creer")
	@PreAuthorize("isAuthenticated()")
	public String creerArticleForm(Model model, Authentication authentication) {
		Article article = new Article();
		String username = authentication.getName();
		Utilisateur utilisateur = articleService.getUtilisateurParNom(username);
		Retrait retrait  = new Retrait();
		retrait.setRue(utilisateur.getRue());
		retrait.setCode_postal(utilisateur.getCodePostal());
		retrait.setVille(utilisateur.getVille());
		article.setLieuRetrait(retrait); // Initialisation de lieuRetrait
		model.addAttribute("article", article);
		model.addAttribute("categories", categorieService.findAll());
		return "view-article-creation";
	}

	@PostMapping("/article/creer")
	@PreAuthorize("isAuthenticated()")
	public String creerArticleSubmit(@Valid @ModelAttribute("article") Article article, BindingResult bindingResult,
			Authentication authentication, @RequestParam("categorie") int noCategorie, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categorieService.findAll());
			return "view-article-creation";
		} else {
			String username = authentication.getName(); // Nom d'utilisateur actuel
			Utilisateur utilisateur = articleService.getUtilisateurParNom(username);
			article.setUtilisateur(utilisateur);
			articleService.creerArticle(article);
			return "redirect:/encheres";
		}
	}

	@GetMapping("/encheres")
	public String afficherListeArticles(@RequestParam(value = "nomArticle", required = false) String nomArticle,
			@RequestParam(value = "noCategorie", required = false) Long noCategorie,
			@RequestParam(value = "mesVentes", required = false) Boolean mesVentes,
			Authentication authentication, // Authentication
			Model model) {
		// Initialisation de la variable articles pour éviter l'erreur de compilation
		List<Article> articles = new ArrayList<>(); // Liste vide par défaut
		// Vérification si l'utilisateur est authentifié
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Utilisateur utilisateur = enchereService.getUtilisateurParNom(username);
			// Recherche combinée par nom et catégorie
			if (nomArticle != null && !nomArticle.isEmpty() && noCategorie != null) {
				articles = contexteService.consulterArticleParNomEtCategorie(nomArticle, noCategorie);
				// Recherche par nom uniquement
			} else if (nomArticle != null && !nomArticle.isEmpty()) {
				articles = contexteService.consulterArticleParNom(nomArticle);
				// Recherche par catégorie uniquement
			} else if (noCategorie != null) {
				articles = contexteService.consulterArticleParCategorie(noCategorie);
				// Recherche mes ventes uniquement
			} else if (mesVentes != null && mesVentes) {
		//		articles = articleService.getArticlesParUtilisateur(utilisateur); // Récupérer les articles de
																					// l'utilisateur connecté
			} else {
				articles = contexteService.getAllArticles();
			}
		} else {
			// Si l'utilisateur n'est pas authentifié, on peut afficher les articles sans
			// filtrage par utilisateur
			if (nomArticle != null && !nomArticle.isEmpty() && noCategorie != null) {
				articles = contexteService.consulterArticleParNomEtCategorie(nomArticle, noCategorie);
			} else if (nomArticle != null && !nomArticle.isEmpty()) {
				articles = contexteService.consulterArticleParNom(nomArticle);
			} else if (noCategorie != null) {
				articles = contexteService.consulterArticleParCategorie(noCategorie);
			} else {
				articles = contexteService.getAllArticles();
			}
		}
		// Inverser la liste des articles
		Collections.reverse(articles);
		// Récupérer la liste des catégories
		List<Categorie> categories = categorieService.findAll();
		// Ajouter les attributs au modèle
		model.addAttribute("categoriesSession", categories);
		model.addAttribute("articleSession", articles);
		model.addAttribute("nomArticle", nomArticle);
		model.addAttribute("noCategorie", noCategorie);
		model.addAttribute("mesVentes", mesVentes); // Garder la case "Mes Ventes" cochée
		return "view-encheres";
	}

	@PostMapping("/encheres")
	public String afficherDetailArticle(@RequestParam("nomArticle") String nomArticle, Model model) {
		// On récupère toutes les données de l'article et de son vendeur
		Article a = this.articleService.consulterArticleParNom(nomArticle);
		// On récupère toutes les données de la dernière enchère et de l'encherisseur
 		Enchere derniereEnchere = enchereService.getDerniereEncherePourArticle(nomArticle); // Charge la dernière //
																							// enchère
		model.addAttribute("article", a);
		model.addAttribute("derniereEnchere", derniereEnchere);
		model.addAttribute("enchere", new Enchere(null, 0, a, null)); // Ajouter un objet enchère vide
		return "view-detail-vente";
	}

	@GetMapping("/article/details")
	public String afficherUnArticle(@RequestParam("articleId") long id, Model model) {
	    Article a = this.articleService.consulterArticleParId(id);
	    if (a == null) {
	        return "redirect:/error"; // S'assurer que l'article est trouvé
	    }
	    model.addAttribute("article", a);
	    
	    Enchere derniereEnchere = enchereService.getDerniereEncherePourArticle(id);
	    if (derniereEnchere != null) {
	        model.addAttribute("derniereEnchere", derniereEnchere);
	    } else {
	        model.addAttribute("message", "Aucune enchère pour cet article.");
	    }
	    model.addAttribute("enchere", new Enchere(null, 0, a, null));
	    
	    return "view-detail-vente";
	}

	@ModelAttribute("articleSession")
	public List<Article> chargerArticlesEnSession() {
		return this.contexteService.getAllArticles();
	}

	@ModelAttribute("categoriesSession")
	public List<Categorie> chargerCategorieEnSession() {
		return this.categorieService.consulterCategorie();
	}
}
