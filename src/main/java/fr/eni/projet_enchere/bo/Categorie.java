package fr.eni.projet_enchere.bo;

import java.util.List;
import java.util.Objects;

public class Categorie {

	private short noCategorie;
	private String libelle;
	private List<ArticleVendu> articles;

	public Categorie(short noCategorie, String libelle, List<ArticleVendu> articles) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.articles = articles;
	}

	public short getNoCategorie() {
		return noCategorie;
	}
	
	public void setNoCategorie(short noCategorie) {
		this.noCategorie = noCategorie;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", articles=" + articles + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(articles, libelle, noCategorie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		return Objects.equals(articles, other.articles) && Objects.equals(libelle, other.libelle)
				&& noCategorie == other.noCategorie;
	}
	

	
}
