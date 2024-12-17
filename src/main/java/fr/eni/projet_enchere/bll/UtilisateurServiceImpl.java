package fr.eni.projet_enchere.bll;

import java.time.LocalDateTime;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

public class UtilisateurServiceImpl implements UtilisateurService {

	private ArticleService articleService;

	@Override
	public ArticleVendu mettreEnVente(ArticleVendu article) {
		articleService.add(article);
		return article;
	}

	@Override
	public Enchere encherir(Utilisateur utilisateur, ArticleVendu articleAEncherir) {
		if (LocalDateTime.now().isBefore(articleAEncherir.getDateFinEncheres())
				&& articleAEncherir.getEtatVente().equals("en cours")
				&& utilisateur.getCredit() >= articleAEncherir.getPrixVente()
				&& articleAEncherir.getMiseAPrix() <= utilisateur.getCredit()) {
			Enchere enchere = new Enchere(LocalDateTime.now(), articleAEncherir.getPrixVente());
			utilisateur.setCredit(utilisateur.getCredit() - articleAEncherir.getPrixVente()); 
			return enchere;
		}
		// TODO enregister l'enchere dans la base de donnée et l'associer à l'article concerné
		return null;

	}
}
