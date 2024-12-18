package fr.eni.projet_enchere.dal.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import fr.eni.projet_enchere.bo.Article;

public class ArticleRowMapper implements RowMapper<Article> {
		@Override
		public Article mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
			Article article = new Article();
			article.setNoArticle(rs.getInt("id"));
			article.setNomArticle(rs.getString("nom"));
			article.setDescription(rs.getString("description"));
			article.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
			article.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
			article.setMiseAPrix(rs.getInt("mise_a_prix"));
			article.setPrixVente(rs.getInt("prix_vente"));
			article.setEtatVente(rs.getString("etat_vente"));
			article.setUtilisateurId(rs.getInt("vendeur_id"));
			article.setCategorieId(rs.getInt("categorie_id"));
			return article;
		}
	}
