package fr.eni.projet_enchere.bll;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;

public interface EnchereService {

	List<Enchere> add(Enchere enchere);
	
	String etatVente(Article article);
	

}
