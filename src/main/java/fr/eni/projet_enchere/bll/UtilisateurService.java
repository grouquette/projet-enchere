package fr.eni.projet_enchere.bll;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface UtilisateurService {
	
	Article mettreEnVente(Article article);

	Utilisateur read(int id);

	Utilisateur consulterUtilisateurParId(long id);
	
}
