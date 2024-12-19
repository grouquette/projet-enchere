package fr.eni.projet_enchere.bo;

import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Utilisateur {

	private long noUtilisateur;
	@NotBlank(message = "le pseudo ne doit pas être vide.")
	@Pattern(regexp = "^[a-zA-Z0-9_]{3,30}$", message = "Le pseudo doit contenir entre 3 et 30 caractères, sans caractères spéciaux.")
	private String pseudo;
	@NotBlank(message = "le nom ne doit pas être vide.")
//	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\-\\s']+$", message = "Le nom ne doit contenir que des lettres, des espaces ou des traits d'union.")
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\-\\s']{1,30}$", message = "Le nom ne doit contenir que des lettres, des espaces ou des traits d'union.")
	private String nom;
	@NotBlank(message = "le prenom ne doit pas être vide.")
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\-\\s']{1,30}$", message = "Le prénom ne doit contenir que des lettres, des espaces ou des traits d'union.")
	private String prenom;
	@NotBlank(message = "le email ne doit pas être vide.")
	@Email(message = "L'email est invalide.")
	private String email;
	@NotBlank(message = "le telephone ne doit pas être vide.")
	@Pattern(regexp = "^(\\+\\d{1,3})?\\d{10}$", message = "Le numéro de téléphone doit contenir 10 chiffres, avec un préfixe international facultatif.")
	private String telephone;
	@NotBlank(message = "La rue ne doit pas être vide.")
	private String rue;
	@NotBlank(message = "le codePostal ne doit pas être vide.")
	@Pattern(regexp = "^\\d{5}$", message = "Le code postal doit contenir 5 chiffres.")
	private String codePostal;
	@NotBlank(message = "le ville ne doit pas être vide.")
//	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s'-]+$", message = "La ville ne doit contenir que des lettres, des espaces ou des traits d'union.")
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s'-]{1,30}$", message = "La ville ne doit contenir que des lettres, des espaces ou des traits d'union (max 30 caractères).")
	private String ville;
	@NotBlank(message = "le motDePasse ne doit pas être vide.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial.")
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	private List<Enchere> encheres;
	private List<Article> articles;

	public Utilisateur() {
		this.setCredit(0);
		this.setAdministrateur(false);
	}

	public Utilisateur(long noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,

			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur,
			List<Enchere> encheres, List<Article> articles) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.encheres = encheres;
		this.articles = articles;
	}

	public long getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(long noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + ", encheres=" + encheres + ", articles=" + articles + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(administrateur, articles, codePostal, credit, email, encheres, motDePasse, noUtilisateur,
				nom, prenom, pseudo, rue, telephone, ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return administrateur == other.administrateur && Objects.equals(articles, other.articles)
				&& codePostal == other.codePostal && credit == other.credit && Objects.equals(email, other.email)
				&& Objects.equals(encheres, other.encheres) && Objects.equals(motDePasse, other.motDePasse)
				&& noUtilisateur == other.noUtilisateur && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom) && Objects.equals(pseudo, other.pseudo)
				&& Objects.equals(rue, other.rue) && telephone == other.telephone && Objects.equals(ville, other.ville);
	}
}
