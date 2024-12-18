package fr.eni.projet_enchere.bll;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.UtilisateurDAO;
import fr.eni.projet_enchere.exception.BusinessException;

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
	public Utilisateur consulterUtilisateurParId(int id) {
		return utilisateurDAO.read(id);
	}

	public Enchere saveEnchere() {
		return null;
		//TODO
	}

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.creer(utilisateur);
		
	}

	
	@Override
	public void encherir(Utilisateur utilisateur, Article articleAEncherir, int montantEnchere) {
		// TODO Auto-generated method stub
	}

	@Override
	public Utilisateur consulterProfilUtilisateurParId(long id) {
	Utilisateur u =this.consulterProfilUtilisateurParId(id);
	return u;
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.modifier(utilisateur);
		
	}
	
}
