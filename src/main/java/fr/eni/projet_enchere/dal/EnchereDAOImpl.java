package fr.eni.projet_enchere.dal;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Enchere;

@Repository
public class EnchereDAOImpl implements EnchereDAO {

	private static final String INSERT_ENCHERE = "INSERT INTO Encheres (date_enchere, montant_enchere, article_id, utilisateur_id) VALUES (:dateEnchere, :montantEnchere, :articleId, :utilisateurId)";

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
		params.addValue("articleId", noArticle);
		params.addValue("utilisateurId", utilisateurId);
		jdbcTemplate.update(INSERT_ENCHERE, params, keyHolder);
		if (keyHolder != null && keyHolder.getKey() != null) {
			enchere.setIdEnchere(keyHolder.getKey().longValue());
		}

	}

}
