package fr.eni.projet_enchere.dal.rowmapper;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Enchere;
import fr.eni.projet_enchere.bo.Utilisateur;

public class EnchereRowMapper implements RowMapper<Enchere> {

	@Override
	public Enchere mapRow(ResultSet rs, int rowNum) throws java.sql.SQLException {
		Enchere e = new Enchere ();
		e.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
		e.setMontantEnchere(rs.getInt("montant_enchere"));
		
		Article a = new Article();
		a.setArticleId(rs.getLong("no_article"));
		a.setNomArticle(rs.getString("nom_article"));
		e.setArticle(a);
		
		Utilisateur u = new Utilisateur();
		u.setNoUtilisateur(rs.getLong("no_utilisateur"));
		u.setPseudo(rs.getString("pseudo"));
		e.setUtilisateur(u);
		return e;

	}
}
