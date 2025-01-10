package fr.eni.projet_enchere.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

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
	public String afficherListeArticles(
			@RequestParam(value = "nomArticle", required = false) String nomArticle,
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

		articles.forEach(article -> {
					article.setCurrentMaximumEnchere(enchereService.getMaximumMontantEnchere(article.getNoArticle()));
					article.setEnchereIsClosed(LocalDateTime.now().isAfter(article.getDateFinEncheres()));
				}
		);

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
	@PreAuthorize("isAuthenticated()")
	public String afficherDetailArticle(Principal principal, @RequestParam("nomArticle") String nomArticle, Model model) {
		// On récupère toutes les données de l'article et de son vendeur
		// Recupere le username du spring contexte (== pseudo dans bdd)
		String currentUsername = principal.getName();
		Article article = this.articleService.consulterArticleParNom(nomArticle);
		article.setEnchereIsClosed(LocalDateTime.now().isAfter(article.getDateFinEncheres()));
		// On récupère toutes les données de la dernière enchère et de l'encherisseur
 		Enchere derniereEnchere = enchereService.getDerniereEncherePourArticle(nomArticle); // Charge la dernière enchere
		if(article.getEnchereIsClosed() && derniereEnchere != null){
			article.setCurrentUserIsWinner(Objects.equals(derniereEnchere.getUtilisateur().getPseudo(), currentUsername));
		}
		model.addAttribute("article", article);
		model.addAttribute("derniereEnchere", derniereEnchere);
		model.addAttribute("enchere", new Enchere(null, 0, article, null)); // Ajouter un objet enchère vide
		return "view-detail-vente";
	}

	@GetMapping("/article/details")
	public String afficherUnArticle(Principal principal, @RequestParam("articleId") long id, Model model) {
	    Article article = this.articleService.consulterArticleParId(id);
		if (article == null) {
			return "redirect:/error"; // S'assurer que l'article est trouvé
		}

		String currentUserPseudo = principal.getName();
		Enchere derniereEnchere = enchereService.getDerniereEncherePourArticle(id);
		article.setEnchereIsClosed(LocalDateTime.now().isAfter(article.getDateFinEncheres()));

		if(article.getEnchereIsClosed() && derniereEnchere != null){
			Boolean isWinner = Optional.ofNullable(derniereEnchere.getUtilisateur())
					.map(Utilisateur::getPseudo)
					.map(pseudo -> pseudo.equals(currentUserPseudo))
					.orElse(false);
			article.setCurrentUserIsWinner(isWinner);
		}

	    model.addAttribute("article", article);

	    if (derniereEnchere != null) {
	        model.addAttribute("derniereEnchere", derniereEnchere);
	    } else {
	        model.addAttribute("message", "Aucune enchère pour cet article.");
	    }
	    model.addAttribute("enchere", new Enchere(null, 0, article, null));
	    
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
