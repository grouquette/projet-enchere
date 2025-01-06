package fr.eni.projet_enchere.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Categorie;
import fr.eni.projet_enchere.bo.Retrait;
import fr.eni.projet_enchere.dal.ArticleDAO;
import fr.eni.projet_enchere.dal.CategorieDAO;

@Service
public class ArticleServiceImpl implements ArticleService {

	private List<Article> articles;
	private ArticleDAO articleDAO;
	private CategorieDAO categorieDAO;

	public ArticleServiceImpl(ArticleDAO articleDAO, CategorieDAO categorieDAO) {
		this.articleDAO = articleDAO;
		this.categorieDAO = categorieDAO;
		this.articles = new ArrayList<>();
	}

	@Override
	public Article creerArticle(String nomArticle, String description, Categorie categorie,
			LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, int miseAPrix, Retrait lieuRetrait) {
		Article article = new Article(nomArticle, description, categorie, dateDebutEncheres, dateFinEncheres, miseAPrix,
				lieuRetrait);
		articleDAO.creerArticle(article);
		return article;
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

	@Override
	public Article consulterArticleParNom(String nomArticle) {
		Article article = articleDAO.readByName(nomArticle);
		return article;
	}

	@Override
	public List<Categorie> consulterCategorie() {
		return categorieDAO.findAll();
	}
}
