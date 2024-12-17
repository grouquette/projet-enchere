package fr.eni.projet_enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

@Service
public class EnchereServiceImpl implements EnchereService {


	@Override
	public List<Enchere> add(Utilisateur utilisateur, ArticleVendu articleAEncherir) {
		return null;
	}

}
