package fr.eni.projet_enchere.bll;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface ArticleService {

	Article creerArticle(Article article);

	void remove(Article article);

	List<Article> add(Article articleAVendre);

	Article consulterArticleParId(long id);

	Article consulterArticleParNom(String nomArticle);

	List<Article> getArticlesParUtilisateur(Utilisateur utilisateur);
	
	Utilisateur getUtilisateurParNom(String username);

	String etatVente(Article article);

	void updateArticle(Article article);

	Article gagnerArticle(long noArticle);

	List<Article> findByEnchere(long noUtilisateur);
}
