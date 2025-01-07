package fr.eni.projet_enchere.bo;

import java.util.List;
import java.util.Objects;

public class Categorie {


	private short noCategorie;
	private String libelle;
	private List<Article> articles;

	public Categorie(short noCategorie, String libelle, List<Article> articles) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.articles = articles;
	}
	
	public Categorie() {
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
	
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return libelle;
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
