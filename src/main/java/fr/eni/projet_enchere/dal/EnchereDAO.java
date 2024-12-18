package fr.eni.projet_enchere.dal;

import fr.eni.projet_enchere.bo.Enchere;

public interface EnchereDAO {

	void creerEnchere(Enchere enchere, int articleId, long utilisateurId);

}
