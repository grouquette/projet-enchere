package fr.eni.projet_enchere.bll;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface ArticleService {


	void remove(Article article);
	List<Article> add(Article articleAVendre);
	Article consulterArticleParId(long id);
	Article creerArticle(Article article);
	Article consulterArticleParNom(String nomArticle);
	Utilisateur getUtilisateurParNom(String username);
	String etatVente(Article article);
	void updateArticle(Article article);
	
}

