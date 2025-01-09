package fr.eni.projet_enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Article;
import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.rowmapper.ArticleRowMapper;
import fr.eni.projet_enchere.dal.rowmapper.EncheresRowMapper;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String FIND_BY_ID = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, a.no_utilisateur, a.no_categorie, "
			+ "c.libelle, u.pseudo, r.rue, r.code_postal, r.ville " 
			+ "FROM Articles_vendus a "
			+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie " 
			+ "INNER JOIN RETRAITS r ON a.no_article= r.no_article "
			+ "WHERE a.no_article = :idArticle";
	private static final String FIND_ALL_ARTICLE = "SELECT * FROM Articles_vendus";
	private static final String FIND_BY_NAME = 
		    "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, " +
		    "a.prix_initial, a.prix_vente, a.no_utilisateur, a.no_categorie, c.libelle, u.pseudo, r.rue, r.code_postal, r.ville " +
		    "FROM Articles_vendus a " +
		    "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur " +
		    "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie " +
		    "INNER JOIN RETRAITS r ON a.no_article = r.no_article " +
		    "WHERE a.nom_article LIKE :nomArticle";

	private static final String FIND_BY_CATEGORY = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_vendus WHERE no_categorie = :noCategorie";
	private static String INSERT_ARTICLE = "INSERT INTO Articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (:nom, :description, :dateDebutEncheres, :dateFinEncheres, :miseAPrix, :prixVente, :noVendeur, :noCategorie)";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (:noArticle, :rue, :codePostal, :ville)";
	private static final String FIND_BY_NAME_AND_CATEGORY = 
		    "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, " +
		    "a.prix_initial, a.prix_vente, a.no_utilisateur, a.no_categorie, c.libelle, u.pseudo, r.rue, r.code_postal, r.ville " +
		    "FROM Articles_vendus a " +
		    "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur " +
		    "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie " +
		    "INNER JOIN RETRAITS r ON a.no_article = r.no_article " +
		    "WHERE a.nom_article LIKE :nomArticle AND a.no_categorie = :noCategorie";

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
		params.addValue("prixVente", article.getMiseAPrix());
		params.addValue("noVendeur", article.getUtilisateur().getNoUtilisateur());
		params.addValue("noCategorie", article.getCategorie().getNoCategorie());
		jdbcTemplate.update(INSERT_ARTICLE, params, keyHolder);
		if (keyHolder.getKey() != null) {
			article.setNoArticle(keyHolder.getKey().longValue());
		}
		MapSqlParameterSource paramsRetrait = new MapSqlParameterSource();
		paramsRetrait.addValue("noArticle", article.getNoArticle());
		paramsRetrait.addValue("rue", article.getLieuRetrait().getRue());
		paramsRetrait.addValue("codePostal", article.getLieuRetrait().getCode_postal());
		paramsRetrait.addValue("ville", article.getLieuRetrait().getVille());

		jdbcTemplate.update(INSERT_RETRAIT, paramsRetrait);
	}

	@Override
	public Article read(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("idArticle", id);
		return this.jdbcTemplate.queryForObject(FIND_BY_ID, params, new ArticleRowMapper());
	}

	@Override
	public List<Article> findAll() {
		return jdbcTemplate.query(FIND_ALL_ARTICLE, new EncheresRowMapper());
	}

	@Override
	public Article readByName(String nomArticle) {
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("nomArticle", "%" + nomArticle + "%"); // Ajout des caractères génériques
	    return this.jdbcTemplate.queryForObject(FIND_BY_NAME, params, new ArticleRowMapper());
	}

	@Override
	public List<Article> findByName(String nomArticle) {
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("nomArticle", "%" + nomArticle + "%"); // Ajout des caractères génériques
	    return this.jdbcTemplate.query(FIND_BY_NAME, params, new ArticleRowMapper());
	}

	@Override
	public List<Article> findByCategory(Long noCategorie) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("noCategorie", noCategorie);
		return jdbcTemplate.query(FIND_BY_CATEGORY, params, new BeanPropertyRowMapper<>(Article.class));
	}

	@Override
	public void update(Article article) {
	    String sql = "UPDATE Articles_vendus SET nom_article = :nomArticle, description = :description, " +
	                 "date_debut_encheres = :dateDebutEncheres, date_fin_encheres = :dateFinEncheres, " +
	                 "prix_initial = :miseAPrix, prix_vente = :prixVente, no_utilisateur = :noUtilisateur, " +
	                 "no_categorie = :noCategorie WHERE no_article = :noArticle";
	    
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("nomArticle", article.getNomArticle());
	    params.addValue("description", article.getDescription());
	    params.addValue("dateDebutEncheres", article.getDateDebutEncheres());
	    params.addValue("dateFinEncheres", article.getDateFinEncheres());
	    params.addValue("miseAPrix", article.getMiseAPrix());
	    params.addValue("prixVente", article.getPrixVente());
	    params.addValue("noUtilisateur", article.getUtilisateur().getNoUtilisateur());
	    params.addValue("noCategorie", article.getCategorie().getNoCategorie());
	    params.addValue("noArticle", article.getNoArticle());
	    
	    jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Article> findByNomAndCategorie(String nomArticle, Long noCategorie) {
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("nomArticle", "%" + nomArticle + "%"); // Permet une recherche partielle sur le nom
	    params.addValue("noCategorie", noCategorie);
	    return jdbcTemplate.query(FIND_BY_NAME_AND_CATEGORY, params, new ArticleRowMapper());
	}

	@Override
	public List<Article> findByUtilisateur(Utilisateur utilisateur) {
	    String sql = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, " +
	                 "a.prix_initial, a.prix_vente, a.no_utilisateur, a.no_categorie, c.libelle, u.pseudo, r.rue, r.code_postal, r.ville " +
	                 "FROM Articles_vendus a " +
	                 "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur " +
	                 "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie " +
	                 "INNER JOIN RETRAITS r ON a.no_article = r.no_article " +
	                 "WHERE a.no_utilisateur = :noUtilisateur";
	    
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("noUtilisateur", utilisateur.getNoUtilisateur()); // On filtre par le numéro d'utilisateur
	    return jdbcTemplate.query(sql, params, new ArticleRowMapper()); // Retourner la liste des articles
	}

}
