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
	public Article creerArticle(Article article){
		
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
	public void updateArticle(Article article) {
	    Article existingArticle = articleDAO.read(article.getNoArticle());
	    
	    existingArticle.setNomArticle(article.getNomArticle());
	    existingArticle.setDescription(article.getDescription());
	    existingArticle.setDateDebutEncheres(article.getDateDebutEncheres());
	    existingArticle.setDateFinEncheres(article.getDateFinEncheres());
	    existingArticle.setMiseAPrix(article.getMiseAPrix());
	    existingArticle.setPrixVente(article.getPrixVente());
	    existingArticle.setUtilisateur(article.getUtilisateur());
	    existingArticle.setCategorie(article.getCategorie());
	    
	    articleDAO.update(existingArticle);
	}



}
