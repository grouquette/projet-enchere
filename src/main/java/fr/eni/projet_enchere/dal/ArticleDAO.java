package fr.eni.projet_enchere.dal;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;


public interface ArticleDAO {

	void creerArticle(Article article);

	Article read(long id);

	List<Article> findAll();

	Article readByName(String nomArticle);

	List<Article> findByName(String nomArticle);

	List<Article> findByCategory(Long noCategorie);

	void update(Article existingArticle);

	
	
}
