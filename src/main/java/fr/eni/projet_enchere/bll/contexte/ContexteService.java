package fr.eni.projet_enchere.bll.contexte;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Utilisateur;

@Service
public interface ContexteService {
	Utilisateur charger(String pseudo);

//	List<Enchere> getAllEncheres();

	List<Article> getAllArticles();

	List<Article> consulterArticleParNom(String nomArticle);

	List<Article> consulterArticleParCategorie(Long noCategorie);

	List<Article> consulterArticleParNomEtCategorie(String nomArticle, Long noCategorie);
}
