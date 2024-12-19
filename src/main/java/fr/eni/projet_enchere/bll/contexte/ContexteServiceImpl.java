package fr.eni.projet_enchere.bll.contexte;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.UtilisateurDAO;

@Service
public class ContexteServiceImpl implements ContexteService {
	private UtilisateurDAO utilisateurDAO;

	public ContexteServiceImpl(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public Utilisateur charger(String pseudo) {
		return utilisateurDAO.findByPseudo(pseudo);
	}
}
