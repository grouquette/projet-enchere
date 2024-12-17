package fr.eni.projet_enchere.bll;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface UtilisateurService {

	Utilisateur creerUtilisateur(long noUtilisateur, String pseudo, String nom, String prenom, String email,
			int telephone, String rue, short codePostal, String ville, String motDePasse, int credit,
			boolean administrateur);
	
	Utilisateur acheterArticle(ArticleVendu articleAcheter);

	ArticleVendu mettreEnVente();
}
