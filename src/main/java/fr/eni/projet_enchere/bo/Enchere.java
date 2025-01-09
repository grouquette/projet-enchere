package fr.eni.projet_enchere.bo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enchere {

	private long idEnchere;
	private LocalDateTime dateEnchere;
	private Integer montantEnchere;
	private Article article; // Association avec un article
	private Utilisateur utilisateur; // Association avec un utilisateur

	public Enchere() {
	}

	public Enchere(LocalDateTime dateEnchere, Integer montantEnchere, Article article, Utilisateur utilisateur) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public long getIdEnchere() {
		return idEnchere;
	}

	public void setIdEnchere(long idEnchere) {
		this.idEnchere = idEnchere;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montantEnchere + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateEnchere, montantEnchere);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enchere other = (Enchere) obj;
		return dateEnchere == other.dateEnchere && montantEnchere == other.montantEnchere;
	}
}
