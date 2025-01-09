package fr.eni.projet_enchere.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.ArticleDAO;
import fr.eni.projet_enchere.dal.UtilisateurDAO;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	private List<Article> articles;
	@Autowired
	private ArticleDAO articleDAO;
	@Autowired
	private UtilisateurDAO utilisateurDAO;

	public ArticleServiceImpl(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
		this.articles = new ArrayList<>();
	}

	@Override
	@Transactional
	public Article creerArticle(Article article) {

		articleDAO.creerArticle(article);
		// Mettre à jour l'état de vente
		return article;
	}

	public void remove(Article articleARetirer) {
		articles.remove(articleARetirer);
	}

	@Override
	public List<Article> add(Article articleAVendre) {
		articles.add(articleAVendre);
		return articles;
	}

	public Article consulterArticleParId(long id) {
		Article article = articleDAO.read(id);
		return article;
	}

	@Override
	public Article consulterArticleParNom(String nomArticle) {
		Article article = articleDAO.readByName(nomArticle);
		return article;
	}

	public List<Article> getArticlesParUtilisateur(Utilisateur utilisateur) {
		return articleDAO.findByUtilisateur(utilisateur);
	}

	@Override
	public Utilisateur getUtilisateurParNom(String username) {
		return utilisateurDAO.findByPseudo(username);

	}

	@Override
	public String etatVente(Article articleAEncherir) {
		if (articleAEncherir.getDateFinEncheres().isBefore(LocalDateTime.now())) {
			return "Terminée";
		}
		if (articleAEncherir.getDateDebutEncheres().isAfter(LocalDateTime.now())) {
			return "À venir";
		}
		return "En cours";
	}

	@Override
	@Transactional
	public Article gagnerArticle(long noArticle) {
		// Récupérer la dernière enchère
		Enchere derniereEnchere = enchereDAO.findLastEnchereByArticleId(noArticle);
		if (derniereEnchere == null) {
			return null;
		}
		Article article = derniereEnchere.getArticle();
		// Vérifier si l'enchère est terminée
		if (LocalDateTime.now().isBefore(article.getDateFinEncheres())) {
			throw new IllegalStateException("L'enchère n'est pas encore terminée.");
		}
		// Vérifier si l'article n'est pas déjà marqué comme terminé
		if ("Terminé".equals(article.getEtatVente())) {
			throw new IllegalStateException("Cette vente est déjà terminée.");
		}
//		Utilisateur gagnant = derniereEnchere.getUtilisateur();
		// Mettre à jour l'état de l'article
		article.setEtatVente("Terminé");
		article.setPrixVente(derniereEnchere.getMontantEnchere());
		// Mise à jour de l'article
		updateArticle(article);
		return article;
	}

	@Override
	@Transactional
	public void updateArticle(Article article) {
		// Vérifier si l'article existe
		Article existingArticle = articleDAO.read(article.getNoArticle());
		if (existingArticle == null) {
			throw new IllegalArgumentException("Article non trouvé");
		}
		// Vérifier les dates
		if (article.getDateDebutEncheres() != null && article.getDateFinEncheres() != null
				&& article.getDateDebutEncheres().isAfter(article.getDateFinEncheres())) {
			throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début");
		}
		// Mise à jour des champs
		existingArticle.setNomArticle(article.getNomArticle());
		existingArticle.setDescription(article.getDescription());
		existingArticle.setDateDebutEncheres(article.getDateDebutEncheres());
		existingArticle.setDateFinEncheres(article.getDateFinEncheres());
		existingArticle.setMiseAPrix(article.getMiseAPrix());
		existingArticle.setPrixVente(article.getPrixVente());
		existingArticle.setUtilisateur(article.getUtilisateur());
		existingArticle.setCategorie(article.getCategorie());
		existingArticle.setEtatVente(article.getEtatVente());
		// Sauvegarder les modif
		try {
			articleDAO.update(existingArticle);
		} catch (Exception e) {
			throw new RuntimeException("Erreur lors de la mise à jour de l'article", e);
		}
	}
}
