package fr.eni.projet_enchere.bll;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Utilisateur;

public class UtilisateurServiceImpl implements UtilisateurService {
	

	@Override
	public Utilisateur creerUtilisateur(long noUtilisateur, String pseudo, String nom, String prenom, String email,
			int telephone, String rue, short codePostal, String ville, String motDePasse, int credit,
			boolean administrateur) {
		Utilisateur utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal,
				ville, motDePasse, credit, administrateur);
		return utilisateur;
	}

	@Override
	public ArticleVendu mettreEnVente() {
		
		return null;
	}

	@Override
	public Utilisateur acheterArticle(ArticleVendu articleAcheter) {
		// TODO Auto-generated method stub
		return null;
	}

}
