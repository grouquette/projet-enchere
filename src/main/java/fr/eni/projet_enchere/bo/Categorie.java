package fr.eni.projet_enchere.bo;

import java.util.List;
import java.util.Objects;

public class Categorie {

	private short categorieId;
	private String libelle;
	private List<Article> articles;

	public Categorie(short noCategorie, String libelle, List<Article> articles) {
		this.categorieId = noCategorie;
		this.libelle = libelle;
		this.articles = articles;
	}

	public short getCategorieId() {
		return categorieId;
	}
	
	public void setCategorieId(short noCategorie) {
		this.categorieId = noCategorie;
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
		return "Categorie [noCategorie=" + categorieId + ", libelle=" + libelle + ", articles=" + articles + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(articles, libelle, categorieId);
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
				&& categorieId == other.categorieId;
	}
	

	
}
