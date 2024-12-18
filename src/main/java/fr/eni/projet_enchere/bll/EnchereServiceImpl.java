package fr.eni.projet_enchere.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;

@Service
public class EnchereServiceImpl implements EnchereService {

	private List<Enchere> encheres = new ArrayList<>();

	@Override
	public List<Enchere> add(Enchere enchere) {
		encheres.add(enchere);
		return encheres;
	}
	
	public String etatVente(Article articleAEncherir) {
		if (LocalDateTime.now().isBefore(articleAEncherir.getDateFinEncheres())) {
		//TODO définir les règles etats de vente 	
		}
		return null;

	}
}
