package fr.eni.projet_enchere.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class Article {

	private long noArticle;
	@Size(min = 3, max = 250, message = "Le nom de l'article doit contenir entre 3 et 250 caractères")
	private String nomArticle;
	@Size(min = 3, max = 300, message = "La description doit contenir entre 3 et 250 caractères")
	private String description;
	@NotNull(message = "La date de début d'enchère est obligatoire")
	@FutureOrPresent(message = "Le date du début d'enchère ne peut pas être antérieure à aujourd'hui")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateDebutEncheres;
	@NotNull(message = "La date de fin d'enchère est obligatoire")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateFinEncheres;
	@NotNull(message = "La mise à prix est obligatoire")
	@PositiveOrZero(message = "La mise à prix ne peut pas être négative")
	private int miseAPrix;
	@NotNull(message = "Le prix de vente est obligatoire")
	private int prixVente;
	private String etatVente;
	@Valid
	private Retrait lieuRetrait;
	private List<Enchere> encheres;
	private Categorie categorie;
	private Utilisateur utilisateur;
	private long utilisateurId;

	@AssertTrue(message = "La date de fin doit être postérieure à la date de début")
	public boolean isDateFinEncheresValid() {
		if (dateDebutEncheres == null || dateFinEncheres == null) {
			return true; // La validation @NotNull s'occupera de ce cas
		}
		return dateFinEncheres.isAfter(dateDebutEncheres);
	}

	public Article() {
	}

	@AssertTrue(message = "Le prix de vente ne peut pas être inférieur à la mise à prix")
	private boolean isPrixVenteValid() {
		// Si le prix de vente est 0, cela signifie qu'il n'y a pas encore eu de vente
		if (prixVente == 0) {
			return true;
		}
		return prixVente >= miseAPrix;
	}

	public Article(long noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Retrait lieuRetrait,
			List<Enchere> encheres, Categorie categorie, Utilisateur utilisateur, long utilisateurId,
			long categorieId) {

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
	}

	public Article(String nomArticle, String description, Categorie categorie, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, Retrait lieuRetrait, Utilisateur utilisateur) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.categorie = categorie;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.lieuRetrait = lieuRetrait;
		this.prixVente = 0; // Par défaut, prixVente pourrait être 0.
		this.etatVente = "En attente"; // Par défaut, l'état de vente est "En attente".
		this.encheres = new ArrayList<>(); // Initialisation de la liste des enchères.
		this.utilisateur = utilisateur; // Vous pourriez également initialiser avec un utilisateur par défaut.
	}

	public long getNoArticle() {
		return noArticle;
	}

	public void setArticleId(long noArticle) {
		this.noArticle = noArticle;
	}

	public void setNoArticle(long noArticle) {
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

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
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

	public long getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", lieuRetrait=" + lieuRetrait
				+ ", encheres=" + encheres + ", categorie=" + categorie + ", utilisateur=" + utilisateur
				+ ", utilisateurId=" + utilisateurId + ", categorieId=" + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(noArticle, categorie, dateDebutEncheres, dateFinEncheres, description, encheres, etatVente,
				lieuRetrait, miseAPrix, nomArticle, prixVente, utilisateur, utilisateurId);
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
				&& Objects.equals(dateDebutEncheres, other.dateDebutEncheres)
				&& Objects.equals(dateFinEncheres, other.dateFinEncheres)
				&& Objects.equals(description, other.description) && Objects.equals(encheres, other.encheres)
				&& Objects.equals(etatVente, other.etatVente) && Objects.equals(lieuRetrait, other.lieuRetrait)
				&& miseAPrix == other.miseAPrix && Objects.equals(nomArticle, other.nomArticle)
				&& prixVente == other.prixVente && Objects.equals(utilisateur, other.utilisateur)
				&& utilisateurId == other.utilisateurId;
	}
}
