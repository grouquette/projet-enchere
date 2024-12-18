package fr.eni.projet_enchere.dal;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;


public interface ArticleDAO {

	Article read(int id);

	List<Article> findById(int id);
	
}
