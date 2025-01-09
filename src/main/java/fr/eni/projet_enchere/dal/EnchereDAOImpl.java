package fr.eni.projet_enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Enchere;

@Repository
public class EnchereDAOImpl implements EnchereDAO {
	private static final String INSERT_ENCHERE = "INSERT INTO Encheres (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (:dateEnchere, :montantEnchere, :noArticle, :utilisateurId)";
	private static final String FIND_ALL_ENCHERE = "SELECT * FROM Encheres";
	private static final String FIND_BY_ARTICLE_ID = "SELECT * FROM Encheres WHERE no_article = :noArticle";
	private static final String FIND_LAST_ENCHERE_BY_ARTICLE_ID = "SELECT TOP 1 e.*, a.nom_article "
			+ "FROM Encheres e " + "INNER JOIN Articles_vendus a ON e.no_article = a.no_article "
			+ "WHERE e.no_article = :noArticle " + "ORDER BY e.date_enchere DESC";

	private static final String FIND_LAST_ENCHERE_BY_ARTICLE_NAME = "SELECT TOP 1 e.*, a.nom_article, u.pseudo "
			+ "FROM Encheres e " + "INNER JOIN Articles_vendus a ON e.no_article = a.no_article INNER JOIN UTILISATEURS u on e.no_utilisateur = u.no_utilisateur "
			+ "WHERE a.nom_article = :nomArticle " + "ORDER BY e.date_enchere DESC";
	private static final String COUNT_ENCHERE_UTILISATEUR = "SELECT COUNT(*) FROM ENCHERES WHERE no_utilisateur = :noUtilisateur AND no_article = :noArticle";

	private static final String UPDATE_ENCHERE_UTILISATEUR = "UPDATE ENCHERES SET date_enchere = :dateEnchere, montant_enchere =  :montantEnchere "
			+ "WHERE no_utilisateur = :noUtilisateur AND no_article = :noArticle";

	private NamedParameterJdbcTemplate jdbcTemplate;

	public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void creerEnchere(Enchere enchere, long noArticle, long utilisateurId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("dateEnchere", enchere.getDateEnchere());
		params.addValue("montantEnchere", enchere.getMontantEnchere());
		params.addValue("noArticle", noArticle);
		params.addValue("utilisateurId", utilisateurId);
		jdbcTemplate.update(INSERT_ENCHERE, params);
	}

	@Override
	public List<Enchere> findAll() {
		return jdbcTemplate.query(FIND_ALL_ENCHERE, new BeanPropertyRowMapper<>(Enchere.class));
	}

	@Override
	public List<Enchere> findByArticleId(long noArticle) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("noArticle", noArticle);
		return jdbcTemplate.query(FIND_BY_ARTICLE_ID, params, new BeanPropertyRowMapper<>(Enchere.class));
	}

	@Override
	public Enchere findLastEnchereByArticleId(long noArticle) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("noArticle", noArticle);
		List<Enchere> result = jdbcTemplate.query(FIND_LAST_ENCHERE_BY_ARTICLE_ID, params,
				new BeanPropertyRowMapper<>(Enchere.class));
		return result.isEmpty() ? null : result.get(0); 
	}

	@Override
	public Enchere findLastEnchereByArticleName(String nomArticle) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("nomArticle", nomArticle);
		List<Enchere> result = jdbcTemplate.query(FIND_LAST_ENCHERE_BY_ARTICLE_NAME, params,
				new BeanPropertyRowMapper<>(Enchere.class));
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public void updateEnchere(Enchere enchere, long noArticle, long utilisateurId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("dateEnchere", enchere.getDateEnchere());
		params.addValue("montantEnchere", enchere.getMontantEnchere());
		params.addValue("noArticle", noArticle);
		params.addValue("noUtilisateur", utilisateurId);
		jdbcTemplate.update(UPDATE_ENCHERE_UTILISATEUR, params);
		
	}
	
	@Override
	public boolean enchereUnique(long noUtilisateur, long noArticle) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("noUtilisateur", noUtilisateur);
		map.addValue("noArticle", noArticle);
		int nbEnchere = jdbcTemplate.queryForObject(COUNT_ENCHERE_UTILISATEUR, map, Integer.class);
		return nbEnchere > 0 ? true : false;
	}

	
}
