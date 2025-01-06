package fr.eni.projet_enchere.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Categorie;

@Repository
public class CategorieDAOImpl implements CategorieDAO {

    private static final String FIND_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM Categories";
    private static final String FIND_CATEGORY_BY_ID = "SELECT no_categorie, libelle FROM Categories WHERE no_categorie = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CategorieDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Categorie> findAll() {
        return jdbcTemplate.query(FIND_ALL_CATEGORIES, new BeanPropertyRowMapper<>(Categorie.class));
    }

	@Override
	public Categorie findById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<Categorie> result = jdbcTemplate.query(FIND_CATEGORY_BY_ID, params, new BeanPropertyRowMapper<>(Categorie.class));
        return result.isEmpty() ? null : result.get(0);
	}

}
