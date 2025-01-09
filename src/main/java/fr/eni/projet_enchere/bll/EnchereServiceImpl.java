package fr.eni.projet_enchere.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.EnchereDAO;
import fr.eni.projet_enchere.dal.UtilisateurDAO;

@Service
public class EnchereServiceImpl implements EnchereService {
	private List<Enchere> encheres = new ArrayList<>();
	@Autowired
	private EnchereDAO enchereDAO;
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	@Override
	public List<Enchere> add(Enchere enchere) {
		encheres.add(enchere);
		return encheres;
	}

	@Override
	public void creerEnchere(Utilisateur utilisateur, Article articleAEncherir, int montantEnchere) {
		boolean valide = validerEnchereUnique(utilisateur.getNoUtilisateur(), articleAEncherir.getNoArticle());
		if (etatVente(articleAEncherir).equals("En cours") && montantEnchere >= articleAEncherir.getMiseAPrix()
				&& utilisateur.getCredit() >= montantEnchere
				&& montantEnchere > getMaximumMontantEnchere(articleAEncherir.getNoArticle())) {
			if (utilisateur.getEncheres() == null) {
				utilisateur.setEncheres(new ArrayList<>()); // Initialiser la liste d'enchères si elle est null
			}
			
			Enchere enchere = new Enchere(LocalDateTime.now(), montantEnchere, articleAEncherir, utilisateur);
			utilisateur.getEncheres().add(enchere);
			if (valide) {
				enchereDAO.creerEnchere(enchere, articleAEncherir.getNoArticle(), utilisateur.getNoUtilisateur());
			}else {
				enchereDAO.updateEnchere(enchere, articleAEncherir.getNoArticle(), utilisateur.getNoUtilisateur());
			}
			
		} else {
			// Gérer les cas où l'enchère ne peut pas être créée

		}
	}

	@Override
	public int getMaximumMontantEnchere(long noArticle) {
		List<Enchere> encheres = enchereDAO.findByArticleId(noArticle);
		return encheres.stream().max(Comparator.comparing(Enchere::getMontantEnchere)).map(Enchere::getMontantEnchere)
				.orElse(0); // Retourne 0 si aucune enchère n'est trouvée
	}

	@Override
	public Enchere getDerniereEncherePourArticle(long noArticle) {
		return enchereDAO.findLastEnchereByArticleId(noArticle);
	}

	@Override
	public Enchere getDerniereEncherePourArticle(String nomArticle) {
		return enchereDAO.findLastEnchereByArticleName(nomArticle);
	}

	@Override
	public String etatVente(Article articleAEncherir) {
		if (articleAEncherir.getDateFinEncheres().isBefore(LocalDateTime.now())) {
			return "Terminée";
		}
		if (articleAEncherir.getDateDebutEncheres().isAfter(LocalDateTime.now())) {
			return "À venir";
		}
		return "En cours";
	}

	@Override
	public Utilisateur getUtilisateurParNom(String username) {
		return utilisateurDAO.findByPseudo(username);
	}


	@Override
	public boolean validerEnchereUnique(long noUtilisateur, long noArticle) {
		boolean enchereUtilisateurExiste = enchereDAO.enchereUnique(noUtilisateur, noArticle);
		
		return !enchereUtilisateurExiste;
	}

	@Override
	public Article gagnerEnchere(long noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

}

