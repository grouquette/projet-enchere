package fr.eni.projet_enchere.dal;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;


public interface ArticleDAO {

	Article read(long id);

	List<Article> findById(long id);

	void creerArticle(Article article);

	List<Article> findAll();
	
}
