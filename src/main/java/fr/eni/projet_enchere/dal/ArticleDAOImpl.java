package fr.eni.projet_enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.dal.rowmapper.ArticleRowMapper;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String FIND_BY_ID = "SELECT id, nom, description, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente, vendeur_id, categorie_id FROM Articles WHERE id = :idArticle";
	private static final String FIND_ALL_BY_ID = "SELECT id, nom, description, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente, vendeur_id, categorie_id FROM Articles WHERE id = :idArticle";
	private static String INSERT_ARTICLE = "INSERT INTO Articles (nom, description, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente, vendeur_id, categorie_id) VALUES (:nom, :description, :dateDebutEncheres, :dateFinEncheres, :miseAPrix, :prixVente, :etatVente, :vendeurId, :categorieId)";
	private static final String FIND_ALL_ARTICLE = "SELECT * FROM Articles_vendus";

	public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void creerArticle(Article article) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("nom", article.getNomArticle());
		params.addValue("description", article.getDescription());
		params.addValue("dateDebutEncheres", article.getDateDebutEncheres());
		params.addValue("dateFinEncheres", article.getDateFinEncheres());
		params.addValue("miseAPrix", article.getMiseAPrix());
		params.addValue("prixVente", article.getPrixVente());
		params.addValue("etatVente", article.getEtatVente());
		params.addValue("vendeurId", article.getUtilisateurId());
		params.addValue("categorieId", article.getCategorieId());
		jdbcTemplate.update(INSERT_ARTICLE, params, keyHolder);
		if (keyHolder.getKey() != null) {
			article.setNoArticle(keyHolder.getKey().longValue());
		}
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

	@Override
	public List<Article> findAll() {
		return jdbcTemplate.query(FIND_ALL_ARTICLE, new BeanPropertyRowMapper<>(Article.class));
	}
}
