package fr.eni.projet_enchere.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.UtilisateurDAO;
import fr.eni.projet_enchere.exception.BusinessException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	private List<Enchere> encheres = new ArrayList<>();
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ArticleService articleService;

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, ArticleService articleService) {
		this.utilisateurDAO = utilisateurDAO;
		this.articleService = articleService;
	}
	@Override
	public List<Enchere> add(Enchere enchere) {
		encheres.add(enchere);
		return encheres;
	}
	@Override
	@Transactional
	public Utilisateur creerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException();
		boolean valide = validerEmailUnique(utilisateur.getEmail(), be);
		valide &= validerPseudoUnique(utilisateur.getPseudo(), be);
		valide &= validerMotDePasse(utilisateur.getMotDePasse(), utilisateur.getMotDePasseConfirme(), be);
		if (valide) {
			String motDePasseHashe = passwordEncoder.encode(utilisateur.getMotDePasse());
			motDePasseHashe = motDePasseHashe.replace("$2a$", "$2y$");
			utilisateur.setMotDePasse(motDePasseHashe);
			utilisateurDAO.creer(utilisateur);
		} else {
			throw be;
		}
		return utilisateur;
	}

	@Override
	@Transactional
	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException();
		boolean valide = validerMotDePasse(utilisateur.getMotDePasse(), utilisateur.getMotDePasseConfirme(), be);
		if (valide) {
			String motDePasseHashe = passwordEncoder.encode(utilisateur.getMotDePasse());
			motDePasseHashe = motDePasseHashe.replace("$2a$", "$2y$");
			utilisateur.setMotDePasse(motDePasseHashe);
			utilisateurDAO.modifier(utilisateur);
		} else {
			throw be;
		}
	}

	private boolean validerPseudoUnique(String pseudo, BusinessException be) {
		boolean pseudoExiste = this.utilisateurDAO.existPseudo(pseudo);
		if (pseudoExiste) {
			be.addMessage("Le pseudo existe déjà");
		}
		return !pseudoExiste;
	}

	private boolean validerEmailUnique(String email, BusinessException be) {
		boolean emailExiste = this.utilisateurDAO.existEmail(email);
		if (emailExiste) {
			be.addMessage("L'adresse email existe déjà");
		}
		return !emailExiste;
	}

	private boolean validerMotDePasse(String motDePasse, String motDePasseConfirme, BusinessException be) {
		boolean motDePasseConfirm = motDePasse != null && motDePasse.equals(motDePasseConfirme);
		if (!motDePasseConfirm) {
			be.addMessage("Les mots de passe ne sont pas identiques");
		}
		return motDePasseConfirm;
	}

	@Override
	public Utilisateur findByPseudo(String pseudo) {
		return utilisateurDAO.findByPseudo(pseudo);
	}

	@Override
	public Utilisateur consulterProfilUtilisateurParId(long id) {
		Utilisateur u = this.utilisateurDAO.lire(id);
		return u;
	}

	@Override
	public void supprimerUtilisateur(long noUtilisateur) {
		utilisateurDAO.supprimer(noUtilisateur);
	}

	@Override
	public Article mettreEnVente(Article article) {
		articleService.add(article);
		return article;
	}

	@Override
	public Utilisateur getUtilisateurParNom(String username) {
	    return utilisateurDAO.findByPseudo(username);
	}
}
