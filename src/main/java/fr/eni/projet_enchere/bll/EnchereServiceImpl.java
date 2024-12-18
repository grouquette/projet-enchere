package fr.eni.projet_enchere.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.EnchereDAO;

@Service
public class EnchereServiceImpl implements EnchereService {

	private List<Enchere> encheres = new ArrayList<>();
	
	@Autowired private EnchereDAO enchereDAO;

	@Override
	public List<Enchere> add(Enchere enchere) {
		encheres.add(enchere);
		return encheres;
	}
	
	// Methode pour enchérir sur un ArticleVendu

		@Override
		public void creerEnchere(Utilisateur utilisateur, Article articleAEncherir, int montantEnchere) {
			if (articleAEncherir.getEtatVente().equals("en cours") && montantEnchere >= articleAEncherir.getMiseAPrix()
					&& utilisateur.getCredit() >= montantEnchere
					&& montantEnchere > getMaximumMontantEnchere(articleAEncherir.getArticleId())) {
				Enchere enchere = new Enchere(LocalDateTime.now(), montantEnchere);
				utilisateur.getEncheres().add(enchere);
				enchereDAO.creerEnchere(enchere, articleAEncherir.getArticleId(), utilisateur.getUtilisateurId());
			}
			
		}

		public int getMaximumMontantEnchere(int articleId) {
			List<Enchere> encheres = List.of(); // a la place du list of, récuperer la liste d'enchere pour un article de
												// depuis la DAO

			return encheres.stream().max(Comparator.comparing(Enchere::getMontantEnchere)).map(Enchere::getMontantEnchere)
					.orElseThrow(() -> new NoSuchElementException()); //récupère un flux d'Enchere, récupère la plus haute 
		}
		
		public Enchere saveEnchere() {
			return null;
			//TODO
		}
	
	public String etatVente(Article articleAEncherir) {
		//TODO définir les règles etats de vente 	
		return null;

	}
}
