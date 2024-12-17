package fr.eni.projet_enchere.bo;

import java.util.Objects;

public class Categorie {

	private short noCategorie;
	private String libelle;
	
	public Categorie(short noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
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
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(libelle, noCategorie);
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
		return Objects.equals(libelle, other.libelle) && noCategorie == other.noCategorie;
	}
	
	
	
}
