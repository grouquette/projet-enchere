package fr.eni.projet_enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.dal.rowmapper.ArticleRowMapper;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String FIND_BY_ID = "SELECT id, nom, description, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente, vendeur_id, categorie_id FROM Articles WHERE id = :idArticle";
	private static final String FIND_ALL_BY_ID = "SELECT id, nom, description, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente, vendeur_id, categorie_id FROM Articles WHERE id = :idArticle";

	public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Article read(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("idArticle", id);
		return this.jdbcTemplate.queryForObject(FIND_BY_ID, params, new ArticleRowMapper());
	}

	@Override
	public List<Article> findById(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("idArticle", id);
		return this.jdbcTemplate.query(FIND_ALL_BY_ID, params, new ArticleRowMapper());
	}
}
