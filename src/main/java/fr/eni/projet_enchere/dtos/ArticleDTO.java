package fr.eni.projet_enchere.dtos;

import java.time.LocalDateTime;

import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

public class ArticleDTO {

	private LocalDateTime dateDebutEncheres;
	private String nomArticle;
	private Integer montantEnchere;
	private String pseudo;
	
	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public ArticleDTO(LocalDateTime dateDebutEncheres, String nomArticle, Integer montantEnchere, String pseudo) {
		this.dateDebutEncheres = dateDebutEncheres;
		this.nomArticle = nomArticle;
		this.montantEnchere = montantEnchere;
		this.pseudo = pseudo;
	}
	
	
}
