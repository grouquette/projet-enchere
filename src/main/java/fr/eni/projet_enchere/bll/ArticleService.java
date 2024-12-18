package fr.eni.projet_enchere.bll;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;

public interface ArticleService {

	void remove(Article article);
	List<Article> add(Article articleAVendre);
	Article consulterArticleParId(int id);
	
}
