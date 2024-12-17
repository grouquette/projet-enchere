package fr.eni.projet_enchere.bll;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface UtilisateurService {
	
<<<<<<< HEAD
=======
	void creerUtilisateur(Utilisateur utilisateur);
	
	Enchere encherir(Utilisateur utilisateur, ArticleVendu articleAEncherir);
>>>>>>> 0eaf2b7a526aab5d79e1cfec8f62a4d59e5d3e52
	ArticleVendu mettreEnVente(ArticleVendu article);
	void encherir(Utilisateur utilisateur, ArticleVendu articleAEncherir, int montantEnchere);
}
