package fr.eni.projet_enchere.bll;

import java.util.List;

import fr.eni.projet_enchere.bo.ArticleVendu;

public interface ArticleService {

	void remove(ArticleVendu articleVendu);
	List<ArticleVendu> add(ArticleVendu articleAVendre);
	
}
