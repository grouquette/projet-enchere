package fr.eni.projet_enchere.bll;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.UtilisateurDAO;
import fr.eni.projet_enchere.exception.BusinessException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurDAO utilisateurDAO;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	private ArticleService articleService;

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, ArticleService articleService) {
		this.utilisateurDAO = utilisateurDAO;
		this.articleService = articleService;
	}
	
	@Override
	public Utilisateur findByPseudo(String pseudo) {
		return utilisateurDAO.findByPseudo(pseudo);
	}
	@Override
	public Article mettreEnVente(Article article) {
		articleService.add(article);
		return article;
	}

	@Override
	public Utilisateur consulterUtilisateurParId(int id) {
		return utilisateurDAO.lire(id);
	}
	
	public Enchere saveEnchere() {
		return null;
		//TODO
	}

	@Override
	@Transactional
	public Utilisateur creerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException be = new BusinessException();
		boolean valide = validerEmailUnique(utilisateur.getEmail(), be);
		valide &= validerPseudoUnique(utilisateur.getPseudo(), be);
		valide &= validerMotDePasse(utilisateur.getMotDePasse(), utilisateur.getMotDePasseConfirme(), be);
		if (valide) {
//			String motDePasseHashe = "{bcrypt}" + passwordEncoder.encode(utilisateur.getMotDePasse());
//		    utilisateur.setMotDePasse(motDePasseHashe);
			utilisateurDAO.creer(utilisateur);
		}else {
			throw be;
		}
		return utilisateur;
	}
	
	@Override
	public void encherir(Utilisateur utilisateur, Article articleAEncherir, int montantEnchere) {
		// TODO Auto-generated method stub
	}

	@Override
	public Utilisateur consulterProfilUtilisateurParId(long id) {
	Utilisateur u =this.consulterProfilUtilisateurParId(id);
	return u;
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.modifier(utilisateur);
		
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
}
