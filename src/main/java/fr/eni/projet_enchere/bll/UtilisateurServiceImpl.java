package fr.eni.projet_enchere.bll;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import fr.eni.projet_enchere.bo.ArticleVendu;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

public class UtilisateurServiceImpl implements UtilisateurService {

	private ArticleService articleService;
	private EnchereServiceImpl enchereService;

	@Override
	public ArticleVendu mettreEnVente(ArticleVendu article) {
		articleService.add(article);
		return article;
	}
// Methode pour enchérir sur un ArticleVendu

	@Override
	public void encherir(Utilisateur utilisateur, ArticleVendu articleAEncherir, int montantEnchere) {
		if (articleAEncherir.getEtatVente().equals("en cours") && montantEnchere >= articleAEncherir.getMiseAPrix()
				&& utilisateur.getCredit() >= montantEnchere
				&& montantEnchere > getMaximumMontantEnchere(articleAEncherir.getNoArticle())) {
			Enchere enchere = new Enchere(LocalDateTime.now(), montantEnchere);
			utilisateur.getEncheres().add(enchere);
		}
		// TODO persister dans un objet
	}

	public int getMaximumMontantEnchere(int articleId) {
		List<Enchere> encheres = List.of(); // a la place du list of, récuperer la liste d'enchere pour un article de
											// depuis la DAO

		return encheres.stream().max(Comparator.comparing(Enchere::getMontantEnchere)).map(Enchere::getMontantEnchere)
				.orElseThrow(() -> new NoSuchElementException());
	}
	
	public Enchere saveEnchere() {
		//TODO
	}
}
