package fr.eni.projet_enchere.bo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Article {


	private int noArticle;
	private String nomArticle;
	private String description; 
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	private Retrait lieuRetrait;
	private List<Enchere> encheres;
	private Categorie categorie;
	private Utilisateur utilisateur;
	private int utilisateurId;
	private int categorieId;
	


	public Article(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Retrait lieuRetrait,
			List<Enchere> encheres, Categorie categorie, Utilisateur utilisateur, int utilisateurId, int categorieId) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.lieuRetrait = lieuRetrait;
		this.encheres = encheres;
		this.categorie = categorie;
		this.utilisateur = utilisateur;
		this.utilisateurId = utilisateurId;
		this.categorieId = categorieId;
	}
	
	public int getNoArticle() {
		return noArticle;
	}
	
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	
	public String getNomArticle() {
		return nomArticle;
	}
	
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	
	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	
	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}
	
	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	public int getMiseAPrix() {
		return miseAPrix;
	}
	
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	
	public int getPrixVente() {
		return prixVente;
	}
	
	public void setPrixVente(int priVente) {
		this.prixVente = priVente;
	}
	
	public String getEtatVente() {
		return etatVente;
	}
	
	public Article() {
	}
	

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public int getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public int getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", lieuRetrait=" + lieuRetrait
				+ ", encheres=" + encheres + ", categorie=" + categorie + ", utilisateur=" + utilisateur
				+ ", utilisateurId=" + utilisateurId + ", categorieId=" + categorieId + "]";

	}

	@Override
	public int hashCode() {
		return Objects.hash(noArticle, categorie, categorieId, dateDebutEncheres, dateFinEncheres, description,
				encheres, etatVente, lieuRetrait, miseAPrix, nomArticle, prixVente, utilisateur, utilisateurId);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return noArticle == other.noArticle && Objects.equals(categorie, other.categorie)
				&& categorieId == other.categorieId && Objects.equals(dateDebutEncheres, other.dateDebutEncheres)
				&& Objects.equals(dateFinEncheres, other.dateFinEncheres)
				&& Objects.equals(description, other.description) && Objects.equals(encheres, other.encheres)
				&& Objects.equals(etatVente, other.etatVente) && Objects.equals(lieuRetrait, other.lieuRetrait)
				&& miseAPrix == other.miseAPrix && Objects.equals(nomArticle, other.nomArticle)
				&& prixVente == other.prixVente && Objects.equals(utilisateur, other.utilisateur)
				&& utilisateurId == other.utilisateurId;
	}
	

}
