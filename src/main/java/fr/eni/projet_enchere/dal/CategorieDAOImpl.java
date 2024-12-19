package fr.eni.projet_enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Categorie;

@Repository
public class CategorieDAOImpl implements CategorieDAO {

    private static final String FIND_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM Categories";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CategorieDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Categorie> findAll() {
        return jdbcTemplate.query(FIND_ALL_CATEGORIES, new BeanPropertyRowMapper<>(Categorie.class));
    }
}
