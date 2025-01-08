package fr.eni.projet_enchere.dal.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Categorie;
import fr.eni.projet_enchere.bo.Retrait;
import fr.eni.projet_enchere.bo.Utilisateur;

public class EncheresRowMapper implements RowMapper<Article> {

	@Override
	public Article mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
		Article article = new Article();
		article.setNoArticle(rs.getInt("no_article"));
		article.setNomArticle(rs.getString("nom_article"));
		article.setDescription(rs.getString("description"));
		article.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
		article.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
		article.setMiseAPrix(rs.getInt("prix_initial"));
		article.setPrixVente(rs.getInt("prix_vente"));
		article.setUtilisateurId(rs.getInt("no_utilisateur"));

//		// Association utilisateur
//		Utilisateur utilisateur = new Utilisateur();
//		utilisateur.setNoUtilisateur(rs.getLong("no_utilisateur"));
//		utilisateur.setPseudo(rs.getString("pseudo"));
//		article.setUtilisateur(utilisateur);

//		// Association catégorie
//		Categorie categorie = new Categorie();
//		categorie.setNoCategorie(rs.getShort("no_categorie"));
//		categorie.setLibelle(rs.getString("libelle"));
//		article.setCategorie(categorie);
//
//		// Association retrait
//		Retrait retrait = new Retrait();
//		retrait.setRue(rs.getString("rue"));
//		retrait.setCode_postal(rs.getString("code_postal"));
//		retrait.setVille(rs.getString("ville"));
//		article.setLieuRetrait(retrait);

		return article;

	}
}
