package fr.eni.projet_enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Enchere;

@Repository
public class EnchereDAOImpl implements EnchereDAO {
	private static final String INSERT_ENCHERE = "INSERT INTO Encheres (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (:dateEnchere, :montantEnchere, :noArticle, :utilisateurId)";
	private static final String FIND_ALL_ENCHERE = "SELECT * FROM Encheres";
	private static final String FIND_BY_ARTICLE_ID = "SELECT * FROM Encheres WHERE no_article = :noArticle";
	private NamedParameterJdbcTemplate jdbcTemplate;

	public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void creerEnchere(Enchere enchere, long noArticle, long utilisateurId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("dateEnchere", enchere.getDateEnchere());
		params.addValue("montantEnchere", enchere.getMontantEnchere());
		params.addValue("noArticle", noArticle);
		params.addValue("utilisateurId", utilisateurId);
		jdbcTemplate.update(INSERT_ENCHERE, params, keyHolder);
		if (keyHolder != null && keyHolder.getKey() != null) {
			enchere.setIdEnchere(keyHolder.getKey().longValue());
		}
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
}
