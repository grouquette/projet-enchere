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

	private static final String FIND_BY_ID = "SELECT id, nom, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, vendeur_id, categorie_id FROM Articles WHERE id = :idArticle";
	private static final String FIND_ALL_BY_ID = "SELECT id, nom, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, vendeur_id, categorie_id FROM Articles WHERE id = :idArticle";
	private static String INSERT_ARTICLE = "INSERT INTO Articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, vendeur_id, categorie_id) VALUES (:nom, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :etat_vente, :vendeur_id, :categorie_id)";
	private static final String FIND_ALL_ARTICLE = "SELECT * FROM Articles_vendus";
	private static final String FIND_BY_NAME = "SELECT a.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, r.rue, r.code_postal, r.ville,c.libelle, u.pseudo, a.no_utilisateur, a.no_categorie FROM Articles_vendus a  INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie INNER JOIN RETRAITS r ON a.no_article = r.no_article WHERE nom_article = :nomArticle";
	private static final String FIND_BY_CATEGORY = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_vendus WHERE no_categorie = :noCategorie";


	public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void creerArticle(Article article) {
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("nom", article.getNomArticle());
	    params.addValue("description", article.getDescription());
	    params.addValue("date_debut_encheres", article.getDateDebutEncheres());
	    params.addValue("date_fin_encheres", article.getDateFinEncheres());
	    params.addValue("prix_initial", article.getMiseAPrix());
	    params.addValue("prix_vente", article.getPrixVente());
	    params.addValue("etat_vente", article.getEtatVente());
	    params.addValue("vendeur_id", article.getUtilisateurId());
	    params.addValue("categorie_id", article.getCategorieId());
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
	public List<Article> findByName(String nomArticle) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("nomArticle", nomArticle);
		return this.jdbcTemplate.query(FIND_BY_NAME, params, new ArticleRowMapper());
	}

	@Override
	public List<Article> findAll() {
		return jdbcTemplate.query(FIND_ALL_ARTICLE, new BeanPropertyRowMapper<>(Article.class));
	}

	@Override
	public Article readByName(String nomArticle) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("nomArticle", nomArticle);
		return this.jdbcTemplate.queryForObject(FIND_BY_NAME, params, new ArticleRowMapper());
	}

	@Override
	public List<Article> findByCategory(Long noCategorie) {
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("noCategorie", noCategorie);
	    return jdbcTemplate.query(FIND_BY_CATEGORY, params, new BeanPropertyRowMapper<>(Article.class));
	}

	@Override
	public void save(Article article) {
		creerArticle(article);
		
	}

}
