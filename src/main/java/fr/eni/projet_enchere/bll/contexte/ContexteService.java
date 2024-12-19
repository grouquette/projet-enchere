package fr.eni.projet_enchere.bll.contexte;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Utilisateur;

@Service
public interface ContexteService {
	Utilisateur charger(String pseudo);
}
