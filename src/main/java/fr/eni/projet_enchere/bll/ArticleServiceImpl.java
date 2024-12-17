package fr.eni.projet_enchere.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.ArticleVendu;

@Service
public class ArticleServiceImpl implements ArticleService {

	private List<ArticleVendu> articles = new ArrayList<>();

	public ArticleServiceImpl(List<ArticleVendu> articles) {
		this.articles = articles;
	}

	public void remove(ArticleVendu articleARetirer) {
		articles.remove(articleARetirer);
	}

	@Override
	public List<ArticleVendu> add(ArticleVendu articleAVendre) {
		articles.add(articleAVendre); 
		return articles;
	}
}
