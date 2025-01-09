package fr.eni.projet_enchere.bll;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface ArticleService {

	void remove(Article article);
<<<<<<< HEAD
	List<Article> add(Article articleAVendre);
	Article consulterArticleParId(long id);
	Article creerArticle(Article article);
	Article consulterArticleParNom(String nomArticle);
	Utilisateur getUtilisateurParNom(String username);
	String etatVente(Article article);
	void updateArticle(Article article);
	
}
=======
>>>>>>> 142a9e3ef693f0e48fd82ceac0988add2697a5f7

	List<Article> add(Article articleAVendre);

	Article consulterArticleParId(long id);

	Article creerArticle(Article article);

	Article consulterArticleParNom(String nomArticle);

	Utilisateur getUtilisateurParNom(String username);

	String etatVente(Article article);

	void updateArticle(Article article);

	List<Article> getArticlesParUtilisateur(Utilisateur utilisateur);

	Article gagnerArticle(long noArticle);
}
