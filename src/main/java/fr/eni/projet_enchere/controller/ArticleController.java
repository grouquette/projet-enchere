package fr.eni.projet_enchere.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
		article.setLieuRetrait(new Retrait()); // Initialisation de lieuRetrait
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
	        @RequestParam(value = "mesVentesEnCours", required = false) Boolean mesVentesEnCours,
	        @RequestParam(value = "ventesNonDebutees", required = false) Boolean ventesNonDebutees,
	        @RequestParam(value = "ventesTerminees", required = false) Boolean ventesTerminees,
	        @RequestParam(value = "mesEncheresEnCours", required = false) Boolean mesEncheresEnCours,
	        Authentication authentication, Model model) {
	    List<Article> articles = new ArrayList<>(); // Liste vide par défaut
	    // Vérification si l'utilisateur est authentifié
	    if (authentication != null && authentication.isAuthenticated()) {
	        String username = authentication.getName();
	        Utilisateur utilisateur = enchereService.getUtilisateurParNom(username);
	        if (utilisateur != null) {
	            if (mesEncheresEnCours != null && mesEncheresEnCours) {
	                // Appel de la méthode dans le DAO pour récupérer les articles associés aux enchères de l'utilisateur
	                articles = articleService.findByEnchere(utilisateur.getNoUtilisateur());
	            }
	            // Condition pour les ventes de l'utilisateur
	            else if (mesVentes != null && mesVentes) {
	                // Récupérer les articles associés à l'utilisateur
	                articles = Optional.ofNullable(articleService.getArticlesParUtilisateur(utilisateur))
	                        .orElse(new ArrayList<>());

	                // Filtrer les articles en fonction des états de vente demandés
	                if (mesVentesEnCours != null && mesVentesEnCours) {
	                    // Filtrer les articles en cours
	                    articles.removeIf(article -> !enchereService.etatVente(article).equals("En cours"));
	                } 
	                else if (ventesNonDebutees != null && ventesNonDebutees) {
	                    // Filtrer les articles dont la vente n'a pas encore commencé
	                    articles.removeIf(article -> !enchereService.etatVente(article).equals("À venir"));
	                } 
	                else if (ventesTerminees != null && ventesTerminees) {
	                    // Filtrer les articles dont la vente est terminée
	                    articles.removeIf(article -> !enchereService.etatVente(article).equals("Terminée"));
	                } 
	                else {
	                    
	                }
	            }
	            // Recherche par nom et catégorie
	            else if (nomArticle != null && !nomArticle.isEmpty() && noCategorie != null) {
	                articles = Optional
	                        .ofNullable(contexteService.consulterArticleParNomEtCategorie(nomArticle, noCategorie))
	                        .orElse(new ArrayList<>());
	            }
	            // Recherche par nom uniquement
	            else if (nomArticle != null && !nomArticle.isEmpty()) {
	                articles = Optional.ofNullable(contexteService.consulterArticleParNom(nomArticle))
	                        .orElse(new ArrayList<>());
	            }
	            // Recherche par catégorie uniquement
	            else if (noCategorie != null) {
	                articles = Optional.ofNullable(contexteService.consulterArticleParCategorie(noCategorie))
	                        .orElse(new ArrayList<>());
	            }
	            // Tous les articles
	            else {
	                articles = Optional.ofNullable(contexteService.getAllArticles()).orElse(new ArrayList<>());
	            }
	        }
	    } else {
	        // Si l'utilisateur n'est pas authentifié
	        if (nomArticle != null && !nomArticle.isEmpty() && noCategorie != null) {
	            articles = Optional
	                    .ofNullable(contexteService.consulterArticleParNomEtCategorie(nomArticle, noCategorie))
	                    .orElse(new ArrayList<>());
	        } else if (nomArticle != null && !nomArticle.isEmpty()) {
	            articles = Optional.ofNullable(contexteService.consulterArticleParNom(nomArticle))
	                    .orElse(new ArrayList<>());
	        } else if (noCategorie != null) {
	            articles = Optional.ofNullable(contexteService.consulterArticleParCategorie(noCategorie))
	                    .orElse(new ArrayList<>());
	        } else {
	            articles = Optional.ofNullable(contexteService.getAllArticles()).orElse(new ArrayList<>());
	        }
	    }
	    // Inverser la liste des articles
	    Collections.reverse(articles);
	    // Ajouter les catégories et les articles au modèle
	    List<Categorie> categories = categorieService.findAll();
	    model.addAttribute("categoriesSession", categories);
	    model.addAttribute("articleSession", articles);
	    model.addAttribute("nomArticle", nomArticle);
	    model.addAttribute("noCategorie", noCategorie);
	    model.addAttribute("mesVentes", mesVentes);
	    model.addAttribute("mesEncheresEnCours", mesEncheresEnCours);
	    return "view-encheres";
	}


	@PostMapping("/encheres")
	public String afficherDetailArticle(@RequestParam("nomArticle") String nomArticle, Model model) {
		Article a = this.articleService.consulterArticleParNom(nomArticle);
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
		model.addAttribute("article", a);
		model.addAttribute("enchere", new Enchere(null, 0, a, null)); // Ajouter un objet enchère vide
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
