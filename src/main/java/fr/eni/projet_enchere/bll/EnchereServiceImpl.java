package fr.eni.projet_enchere.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Enchere;

@Service
public class EnchereServiceImpl implements EnchereService {

	private List<Enchere> encheres = new ArrayList<>();

	@Override
	public List<Enchere> add(Enchere enchere) {
		encheres.add(enchere);
		return encheres;
	}
	
	public String etatVente(ArticleVendu article) {
		return null;
		if (LocalDateTime.now().isBefore(articleAEncherir.getDateFinEncheres())
	}
}
