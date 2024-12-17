package fr.eni.projet_enchere.bo;

import java.util.Objects;

public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description; 
	private int dateDebutEncheres;
	private int dateFinEncheres;
	private int miseAPrix;
	private int priVente;
	private String etatVente;
	
	public ArticleVendu(int noArticle, String nomArticle, String description, int dateDebutEncheres,
			int dateFinEncheres, int miseAPrix, int priVente, String etatVente) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.priVente = priVente;
		this.etatVente = etatVente;
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
	
	public int getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	
	public void setDateDebutEncheres(int dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	
	public int getDateFinEncheres() {
		return dateFinEncheres;
	}
	
	public void setDateFinEncheres(int dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	public int getMiseAPrix() {
		return miseAPrix;
	}
	
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	
	public int getPriVente() {
		return priVente;
	}
	
	public void setPriVente(int priVente) {
		this.priVente = priVente;
	}
	
	public String getEtatVente() {
		return etatVente;
	}
	
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}
	
	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", priVente=" + priVente + ", etatVente=" + etatVente + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(dateDebutEncheres, dateFinEncheres, description, etatVente, miseAPrix, noArticle,
				nomArticle, priVente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleVendu other = (ArticleVendu) obj;
		return dateDebutEncheres == other.dateDebutEncheres && dateFinEncheres == other.dateFinEncheres
				&& Objects.equals(description, other.description) && Objects.equals(etatVente, other.etatVente)
				&& miseAPrix == other.miseAPrix && noArticle == other.noArticle
				&& Objects.equals(nomArticle, other.nomArticle) && priVente == other.priVente;
	}
	
	
	
}
