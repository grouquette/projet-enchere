package fr.eni.projet_enchere.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;

@Service
public class ArticleServiceImpl implements ArticleService {

	private List<Article> articles = new ArrayList<>();

	public ArticleServiceImpl(List<Article> articles) {
		this.articles = articles;
	}

	public void remove(Article articleARetirer) {
		articles.remove(articleARetirer);
	}

	@Override
	public List<Article> add(Article articleAVendre) {
		articles.add(articleAVendre); 
		return articles;
	}
}
