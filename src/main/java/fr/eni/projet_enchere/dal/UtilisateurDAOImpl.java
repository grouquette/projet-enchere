package fr.eni.projet_enchere.dal;

import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Utilisateur;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	private static final String INSERT = "INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :mdp, :credit, :admin)";

	private static final String FIND_BY_ID = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEURS WHERE no_utilisateur = :id";

	private static final String FIND_BY_PSEUDO = "SELECT * FROM Utilisateurs WHERE pseudo = :pseudo";

	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone= :telephone, rue = :rue, code_postal = :codePostal, ville = :ville, mot_de_passe = :motDePasse WHERE no_utilisateur = :email";

	private static final String FIND_ALL = "SELECT id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM Utilisateurs WHERE id = :id";
	
	private static final String COUNT_PSEUDO = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = :pseudo" ;
	
	private static final String COUNT_EMAIL = "SELECT COUNT(*) FROM UTILISATEURS WHERE email = :email" ;

	private static final String DELETE_BY_PSEUDO = "DELETE FROM UTILISATEURS WHERE pseudo = :pseudo";
	
	private NamedParameterJdbcTemplate jdbcTemplate;

	public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void creer(Utilisateur utilisateur) {
		// Manipulation de la clef primaire auto-générée : IDENTIY
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int numAdmin;
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("pseudo", utilisateur.getPseudo());
		map.addValue("nom", utilisateur.getNom());
		map.addValue("prenom", utilisateur.getPrenom());
		map.addValue("email", utilisateur.getEmail());
		map.addValue("telephone", utilisateur.getTelephone());
		map.addValue("rue", utilisateur.getRue());
		map.addValue("codePostal", utilisateur.getCodePostal());
		map.addValue("ville", utilisateur.getVille());
		map.addValue("mdp", utilisateur.getMotDePasse());
		map.addValue("credit", utilisateur.getCredit());

		if (utilisateur.isAdministrateur()) {
			numAdmin = 1;
		} else {
			numAdmin = 0;
		}
		map.addValue("admin", numAdmin);

		this.jdbcTemplate.update(INSERT, map, keyHolder);

		// Mise à jour de l'id du film avec celui généré par la BDD
		if (keyHolder != null && keyHolder.getKey() != null) {
			utilisateur.setNoUtilisateur(keyHolder.getKey().longValue());

		}
	}

	@Override
	public Utilisateur lire(long id) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("id", id);
		return jdbcTemplate.queryForObject(FIND_BY_ID, map, new BeanPropertyRowMapper<>(Utilisateur.class));
	}

	@Override
	public Utilisateur findByPseudo(String pseudo) {
		try {
			Map<String, Object> params = Map.of("pseudo", pseudo);
			return jdbcTemplate.queryForObject(FIND_BY_PSEUDO, params, new BeanPropertyRowMapper<>(Utilisateur.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void modifier(Utilisateur utilisateur) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("pseudo", utilisateur.getPseudo());
		map.addValue("nom", utilisateur.getNom());
		map.addValue("prenom", utilisateur.getPrenom());
		map.addValue("email", utilisateur.getEmail());
		map.addValue("telephone", utilisateur.getTelephone());
		map.addValue("rue", utilisateur.getRue());
		map.addValue("codePostal", utilisateur.getCodePostal());
		map.addValue("ville", utilisateur.getVille());
		map.addValue("mdp", utilisateur.getMotDePasse());

		this.jdbcTemplate.update(UPDATE, map);
	}

	@Override
	public boolean existPseudo(String pseudo) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("pseudo", pseudo);
		int nbPseudo = jdbcTemplate.queryForObject(COUNT_PSEUDO, map, Integer.class);
		return nbPseudo > 0 ? true : false;
	}

	@Override
	public boolean existEmail(String email) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("email", email);
		int nbEmail = jdbcTemplate.queryForObject(COUNT_EMAIL, map, Integer.class);
		return nbEmail > 0 ? true : false;
	}

	public void supprimer(String pseudo) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("pseudo", pseudo);
		this.jdbcTemplate.update(DELETE_BY_PSEUDO, map);
	
		
	}
}
