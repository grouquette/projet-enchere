package fr.eni.projet_enchere.dal;

import java.util.List;

import fr.eni.projet_enchere.bo.Enchere;

public interface EnchereDAO {

	void creerEnchere(Enchere enchere, long noArticle, long utilisateurId);

	List<Enchere> findAll();

	List<Enchere> findByArticleId(long articleId);

	Enchere findLastEnchereByArticleId(long noArticle);

	Enchere findLastEnchereByArticleName(String nomArticle);
	
	void updateEnchere(Enchere enchere, long noArticle, long utilisateurId);
	
	boolean enchereUnique(long noUtilisateur, long noArticle);
}
