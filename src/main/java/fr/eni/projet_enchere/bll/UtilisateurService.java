package fr.eni.projet_enchere.bll;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface UtilisateurService {
	
	Enchere encherir(Utilisateur utilisateur, ArticleVendu articleAEncherir);
	ArticleVendu mettreEnVente(ArticleVendu article);
}
