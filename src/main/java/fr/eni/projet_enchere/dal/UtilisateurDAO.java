package fr.eni.projet_enchere.dal;

import fr.eni.projet_enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	void creer(Utilisateur utilisateur);

	Utilisateur read(int id);
}
