package fr.eni.projet_enchere.bll.contexte;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.ArticleDAO;
import fr.eni.projet_enchere.dal.EnchereDAO;
import fr.eni.projet_enchere.dal.UtilisateurDAO;

@Service
public class ContexteServiceImpl implements ContexteService {
	private UtilisateurDAO utilisateurDAO;
//	private EnchereDAO enchereDAO;
	private ArticleDAO articleDAO;

	public ContexteServiceImpl(UtilisateurDAO utilisateurDAO, EnchereDAO enchereDAO, ArticleDAO articleDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
//		this.enchereDAO = enchereDAO;
		this.articleDAO = articleDAO;
	}

	@Override
	public Utilisateur charger(String pseudo) {
		return utilisateurDAO.findByPseudo(pseudo);
	}

//	@Override
//	public List<Enchere> getAllEncheres() {
//		return enchereDAO.findAll();
//	}

	@Override
	public List<Article> getAllArticles() {
		return articleDAO.findAll();
	}
}
