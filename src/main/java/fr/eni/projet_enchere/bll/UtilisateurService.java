package fr.eni.projet_enchere.bll;

import fr.eni.projet_enchere.bo.Article;

import fr.eni.projet_enchere.bo.Enchere;


import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.exception.BusinessException;

public interface UtilisateurService {
	
	Article mettreEnVente(Article article);
	
	void creerUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	Utilisateur consulterProfilUtilisateurParId(long id);
	
	void modifierUtilisateur(Utilisateur utilisateur);
		
	void encherir(Utilisateur utilisateur, Article articleAEncherir, int montantEnchere);

}
