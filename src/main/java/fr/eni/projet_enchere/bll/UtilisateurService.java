package fr.eni.projet_enchere.bll;

import fr.eni.projet_enchere.bo.Article;

import fr.eni.projet_enchere.bo.Enchere;

import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.exception.BusinessException;

public interface UtilisateurService {

	Utilisateur creerUtilisateur(Utilisateur utilisateur) throws BusinessException;

	Utilisateur findByPseudo(String pseudo);

	Article mettreEnVente(Article article);

	Utilisateur read(int id);

	Utilisateur consulterUtilisateurParId(int id);

	Utilisateur consulterProfilUtilisateurParId(long id);

	void modifierUtilisateur(Utilisateur utilisateur);

	void encherir(Utilisateur utilisateur, Article articleAEncherir, int montantEnchere);

}
