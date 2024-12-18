package fr.eni.projet_enchere.bll;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface UtilisateurService {
	
	void creerUtilisateur(Utilisateur utilisateur);
	
	ArticleVendu mettreEnVente(ArticleVendu article);
	void encherir(Utilisateur utilisateur, ArticleVendu articleAEncherir, int montantEnchere);
}
