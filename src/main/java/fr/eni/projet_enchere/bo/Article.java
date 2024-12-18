package fr.eni.projet_enchere.bo;

import java.time.LocalDateTime;
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
	
	public Article(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int priVente, String etatVente, Retrait lieuRetrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = priVente;
		this.etatVente = etatVente;
		this.lieuRetrait = lieuRetrait;
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
	
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", lieuRetrait=" + lieuRetrait
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateDebutEncheres, dateFinEncheres, description, etatVente, lieuRetrait, miseAPrix,
				noArticle, nomArticle, prixVente);
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
		return dateDebutEncheres == other.dateDebutEncheres && Objects.equals(dateFinEncheres, other.dateFinEncheres)
				&& Objects.equals(description, other.description) && Objects.equals(etatVente, other.etatVente)
				&& Objects.equals(lieuRetrait, other.lieuRetrait) && miseAPrix == other.miseAPrix
				&& noArticle == other.noArticle && Objects.equals(nomArticle, other.nomArticle)
				&& prixVente == other.prixVente;
	}
	

}
