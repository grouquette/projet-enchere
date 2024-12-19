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

	@Autowired
	private EnchereDAO enchereDAO;

	@Override
	public List<Enchere> add(Enchere enchere) {
		encheres.add(enchere);
		return encheres;
	}

	@Override
	public void creerEnchere(Utilisateur utilisateur, Article articleAEncherir, int montantEnchere) {
		if (articleAEncherir.getEtatVente().equals("en cours") && montantEnchere >= articleAEncherir.getMiseAPrix()
				&& utilisateur.getCredit() >= montantEnchere
				&& montantEnchere > getMaximumMontantEnchere(articleAEncherir.getNoArticle())) {
			Enchere enchere = new Enchere(LocalDateTime.now(), montantEnchere);
			utilisateur.getEncheres().add(enchere);

			enchereDAO.creerEnchere(enchere, articleAEncherir.getNoArticle(), utilisateur.getNoUtilisateur());
		}

	}

	@Override
	public int getMaximumMontantEnchere(long noArticle) {
		List<Enchere> encheres = enchereDAO.findByArticleId(noArticle);
		return encheres.stream().max(Comparator.comparing(Enchere::getMontantEnchere)).map(Enchere::getMontantEnchere)
				.orElseThrow(() -> new NoSuchElementException()); // récupère un flux d'Enchere, récupère la plus haute
	}

	@Override
	public String etatVente(Article articleAEncherir) {
		if (articleAEncherir.getDateFinEncheres().isBefore(LocalDateTime.now())) {
			return "Terminée";
		}
		return "En cours";
	}
}
