package fr.eni.projet_enchere.bll;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.UtilisateurDAO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO utilisateurDAO;
	private ArticleService articleService;

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, ArticleService articleService) {
		this.utilisateurDAO = utilisateurDAO;
		this.articleService = articleService;
	}

	@Override
	public Article mettreEnVente(Article article) {
		articleService.add(article);
		return article;
	}

	@Override
	public Utilisateur read(int id) {
		return utilisateurDAO.read(id);
	}

	@Override
	public Utilisateur consulterUtilisateurParId(long id) {
		return utilisateurDAO.read(id);
	}
}
