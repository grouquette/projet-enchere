package fr.eni.projet_enchere.bll;

import java.util.List;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

public interface EnchereService {

	List<Enchere> add(Enchere enchere);
	
	String etatVente(Article article);
	
	void creerEnchere(Utilisateur utilisateur, Article articleAEncherir, int montantEnchere);

	int getMaximumMontantEnchere(long noArticle);
	
	Enchere getDerniereEncherePourArticle(long noArticle);
	
	Enchere getDerniereEncherePourArticle(String nomArticle);

	Utilisateur getUtilisateurParNom(String username);
	
	boolean validerEnchereUnique(long noUtilisateur, long noArticle);

	Article gagnerEnchere(long noArticle);



}
