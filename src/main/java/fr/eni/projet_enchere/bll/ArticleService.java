package fr.eni.projet_enchere.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Categorie;
import fr.eni.projet_enchere.bo.Retrait;

public interface ArticleService {


	void remove(Article article);
	List<Article> add(Article articleAVendre);
	Article consulterArticleParId(long id);
	Article creerArticle(String nomArticle, String description, Categorie categorie, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, Retrait lieuRetrait);
	Article consulterArticleParNom(String nomArticle);
	List<Categorie> consulterCategorie();
}
