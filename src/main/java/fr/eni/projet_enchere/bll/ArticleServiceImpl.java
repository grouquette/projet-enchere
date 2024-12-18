package fr.eni.projet_enchere.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.dal.ArticleDAO;

@Service
public class ArticleServiceImpl implements ArticleService {

	private List<Article> articles;
	private ArticleDAO articleDAO;

	public ArticleServiceImpl(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
		this.articles = new ArrayList<>();
	}

	public void remove(Article articleARetirer) {
		articles.remove(articleARetirer);
	}

	@Override
	public List<Article> add(Article articleAVendre) {
		articles.add(articleAVendre);
		return articles;
	}

	public Article consulterArticleParId(long id) {
		Article article = articleDAO.read(id);
		return article;
	}
	
}
